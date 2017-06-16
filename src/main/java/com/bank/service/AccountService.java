package com.bank.service;

/**
 * 账户业务接口
 *
 * @author YiJie  2017/6/15
 **/
public interface AccountService{


    /**
     * 查询余额
     *
     * @param name 用户名
     */
    public void inquiry(String name);

    /**
     * 取款
     *
     * @param amount 金额
     */
    public void withdrawals(double amount);

    /**
     * 存款
     *
     * @param amount 金额
     */
    public void deposit(double amount);

    /**
     * 转账
     *
     * @param from 转账人
     * @param to   收款人
     */
    public void transfer(String from, String to);

}
