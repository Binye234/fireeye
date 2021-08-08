package cn.boood.fireeye.service.impl;

import cn.boood.fireeye.dao.SensitiveWordsMapper;
import cn.boood.fireeye.dao.TaskMapper;
import cn.boood.fireeye.mybatis.entity.TaskInfo;
import cn.boood.fireeye.service.TaskService;
import cn.boood.fireeye.spider.SpiderRun;
import cn.boood.fireeye.utils.PublicUtil;
import cn.boood.fireeye.vo.TaskInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/3 21:31
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private SpiderRun spiderRun;
    @Autowired
    private SensitiveWordsMapper sensitiveWordsMapper;
    @Override
    public List<TaskInfoVo> getTasks(String name) {
        List<TaskInfo> taskInfos=taskMapper.getTaskInfos(name);
        List<TaskInfoVo> vos=new ArrayList<>();
        for (TaskInfo info : taskInfos){
            vos.add(toTaskInfoVo(info));
        }
        return vos;
    }

    private TaskInfoVo toTaskInfoVo(TaskInfo info){
        TaskInfoVo vo=new TaskInfoVo();
        vo.setTaskId(info.getTaskId());
        vo.setTaskName(info.getTaskName());
        vo.setTaskUrl(info.getTaskUrl());
        vo.setTaskTime(PublicUtil.getDateFormat(info.getTaskTime()));
        return vo;
    }
    /**
     * 按任务名得到记录数
     * @param name
     * @return
     */
    @Override
    public int getTasksCount(String name) {
        return taskMapper.getTaskInfosCount(name);
    }

    @Override
    public int insertTask(String name, String url) {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setTaskId(PublicUtil.getUUID());
        taskInfo.setTaskName(name);
        taskInfo.setTaskUrl(url);
        taskInfo.setTaskTime(new Date());
        taskMapper.insertTask(taskInfo);
        spiderRun.run(taskInfo);
        return 1;
    }

    @Override
    public void deleteTask(String taskId) {
        taskMapper.delByTaskIdAfter(taskId);
        sensitiveWordsMapper.delByTaskId(taskId);
    }
}
