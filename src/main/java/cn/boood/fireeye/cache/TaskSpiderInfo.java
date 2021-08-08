package cn.boood.fireeye.cache;

import cn.boood.fireeye.mybatis.entity.TaskInfo;
import us.codecraft.webmagic.Spider;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/7 17:35
 */
public class TaskSpiderInfo {
    private TaskInfo taskInfo;
    private Spider spider;
    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public Spider getSpider() {
        return spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }
}
