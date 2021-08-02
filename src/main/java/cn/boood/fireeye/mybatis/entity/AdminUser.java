package cn.boood.fireeye.mybatis.entity;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/1 21:40
 */
public class AdminUser {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
