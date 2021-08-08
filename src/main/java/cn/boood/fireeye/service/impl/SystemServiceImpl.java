package cn.boood.fireeye.service.impl;

import cn.boood.fireeye.dao.SensitiveWordsMapper;
import cn.boood.fireeye.dao.TaskMapper;
import cn.boood.fireeye.dao.UserMapper;
import cn.boood.fireeye.mybatis.entity.AdminUser;
import cn.boood.fireeye.mybatis.entity.SensitiveWords;
import cn.boood.fireeye.mybatis.entity.TaskInfo;
import cn.boood.fireeye.service.SystemService;
import cn.boood.fireeye.vo.TaskNum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/3 10:53
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SensitiveWordsMapper sensitiveWordsMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Override
    public int changePassword(AdminUser user) {
        return userMapper.updateAdminUser(user.getUsername(),user.getPassword());
    }

    @Override
    public List<TaskNum> getLastTasks() {
        List<TaskInfo> taskInfos=taskMapper.lastTaskInfos();
        List<TaskNum> taskNums=new ArrayList<>();
        for (TaskInfo taskInfo : taskInfos){
            int num=sensitiveWordsMapper.countByTaskId(taskInfo.getTaskId());
            taskNums.add(new TaskNum(taskInfo.getTaskName(),num));
        }
        if(taskNums.size()<5){
            for(int i=0;i<5-taskNums.size();i++){
                taskNums.add(new TaskNum());
            }
        }
        return taskNums;
    }
}
