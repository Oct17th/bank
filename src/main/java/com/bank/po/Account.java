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
     * 用户名
     */
    String name;
    /**
     * 账户余额
     */
    Float balance;

    private static final Float DEFAULT_BALANCE = 0.0f;

    public Account() {
        super();
    }

    public Account(User user) {
        this.user = user;
        this.name = user.getName();
        this.balance = DEFAULT_BALANCE;
    }

    public Account(String name) {
        this(new User(name));
    }

    public Account(User user, Float balance) {
        this.user = user;
        this.name = user.getName();
        this.balance = balance;
    }

    public Account(String name, Float balance) {
        this(new User(name), balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (user == null) {
            setUser(new User());
        }
        this.user.setName(name);
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.name = user.getName();
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user=" + user +
                ", balance=" + balance +
                '}';
    }
}
