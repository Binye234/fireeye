package cn.boood.fireeye.vo;

import java.util.Date;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/8 14:55
 */
public class TaskInfoVo {
    private String taskId;
    private String taskName;
    private String taskUrl;
    private String taskTime;

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

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }
}
