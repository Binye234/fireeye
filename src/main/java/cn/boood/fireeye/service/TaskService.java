package cn.boood.fireeye.service;

import cn.boood.fireeye.mybatis.entity.TaskInfo;
import cn.boood.fireeye.vo.SensitiveWordsVo;
import cn.boood.fireeye.vo.TaskInfoVo;

import java.util.List;

public interface TaskService {
    /**
     * 按任务名查找
     * @param name
     * @return
     */
    public List<TaskInfoVo> getTasks(String name,int page,int rows);
    /**
     * 得到按任务名查找总数
     * @param name
     * @return
     */
    public int getTasksCount(String name);

    /**
     * 生成任务
     * @param name
     * @param url
     * @return
     */
    public int insertTask(String name,String url);

    /**
     * 删除任务
     * @param taskId
     */
    public void deleteTask(String taskId);

    /**
     * 按任务id取敏感词记录
     * @param taskId
     * @return
     */
    public List<SensitiveWordsVo> getSensitiveWords(String taskId,int page,int rows);

    /**
     * 按任务id查找记录数
     * @param taskId
     * @return
     */
    public int getSensitiveWordsCount(String taskId);
}
