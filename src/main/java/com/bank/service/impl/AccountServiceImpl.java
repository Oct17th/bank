package com.bank.service.impl;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidAmountException;
import com.bank.exception.PropertiesNotFoundException;
import com.bank.exception.UserException;
import com.bank.service.AccountService;
import com.bank.dao.util.PropertiesUtil;

import java.io.File;

/**
 * 账户业务实现
 *
 * @author YiJie  2017/6/15
 **/
public class AccountServiceImpl implements AccountService {
    String path;
    PropertiesUtil propertiesUtil;

    private AccountServiceImpl() throws PropertiesNotFoundException {
        path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
        propertiesUtil = new PropertiesUtil(path);
    }

    public AccountServiceImpl(PropertiesUtil propertiesUtil) {
        this.propertiesUtil = propertiesUtil;
    }

    /**
     * 查询余额
     *
     * @param userName 用户名
     * @return 余额
     */
    public double inquiry(String userName) {
        String balance = propertiesUtil.get(userName + ".account");
        return null == balance || ("").equals(balance) ? 0 : Double.parseDouble(balance);//
        /**
         * 此处空值校验说明：其实不会出现balance为空串的情况，账户通过register方法写入，用户一注册，即初始化account=0。
         * 不会出现用户存在，用户账号却为null或为""的情况。
         * @see UserServiceImpl#register(String, String)
         */
    }

    /**
     * 存款
     *
     * @param userName 用户名
     * @param amount   金额
     * @return 账户余额
     * @throws InvalidAmountException 存入金额为负数
     */
    public double deposit(String userName, double amount) throws InvalidAmountException {
        if (amount <= 0) {//存入金额为负数
            throw new InvalidAmountException(amount);
        }
//        checkUser(user);//传入的用户名是系统保存的登录用户数据，不会为空，相当于已做校验
        double balance = inquiry(userName);//一定要先用inquiry取，因为set前money值可能各种为空，inquiry方法里做了判空处理
        String key = userName + ".account";
        propertiesUtil.set(key, String.valueOf(balance + amount));
        return Double.parseDouble(propertiesUtil.get(key));
    }

    /**
     * 取款
     *
     * @param userName 用户名
     * @param amount   金额
     * @return 账户余额
     * @throw InvalidAmountException 取款金额为负数
     * @throw AccountOverDrawnException 账户余额不足
     */
    public double withdrawals(String userName, double amount) throws InvalidAmountException, AccountOverDrawnException {
        if (amount <= 0) {//取款金额为负数
            throw new InvalidAmountException(amount);
        }
//        checkUser(user);//传入的用户名是系统保存的登录用户数据，不会为空，相当于已做校验
        double balance = inquiry(userName);//一定要先用inquiry取，因为set前money值可能各种为空，inquiry方法里做了判空处理
        if (balance - amount < 0) {//判断取款金额是否超出余额
            throw new AccountOverDrawnException(amount);
        }
        String key = userName + ".account";
        propertiesUtil.set(key, String.valueOf(balance - amount));
        return Double.parseDouble(propertiesUtil.get(key));
    }

    /**
     * 转账 //TODO 存入转账记录
     *
     * @param from   转账人用户名
     * @param to     收款人用户名
     * @param amount 转账金额
     * @return 转账人账户余额
     * @throw InvalidAmountException 转账金额为负数
     * @throw AccountOverDrawnException 转账人账户余额不足
     * @throw UserException 用户异常信息
     */
    public double transfer(String from, String to, double amount) throws InvalidAmountException, AccountOverDrawnException, UserException {
        checkTransferUser(from, to);
        withdrawals(from, amount);
        deposit(to, amount);
        return inquiry(from);
    }

    /**
     * 校验用户是否存在
     *
     * @param userName
     * @throws UserException
     */
    public void checkUser(String userName) throws UserException {
        if (propertiesUtil.get(userName) == null) {
            throw new UserException("不存在用户" + userName + "！");
        }
    }

    /**
     * 校验转账用户是否合法
     * 1.转账人与收款人判空
     * 2.转账人不能为收款人
     *
     * @param from
     * @param to
     * @throws UserException
     */
    private void checkTransferUser(String from, String to) throws UserException {
//        checkUser(from);//传入的用户名是系统保存的登录用户数据，不会为空，相当于已做校验
        checkUser(to);
        if (from.equals(to)) {
            throw new UserException("转账人与收款人相同！");
        }
    }
}
