package cn.boood.fireeye.service;

import cn.boood.fireeye.mybatis.entity.AdminUser;
import cn.boood.fireeye.vo.TaskNum;

import java.util.List;

public interface SystemService {
    /**
     * 更改密码
     * @param user
     * @return
     */
    public int changePassword(AdminUser user);

    /**
     * 取得最新任务情况
     * @return
     */
    public List<TaskNum> getLastTasks();
}
