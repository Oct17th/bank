package com.bank.service;

import com.bank.exception.UserException;

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
    public void register(String name, String password) throws UserException;

    /**
     * 登录
     *
     * @param name     用户名
     * @param password 密码
     */
    public void login(String name, String password) throws UserException;

    /**
     * 退出系统
     */
    public void exitSystem();

    /**
     * 校验用户是否存在
     *
     * @param userName
     * @throws UserException
     */
    public void checkUser(String userName) throws UserException;
}