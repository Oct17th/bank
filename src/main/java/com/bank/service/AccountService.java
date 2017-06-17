package com.bank.service;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidAmountException;
import com.bank.exception.UserException;

/**
 * 账户业务接口
 *
 * @author YiJie  2017/6/15
 **/
public interface AccountService{


    /**
     * 查询余额
     *
     * @param user 用户名
     * @return 账户余额
     */
    public double inquiry(String user);

    /**
     * 取款
     *
     * @param user 用户名
     * @param amount 金额
     * @return 账户余额
     */
    public double withdrawals(String user,double amount) throws AccountOverDrawnException, InvalidAmountException;

    /**
     * 存款
     *
     * @param user 用户名
     * @param amount 金额
     * @return 账户余额
     */
    public double deposit(String user,double amount) throws InvalidAmountException;

    /**
     * 转账
     *
     * @param from 转账人
     * @param to   收款人
     * @param amount 转账金额
     * @return 转账人账户余额
     */
    public double transfer(String from, String to,double amount) throws InvalidAmountException, AccountOverDrawnException, UserException;

}
