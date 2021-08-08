package cn.boood.fireeye.cache;

import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/7 23:07
 */
public class TasksCache {
    /**
     * 正在运行任务缓存队列
     */
   static private List<TaskSpiderInfo> spiderInfoList=new ArrayList<>();

   static public List<TaskSpiderInfo> getSpiderInfoList() {
       removeTask();
       return spiderInfoList;
    }

    /**
     * 去除已运行结束的任务
     */
   static public void removeTask(){
        for (TaskSpiderInfo taskSpiderInfo : spiderInfoList){
            if(taskSpiderInfo.getSpider().getStatus()== Spider.Status.Stopped){
                spiderInfoList.remove(taskSpiderInfo);
            }
        }
   }

    /**
     * 缓存任务
     * @param taskSpiderInfo
     */
   static public void addTask(TaskSpiderInfo taskSpiderInfo){
       spiderInfoList.add(taskSpiderInfo);
   }
}
