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
     * @param params url name
     * @return
     */
    List<TaskInfo> getTaskInfos(Map<String,Object> params);
}
