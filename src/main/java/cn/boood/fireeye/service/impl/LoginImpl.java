package cn.boood.fireeye.service.impl;

import cn.boood.fireeye.dao.UserMapper;
import cn.boood.fireeye.mybatis.entity.AdminUser;
import cn.boood.fireeye.service.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/2 15:20
 */
@Service
public class LoginImpl implements Login {
    @Autowired
    private UserMapper userMapper;

    @Override
    public AdminUser getAdminUser(String name) {
        return userMapper.getAdminUser(name);
    }
}
