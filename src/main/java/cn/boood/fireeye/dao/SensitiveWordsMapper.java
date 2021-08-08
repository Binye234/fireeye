package cn.boood.fireeye.dao;


import cn.boood.fireeye.mybatis.entity.SensitiveWords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SensitiveWordsMapper {
    /**
     * 按任务查找记录
     * @param taskId
     * @return
     */
    List<SensitiveWords> getSensitiveWords(@Param("taskId") String taskId);

    /**
     * 插入记录
     * @param words
     * @return
     */
    int insertSensitiveWords(@Param("sensitiveWords") SensitiveWords words);

    /**
     * 查询任务记录总数
     * @param taskId
     * @return
     */
    int countByTaskId(@Param("taskId") String taskId);

    /**
     * 按id删除任务
     * @param taskId
     * @return
     */
    int delByTaskId(@Param("taskId") String taskId);
}
