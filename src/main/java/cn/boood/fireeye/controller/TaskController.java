package cn.boood.fireeye.controller;

import cn.boood.fireeye.mybatis.entity.TaskInfo;
import cn.boood.fireeye.service.TaskService;
import cn.boood.fireeye.utils.HttpUtils;
import cn.boood.fireeye.utils.XSSUtil;
import cn.boood.fireeye.vo.SystemMsg;
import cn.boood.fireeye.vo.TaskInfoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/3 21:26
 */
@Controller
@RequestMapping("/system")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 按任务名找记录
     * @param taskname
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @PostMapping("/searchtask")
    public Map FindTasks(String taskname) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        int count=taskService.getTasksCount(taskname);
        Map map=new HashMap();
        if(count==0){
            return null;
        }
        List<TaskInfoVo> taskInfos=taskService.getTasks(taskname);

        map.put("total",count);
        map.put("rows",taskInfos);
        return map;
    }
    @ResponseBody
    @PostMapping("/newtask")
    public SystemMsg CreateTask(@Param("taskname" ) String taskname, @Param("taskurl") String taskurl){
        SystemMsg msg=new SystemMsg();
        if(taskname.equals("")||taskname.equals("undefined")||taskurl.equals("")||taskurl.equals("undefined")){
            msg.setCode("202");
            msg.setMsg("任务信息输入错误");
            return msg;
        }
        taskname= XSSUtil.xssFilter(taskname);
        taskurl=XSSUtil.xssFilter(taskurl);
        if(!HttpUtils.isVisit(taskurl)){
            msg.setCode("201");
            msg.setMsg("网址无法访问");
            return msg;
        }
        String finalTaskname = taskname;
        String finalTaskurl = taskurl;
        new Thread(()->taskService.insertTask(finalTaskname, finalTaskurl)).start();
        msg.setCode("200");
        msg.setMsg("任务建立成功");
        return msg;
    }
    @ResponseBody
    @PostMapping("/deletetask")
    public String deleteTask(String taskId){
        taskService.deleteTask(taskId);
        return "true";
    }

}