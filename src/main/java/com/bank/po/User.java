package com.bank.po;

/**
 * 用户持久类
 *
 * @author YiJie 2017/9/4.
 */
public class User {
    /**
     * 用户名
     */
    String name;
    /**
     * 密码
     */
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
    }
}
