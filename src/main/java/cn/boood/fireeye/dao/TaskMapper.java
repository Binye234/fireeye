package cn.boood.fireeye.dao;

import cn.boood.fireeye.mybatis.entity.TaskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TaskMapper {
    /**
     * 插入任务记录
     * @param taskInfo
     * @return
     */
    int insertTask(@Param("taskInfo") TaskInfo taskInfo);

    /**
     * 查询任务记录
     * @param   name
     * @return
     */
    List<TaskInfo> getTaskInfos(@Param("taskname") String name);

    /**
     * 得到查询的记录数
     * @param name
     * @return
     */
    int getTaskInfosCount(@Param("taskname") String name);
    /**
     * 查询最新5条任务记录
     * @return
     */
    List<TaskInfo> lastTaskInfos();

    /**
     * 按id删除任务
     * @param taskId
     * @return
     */
    int delByTaskIdAfter(@Param("taskId") String taskId);
}
