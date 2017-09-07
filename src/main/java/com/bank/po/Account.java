package com.bank.po;

/**
 * 账户持久类
 *
 * @author YiJie 2017/9/4.
 */
public class Account {
    /**
     * 用户
     */
    User user;
    /**
     * 账户余额
     */
    Float balance;

    public Account() {
    }

    public Account(String name) {
        this.user = new User(name);
    }

    public Account(String name, Float balance) {
        this.user = new User(name);
        this.balance = balance;
    }

    public Account(User user, Float balance) {
        this.user = user;
        this.balance = balance;
    }

    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        if (user == null) {
            setUser(new User());
        }
        this.user.setName(name);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
