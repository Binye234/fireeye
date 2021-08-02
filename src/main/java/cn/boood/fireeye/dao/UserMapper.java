package cn.boood.fireeye.dao;

import cn.boood.fireeye.mybatis.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/1 21:41
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 查找用户
     * @param name
     * @return
     */
     AdminUser getAdminUser(@Param("name") String name);

    /**
     * 更新密码
     * @param name
     * @param password
     * @return
     */
     int updateAdminUser(@Param("name") String name,@Param("password") String password);
}
