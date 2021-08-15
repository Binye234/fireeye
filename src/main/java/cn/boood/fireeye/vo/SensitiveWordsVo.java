package cn.boood.fireeye.vo;

import java.util.Date;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/15 14:14
 */
public class SensitiveWordsVo {
    private String taskId;
    private String swId;
    private String swWords;
    private String swUrl;
    private String swContent;
    private String swTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSwId() {
        return swId;
    }

    public void setSwId(String swId) {
        this.swId = swId;
    }

    public String getSwWords() {
        return swWords;
    }

    public void setSwWords(String swWords) {
        this.swWords = swWords;
    }

    public String getSwUrl() {
        return swUrl;
    }

    public void setSwUrl(String swUrl) {
        this.swUrl = swUrl;
    }

    public String getSwContent() {
        return swContent;
    }

    public void setSwContent(String swContent) {
        this.swContent = swContent;
    }

    public String getSwTime() {
        return swTime;
    }

    public void setSwTime(String swTime) {
        this.swTime = swTime;
    }
}
