package cn.boood.fireeye.spider;

import cn.boood.fireeye.cache.TaskSpiderInfo;
import cn.boood.fireeye.mybatis.entity.SensitiveWords;
import cn.boood.fireeye.utils.DFAUtil;
import cn.boood.fireeye.utils.HttpUtils;
import cn.boood.fireeye.utils.PublicUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HtmlUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/7 17:31
 */
public class CustomPageProcessor implements PageProcessor {
    private Site site=Site.me().setCharset("utf-8").setRetryTimes(3).setSleepTime(1000);

    public TaskSpiderInfo getTaskSpiderInfo() {
        return taskSpiderInfo;
    }

    public void setTaskSpiderInfo(TaskSpiderInfo taskSpiderInfo) {
        this.taskSpiderInfo = taskSpiderInfo;
    }

    private TaskSpiderInfo taskSpiderInfo;
    @Override
    public void process(Page page) {
        Html html=page.getHtml();
        List<String> links=html.links().all();
        links=getLinks(links);

        page.addTargetRequests(links);
        String text=DFAUtil.cleanHtmlTag(html.toString());
        List<String> keys= DFAUtil.matchKey(text);
        Set<String> set=DFAUtil.wordsToSet(keys);
        List<SensitiveWords> list=new ArrayList<>();
        for (String str : set){
            list.addAll(findSensitiveWords(taskSpiderInfo.getTaskInfo().getTaskId(),page.getUrl().toString(),str,text));
        }
        if(list.size()>0){
            page.putField("SensitiveWords",list);
        }
    }

    /**
     * 找到文本内所有敏感词语句生成记录
     * @param taskId
     * @param url
     * @param keyword
     * @param text
     * @return
     */
    private List<SensitiveWords> findSensitiveWords(String taskId,String url,String keyword,String text){
        List<String> result=DFAUtil.findKeyContents(text,keyword);
        List<SensitiveWords> list=new ArrayList<>();
        for (String str : result){
            SensitiveWords sensitiveWords=new SensitiveWords();
            sensitiveWords.setTaskId(taskId);
            sensitiveWords.setSwId(PublicUtil.getUUID());
            sensitiveWords.setSwWords(keyword);
            sensitiveWords.setSwUrl(url);
            sensitiveWords.setSwContent(str);
            sensitiveWords.setSwTime(new Date());
            list.add(sensitiveWords);
        }
        return list;
    }
    @Override
    public Site getSite() {
        return site;
    }
    /**
     * 对爬取到的url进行清洗非本站的一律去除
     * @param links 爬取到的url集合
     * @return 清洗过后的集合
     */
    private List<String> getLinks(List<String> links){
        List<String> result=new ArrayList<String>();
        for(String s : links){
            if(s.equals("")||s==null){
                continue;
            }else if(!excludeDomainAndSuffix(s)){
                continue;
            }else {
                result.add(s.replace("#",""));
            }
        }
        return result;
    }

    /**
     * 排出其它域名的网址和文件下载网址
     * @param url
     * @return
     */
    private boolean excludeDomainAndSuffix(String url){
        if(url.indexOf(taskSpiderInfo.getDomain())<0){
            return false;
        }
        if(HttpUtils.excludeSuffix(url)){
            return false;
        }
        return true;
    }
}
