package cn.boood.fireeye.mybatis.entity;

import java.util.Date;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/2 9:23
 */
public class SensitiveWords {
    @Override
    public String toString() {
        return "SensitiveWords{" +
                "taskId='" + taskId + '\'' +
                ", swId='" + swId + '\'' +
                ", swWords='" + swWords + '\'' +
                ", swUrl='" + swUrl + '\'' +
                ", swContent='" + swContent + '\'' +
                ", swTime=" + swTime +
                '}';
    }

    public SensitiveWords(){}

    public SensitiveWords(String taskId, String swId, String swWords, String swUrl, String swContent, Date swTime) {
        this.taskId = taskId;
        this.swId = swId;
        this.swWords = swWords;
        this.swUrl = swUrl;
        this.swContent = swContent;
        this.swTime = swTime;
    }

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

    public Date getSwTime() {
        return swTime;
    }

    public void setSwTime(Date swTime) {
        this.swTime = swTime;
    }

    private String taskId;
    private String swId;
    private String swWords;
    private String swUrl;
    private String swContent;
    private Date swTime;
}
