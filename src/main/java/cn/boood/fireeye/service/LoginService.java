package cn.boood.fireeye.service;

import cn.boood.fireeye.mybatis.entity.AdminUser;

public interface LoginService {
    /**
     * 查找用户
     * @param name
     * @return
     */
    public AdminUser getAdminUser(String name);
}
