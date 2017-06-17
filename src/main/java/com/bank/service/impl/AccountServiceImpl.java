package com.bank.service.impl;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidDepositionException;
import com.bank.exception.PropertiesNotFoundException;
import com.bank.service.AccountService;
import com.bank.util.PropertiesUtil;

import java.io.File;

/**
 * 账户业务实现
 *
 * @author YiJie  2017/6/15
 **/
public class AccountServiceImpl implements AccountService {
    String path;
    PropertiesUtil propertiesUtil;

    public AccountServiceImpl() throws PropertiesNotFoundException {
        path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
        propertiesUtil = new PropertiesUtil(path);
    }

    /**
     * @param name 用户名
     * @return 余额 //TODO 暂时不考虑没开户的情况（即Bank.properties文件里没有moneny键，或money键的值为空或""），若properties取money值为空，返回账户余额为0
     */
    public double inquiry(String name) {
        String money = propertiesUtil.get("money");
        return null == money || ("").equals(money) ? 0 : Double.parseDouble(money);
    }

    public double deposit(double amount) throws InvalidDepositionException {
        if(amount<0){//存入金额为负数
            throw new InvalidDepositionException(amount);
        }
        String key = "money";
        double balance = inquiry("user");//一定要先用inquiry取，因为set前money值可能各种为空，inquiry方法里做了判空处理
        propertiesUtil.set(key, String.valueOf(balance + amount));
        return Double.parseDouble(propertiesUtil.get(key));
    }

    public double withdrawals(double amount) throws AccountOverDrawnException {//TODO 参数里应该有用户名
        String key = "money";
        double balance = inquiry("user");//一定要先用inquiry取，因为set前money值可能各种为空，inquiry方法里做了判空处理
        if(balance - amount<0){//判断取款金额是否超出余额
            throw new AccountOverDrawnException(amount);
        }
        propertiesUtil.set(key, String.valueOf(balance - amount));
        return Double.parseDouble(propertiesUtil.get(key));
    }

    public double transfer(String from, String to) {

        return 0;
    }

}
