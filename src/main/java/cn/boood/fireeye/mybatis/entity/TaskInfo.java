package cn.boood.fireeye.mybatis.entity;

import java.util.Date;

/**
 * @description:
 * @author: boood
 * @time: 2021/7/31 21:36
 */
public class TaskInfo {
    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskUrl='" + taskUrl + '\'' +
                ", taskTime=" + taskTime +
                '}';
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }
    private String taskId;
    private String taskName;
    private String taskUrl;
    private Date taskTime;
}
