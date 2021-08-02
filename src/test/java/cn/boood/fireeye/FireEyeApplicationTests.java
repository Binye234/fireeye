package cn.boood.fireeye;

import cn.boood.fireeye.cache.WordsCache;
import cn.boood.fireeye.dao.SensitiveWordsMapper;
import cn.boood.fireeye.dao.TaskMapper;
import cn.boood.fireeye.dao.UserMapper;
import cn.boood.fireeye.mybatis.entity.AdminUser;
import cn.boood.fireeye.mybatis.entity.SensitiveWords;
import cn.boood.fireeye.mybatis.entity.TaskInfo;
import cn.boood.fireeye.utils.DFAUtil;
import cn.boood.fireeye.utils.PublicUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

@SpringBootTest
class FireEyeApplicationTests {
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SensitiveWordsMapper sensitiveWordsMapper;
    @Test
    void contextLoads()  {
        List<String> words=new ArrayList<>();
        words.add("政府");
        words.add("河东");
        words.add("王勇");
        WordsCache.addWords(words);
        String text="\n" +
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head><script id=\"allmobilize\" charset=\"utf-8\" src=\"//ysp.www.gov.cn/013582404bd78ad3c016b8fffefe6a9a/allmobilize.min.js\"></script><meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" /><link rel=\"alternate\" media=\"handheld\" href=\"#\"/>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "<meta http-equiv=\"x-ua-compatible\" content=\"IE=edge\" >\n" +
                "<link href=\"http://www.gov.cn/govweb/xhtml/favicon.ico\" rel=\"shortcut icon\" type=\"image/x-icon\">\n" +
                "<title>王勇在河北检查指导防汛减灾工作_国务委员王勇_中国政府网</title>\n" +
                "<meta name=\"others\" content=\"页面生成时间 2021-08-01 07:09:14\" />\n" +
                "<meta name=\"template,templategroup,version\" content=\"2486,100039,8.3\" />\n" +
                "<meta name=\"keywords\" content=\"安全,措施,工作,防汛,应急,\">\n" +
                "<meta name=\"description\" content='王勇在河北张家口详细检查冬奥会、冬残奥会场馆及配套设施防汛应急工作，现场查看海河堤防建设、防汛治理工程和城区低洼易涝点防汛排涝措施落实情况。他指出，要深刻吸取近期局地强降雨致灾教训，高度警惕灾害性天气风险，全面排查防大汛防大涝短板隐患并抓紧针对性加以改进，切实加强防范应对措施，全力确保安全度汛。,2021-08-01-06:08:00' />\n" +
                "<meta name=\"catalog\" content=\"c1396\">\n" +
                "<meta name=\"lanmu\" content=\"国务委员王勇\">\n" +
                "<meta name='manuscriptId' content=\"5628785\">\n" +
                "<meta name='author' content=\"宋岩\">\n" +
                "<meta name='firstpublishedtime' content=\"2021-08-01-06:08:00\"> \n" +
                "<meta name='lastmodifiedtime' content=\"2021-08-01-07:08:00\">\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/govweb/xhtml/2016gov/css/base.css\">\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/govweb/xhtml/2016gov/css/common.css\">\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/govweb/xhtml/2016gov/css/common_detail.css\">\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/govweb/xhtml/2016gov/css/common_detail_n920.css\">\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/govweb/xhtml/2016gov/public/share2020/resources/shareStyle.css\">\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/govweb/xhtml/2016gov/css/date.css\">\n" +
                "<script src=\"/govweb/xhtml/2016gov/js/jquery-1.8.3.min.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"/govweb/xhtml/2016gov/js/manuscript.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"/govweb/xhtml/2016gov/js/hover.js\"></script>\n" +
                "<style type=\"text/css\">\n" +
                ".editor span{\n" +
                "\tmargin-right:20px;}\n" +
                ".editor span a{\tcolor:#888888;\n" +
                "}\n" +
                "</style>\n" +
                "<style type=\"text/css\">\n" +
                ".shuzi{ display:none;}\n" +
                ".editor span{\n" +
                "\tmargin-right:20px;}\n" +
                ".editor span a{\tcolor:#888888;\n" +
                "}\n" +
                "@media print {\n" +
                "\t.nav{ \n" +
                "background:none!important;\n" +
                "\tbackground-color:#000;\n" +
                "\t}\n" +
                "\t.shuzi{ display:block;font-size:100px;width:596x;height:455px;margin:0px auto; text-align:center; margin-top:35px;}\n" +
                "\t\t.player_video{ display:none; height:0px;}\n" +
                "\t\t#v_player{ display:none!important;}\n" +
                "\t\t#player_video{ display:none;}\n" +
                "\t\t#iframe1{ display:none;}\n" +
                "\t} \n" +
                "    @page :pseudo-class {\n" +
                "      size: A4 landscape;\n" +
                "      margin:5cm;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "  <div style=\"display: block;position: relative;\">\n" +
                "    <div class=\"share-popup\" id=\"share-popup\">\n" +
                "      <h6>分享到：<b></b></h6>\n" +
                "      <ul>\n" +
                "        <li><a data-w=\"gwds_qzone\" class=\"share-btn gwds_qzone\">QQ空间</a></li>\n" +
                "        <li><a data-w=\"gwds_douban\" class=\"share-btn gwds_douban\">豆瓣网</a></li>\n" +
                "      </ul>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div style=\"display:none\">\n" +
                "</div> \n" +
                "  \n" +
                "<div class=\"main\"> \n" +
                "  <div style=\"display:none\"><img  src=\"/govweb/xhtml/zhuanti/2017lysy/images/guohui.jpg\" width=\"400\" height=\"400\" /></div>\n" +
                "<!--header-->\n" +
                "<iframe id=\"ifr_top\" src=\"/2016public/top.htm\" width=\"100%\" height=\"148\" scrolling=\"no\" marginheight=\"0\" frameborder=\"0\"></iframe>\n" +
                "<!--end header-->\n" +
                "<div class=\"content\">\n" +
                "  <div class=\"padd\">\n" +
                "  <!--面包屑开始-->\n" +
                "  <div class=\"BreadcrumbNav\">\n" +
                "<a href='/index.htm'  target=\"_blank\">首页</a>&nbsp;>&nbsp;<a href='/guowuyuan/index.htm'  target=\"_blank\">国务院</a>&nbsp;>&nbsp;<a href='/guowuyuan/wangyong/index.htm'  target=\"_blank\">王勇</a>\n" +
                "\n" +
                "  </div>\n" +
                "    <!--面包屑结束-->\n" +
                "    <div class=\"article oneColumn pub_border\">\n" +
                "      <h1>\n" +
                "        王勇在河北检查指导防汛减灾工作\n" +
                "      </h1>\n" +
                "      <div class=\"pages-date\">2021-08-01 06:59  <span class=\"font\">来源： 新华社</span>\n" +
                "      <div class=\"pages_print\"><span class=\"font index_switchsize\">【字体：<span class=\"bigger\">大</span> <span class=\"medium\">中</span> <span class=\"smaller\">小</span>】</span><span style=\"cursor:pointer;\" class=\"font printIco\" onclick=\"javascript:window.print()\">打印</span>\n" +
                " <!-- share -->\n" +
                "        <div class=\"share\" id=\"share\">\n" +
                "          <div class=\"share-icon\"></div>\n" +
                "          <div class=\"share-box\" id=\"share-box\">\n" +
                "            <a class=\"share-btn gwds_weixin\" data-w=\"gwds_weixin\" title=\"微信\">&nbsp;</a>\n" +
                "            <a class=\"share-btn gwds_tsina\" data-w=\"gwds_tsina\" title=\"新浪微博\">&nbsp;</a>\n" +
                "            <span class=\"gwds_more\">&nbsp;</span>\n" +
                "          </div>\n" +
                "         \n" +
                "        </div>\n" +
                "\t\t\t</div>\n" +
                "      </div>\n" +
                "          <div class=\"shuzi\"><img src=\"http://www.gov.cn/govweb/xhtml/2016gov/images/public/20190530videobg.jpg\" width=\"596\" height=\"455\"></div>\n" +
                "      <div class=\"pages_content\" id=\"UCAP-CONTENT\">\n" +
                "        <p style=\"font-family: 宋体; font-size: 12pt; text-align: center;\"><span style=\"font-weight: bold; color: rgb(128, 0, 0);\">王勇在河北检查指导防汛减灾工作时强调<br>\n" +
                "<span style=\"font-size: 12pt; text-indent: 2em;\">进一步抓细抓实防汛责任措施<br>\n" +
                "</span><span style=\"font-size: 12pt; text-indent: 2em;\">切实提高暴雨洪水防御应急能力</span></span></p>\n" +
                "<p style=\"text-indent: 2em; font-family: 宋体; font-size: 12pt;\">新华社石家庄7月31日电 国务委员、国家防总总指挥王勇31日在河北检查指导防汛减灾工作。他强调，要深入贯彻习近平总书记“七一”重要讲话和关于防汛减灾工作的重要指示精神，认真落实党中央、国务院决策部署，进一步提高认识、强化责任、完善措施，加强防范应对和应急联动，更加扎实细致做好防大汛防大涝各项准备工作，确保人民群众生命财产安全。</p>\n" +
                "<p style=\"text-indent: 2em; font-family: 宋体; font-size: 12pt;\">王勇在河北张家口详细检查冬奥会、冬残奥会场馆及配套设施防汛应急工作，现场查看海河堤防建设、防汛治理工程和城区低洼易涝点防汛排涝措施落实情况。他指出，要深刻吸取近期局地强降雨致灾教训，高度警惕灾害性天气风险，全面排查防大汛防大涝短板隐患并抓紧针对性加以改进，切实加强防范应对措施，全力确保安全度汛。</p>\n" +
                "<p style=\"text-indent: 2em; font-family: 宋体; font-size: 12pt;\">王勇强调，要高度重视冬奥会、冬残奥会等重点工程设施防汛排涝和应急工作，完善超常规暴雨洪水等灾害应急预案，做好应对各类灾害性天气的周密准备，确保场馆设施建设和运行安全。海河流域河流源短流急、行洪能力不足，要精准分析研判、科学规范发布雨情汛情，严密防范城市内涝和山洪、泥石流、滑坡等次生灾害，强化应急抢险排涝人员、装备和物资准备，坚持不间断巡查防守，严防河道堤坝、蓄滞洪区围堤长期浸泡出现险情。紧盯城市低洼易涝点，强化以气象预警为先导的应急联动机制，严格落实灾害性天气情况下交通、旅游等重点行业和地铁、地下空间等重点场所安全管控措施，提高群众防汛避险意识，及时果断组织受威胁人员转移避险，坚决守住不发生重大人员伤亡底线，最大限度减少洪涝灾害损失。</p>\n" +
                "      </div>\n" +
                "<div class=\"editor\"><span><a href=\"http://www.gov.cn/fuwu/jiucuo.htm\" target=\"_blank\">【我要纠错】</a> </span>责任编辑：宋岩 </div>\n" +
                "<div id=\"pageBreak\"></div>\n" +
                "<script type=\"text/javascript\">manuscriptPage_N()</script>\n" +
                "      <div id=\"div_div\">\n" +
                "        <div id=\"qr_container\" style=\"margin:auto; position:relative;\">扫一扫在手机打开当前页</div>\n" +
                "</div>\n" +
                "<!-- 用来校验该浏览器是否支持HTML5 -->\n" +
                "<canvas id=\"Canvas\"></canvas>\n" +
                "      \n" +
                "     \n" +
                "<!--相关稿件-->\n" +
                "  <div class=\"xg-list related\">\n" +
                "    <div class=\"pannel-title\">相关稿件</div>\n" +
                "    <ul class=\"list01\">\n" +
                "        <li>\n" +
                "          <a href=\"/guowuyuan/2021-07/22/content_5626689.htm\" target=\"_blank\"  >王勇：加强统筹协调 狠抓措施落实 全力做好防汛抢险救灾各项工作</a>\n" +
                "          </li>\n" +
                "        \n" +
                "        <li>\n" +
                "          <a href=\"/guowuyuan/2021-07/21/content_5626412.htm\" target=\"_blank\"  >王勇指挥部署河南防汛抢险救灾工作</a>\n" +
                "          </li>\n" +
                "        \n" +
                "        <li>\n" +
                "          <a href=\"/guowuyuan/2021-07/18/content_5625838.htm\" target=\"_blank\"  >王勇在河南和安徽检查指导防汛工作</a>\n" +
                "          </li>\n" +
                "        \n" +
                "\n" +
                "      </ul>\n" +
                "  </div>\n" +
                "  <!--相关稿件-->\n" +
                "  <div class=\"clear\"></div>\n" +
                "\n" +
                "<!--微信分享图功能-->\n" +
                "<div class=\"share-title\" style=\"display:none\">王勇在河北检查指导防汛减灾工作</div>\n" +
                "  <div class=\"share-substr\" style=\"display:none\">王勇在河北张家口详细检查冬奥会、冬残奥会场馆及配套设施防汛应急工作，现场查看海河堤防建设、防汛治理工程和城区低洼易涝点防汛排涝措施落实情况。他指出，要深刻吸取近期局地强降雨致灾教训，高度警惕灾害性天气风险，全面排查防大汛防大涝短板隐患并抓紧针对性加以改进，切实加强防范应对措施，全力确保安全度汛。</div>\n" +
                "  <img class=\"share-img\" style=\"display:none\" src=\"http://www.gov.cn/zhuanti/20190109weixinfenxiangimg/150.jpg\" width=\"300\" height=\"300\" /> \n" +
                "  <script src=\"https://res.wx.qq.com/open/js/jweixin-1.0.0.js\"></script> \n" +
                "  <script src=\"http://www.gov.cn/govweb/xhtml/2016gov/js/20191226wxShare.js\"></script> \n" +
                "  <!--微信分享图功能--> \n" +
                "     \n" +
                "      <!--二维码微信微博-->\n" +
                "      <!--国务院客户端  微博微信  三个链接-->\n" +
                "<!--<div class=\"de_bannerS\" style=\"position: relative;\">\n" +
                "\t<img src=\"/govweb/xhtml/2016gov/images/public/de_bannerS.jpg\">\n" +
                "    <a href=\"http://www.gov.cn/public/yidongkehuduan.htm\" target=\"_blank\" style=\"position: absolute; top: 1px; left: 1px; display: block; width: 528px; height: 128px; opacity: 1; background: none;\"></a>\n" +
                "\t<a href=\"http://www.gov.cn/home/2014-02/18/content_5046260.htm\" target=\"_blank\" style=\"position: absolute; top: 1px; left: 531px; width: 360px; height: 128px; opacity: 1; background: none;\"></a>\n" +
                "</div>-->\n" +
                "\n" +
                "<!--营改增专题  微博微信  三个链接-->\n" +
                "<div class=\"de_bannerS\" style=\" position: relative;\">\n" +
                "\t<img src=\"/govweb/xhtml/2016gov/images/public/de_bannerS_sjh.jpg\">\n" +
                "    <a href=\"http://www.gov.cn/public/yidongkehuduan.htm\" target=\"_blank\" style=\"position: absolute; top: 1px; left: 1px; display: block; width: 141px; height: 128px; opacity: 0;filter:alpha(opacity=0);-khtml-opacity:0;-moz-opacity:0;\"></a>\n" +
                "\t<a href=\"https://liuyan.www.gov.cn/wxzlsjh/index.htm\" target=\"_blank\" style=\"position: absolute; top: 1px; left: 143px; width: 370px; height: 128px; opacity: 0; opacity: 0;filter:alpha(opacity=0);-khtml-opacity:0;-moz-opacity:0;\"></a>\n" +
                "\t<a href=\"http://www.gov.cn/home/2014-02/18/content_5046260.htm\" target=\"_blank\" style=\"position: absolute; top: 1px; left: 531px; width: 360px; height: 128px; opacity: 0; opacity: 0;filter:alpha(opacity=0);-khtml-opacity:0;-moz-opacity:0;\"></a>\n" +
                "</div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"clear\"></div>\n" +
                "</div>\n" +
                " <!--公共尾开始-->\n" +
                "  <iframe id=\"ifr_foot.htm\" src=\"/2016public/bottom.htm\" width=\"100%\" height=\"525\" scrolling=\"no\" marginheight=\"0\" frameborder=\"0\"></iframe>\n" +
                "  <!--公共尾结束-->\n" +
                "<div class=\"back_top\">\n" +
                "<a>回到\n" +
                "顶部</a>\n" +
                "</div></div>\n" +
                "<div style=\"display:none\">\n" +
                "<script type=\"text/javascript\">document.write(unescape(\"%3Cscript src='//stat.www.gov.cn/webdig.js?z=1' type='text/javascript'%3E%3C/script%3E\"));</script>\n" +
                "<script type=\"text/javascript\">wd_paramtracker(\"_wdxid=000000000000000000000000000000000000000000\")</script>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "(function(){var N=100;var P=0.9;var R=N*P;var X=Math.floor(Math.random()*N);if(R>=X){_atrk_opts={atrk_acct:\"noGfn1aMp4107i\",domain:\"www.gov.cn\",dynamic:true};(function(){var as=document.createElement(\"script\");as.type=\"text/javascript\";as.async=true;as.src=\"https://www.gov.cn/alexametrics/atrk.js\";var s=document.getElementsByTagName(\"script\")[0];s.parentNode.insertBefore(as,s)})()}})();\n" +
                "</script>\n" +
                "<noscript><img src=\"https://certify.alexametrics.com/atrk.gif?account=noGfn1aMp4107i\" style=\"display:none\" height=\"1\" width=\"1\" alt=\"\" /></noscript>\n" +
                "</body> </body>\n" +
                "</html>\n" +
                "<script src=\"/govweb/xhtml/2016gov/js/jquery.date_input.pack.js\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "$(function(){\n" +
                "\t$('.date_picker').date_input();\n" +
                "\t})\n" +
                "</script>\n" +
                "<script>\n" +
                "\n" +
                "if ($(\"#v_player, .player_video\").length > 0) {\n" +
                "        $('.shuzi').add();\n" +
                "    }\n" +
                "    else {\n" +
                "     $('.shuzi').remove();\n" +
                "    }\n" +
                "\n" +
                "</script>\n" +
                "<script src=\"/govweb/xhtml/2016gov/js/article_pub.js\"></script>\n" +
                "<script src=\"/govweb/xhtml/2016gov/js/public.js\"></script>\n" +
                "<script src=\"/govweb/xhtml/2016gov/js/search_option.js\"></script>\n" +
                "<script src=\"/govweb/xhtml/2016gov/js/qrcode.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"/govweb/xhtml/2016gov/js/jquery.qrcode.js\" type=\"text/javascript\"></script>\n" +
                "<!--20180408修改js去掉新华微博-->\n" +
                "<script src=\"http://www.gov.cn/zgzfw/sharepage/resources/share.js\"></script>\n" +
                "<script type=text/javascript src=\"http://static.gridsumdissector.com/js/Clients/GWD-801026-069B76/gs.js\"></script>";
        text=DFAUtil.cleanHtmlTag(text);
        List<String> machall = WordsCache.getWordTree().matchAll(text,-1,true,true);
        Set<String> set=DFAUtil.wordsToSet(machall);
        System.out.println(set);
        for (String word : set){
            List<String> contents=DFAUtil.findKeyContents(text,word);
            for (String s : contents){
                System.out.println(s);
            }
        }
        Assertions.assertNotNull(machall);
    }
    @Test
    void Test1(){
        SensitiveWords words=new SensitiveWords("ef964842f33611eb85ab0a80ff2603de","ef964842f33611eb85ab0a80ff2603d9",
                "测试1","https://www.hao123.com","test",new Date());
        int i=sensitiveWordsMapper.insertSensitiveWords(words);

        List<SensitiveWords> list=sensitiveWordsMapper.getSensitiveWords("ef964842f33611eb85ab0a80ff2603de");
        for (SensitiveWords word: list){
            System.out.println(word);
        }
        Assertions.assertNotNull(list);
    }

}
