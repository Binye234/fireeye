package cn.boood.fireeye.spider;

import cn.boood.fireeye.cache.TaskSpiderInfo;
import cn.boood.fireeye.cache.TasksCache;
import cn.boood.fireeye.mybatis.entity.TaskInfo;
import cn.boood.fireeye.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/7 22:55
 */
@Component
public class SpiderRun {
    @Autowired
    private MysqlPipeline mysqlPipeline;
    public void run(TaskInfo taskInfo){
        TaskSpiderInfo taskSpiderInfo=new TaskSpiderInfo();
        taskSpiderInfo.setTaskInfo(taskInfo);
        taskSpiderInfo.setDomain(HttpUtils.getDomain(taskInfo.getTaskUrl()));
        CustomPageProcessor customPageProcessor=new CustomPageProcessor();
        Spider spider=Spider.create(customPageProcessor);
        taskSpiderInfo.setSpider(spider);
        customPageProcessor.setTaskSpiderInfo(taskSpiderInfo);
        //缓存任务
        TasksCache.addTask(taskSpiderInfo);
        //布隆过滤器
        QueueScheduler queueScheduler=new QueueScheduler();
        queueScheduler.setDuplicateRemover(new BloomFilterDuplicateRemover(100000));

        spider.setScheduler(queueScheduler);
        spider.addUrl(taskInfo.getTaskUrl());

        spider.addPipeline(mysqlPipeline);
        spider.thread(20);
        spider.run();
    }
}
