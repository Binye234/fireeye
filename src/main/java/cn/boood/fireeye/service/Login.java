package cn.boood.fireeye.service;

import cn.boood.fireeye.mybatis.entity.AdminUser;

public interface Login {

    public AdminUser getAdminUser(String name);
}
