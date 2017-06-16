package com.bank.service;

/**
 * 用户业务接口
 *
 * @author YiJie  2017/6/15
 **/
public interface UserService {

    /**
     * 注册
     *
     * @param name     用户名
     * @param password 密码
     */
    public void register(String name, String password);

    /**
     * 登录
     *
     * @param name     用户名
     * @param password 密码
     */
    public void login(String name, String password);

    /**
     * 退出系统
     *
     * @param name 用户名
     */
    public void exitSystem(String name);

}
