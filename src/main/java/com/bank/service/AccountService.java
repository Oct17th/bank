package com.bank.service;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidDepositionException;

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
     * @return 账户余额
     */
    public double inquiry(String name);

    /**
     * 取款
     *
     * @param amount 金额
     * @return 账户余额
     */
    public double withdrawals(double amount) throws AccountOverDrawnException;

    /**
     * 存款
     *
     * @param amount 金额
     * @return 账户余额
     */
    public double deposit(double amount) throws InvalidDepositionException;

    /**
     * 转账
     *
     * @param from 转账人
     * @param to   收款人
     * @return 转账人账户余额
     */
    public double transfer(String from, String to);

}
