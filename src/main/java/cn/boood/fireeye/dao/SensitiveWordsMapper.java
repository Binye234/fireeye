package cn.boood.fireeye.dao;


import cn.boood.fireeye.mybatis.entity.SensitiveWords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
}
