package com.bank.service;

import com.bank.exception.PropertiesNotFoundException;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.util.PropertiesUtil;

/**
 * @author YiJie  2017/6/15
 **/
public class AccountServiceTest {
    PropertiesUtil propertiesUtil = new PropertiesUtil("E:\\Workspace\\bank\\src\\main\\resources\\Bank.properties");
    AccountService accountService = new AccountServiceImpl(propertiesUtil);

    public AccountServiceTest() throws PropertiesNotFoundException {
    }

    @org.junit.Test
    public void inquiry() throws Exception {
        System.out.println(accountService.inquiry("user"));
    }

    @org.junit.Test
    public void deposit() throws Exception {
        System.out.println(accountService.deposit("user", 20));
    }

    @org.junit.Test
    public void withdrawals() throws Exception {
        accountService.deposit("user", 20);
        System.out.println(accountService.withdrawals("user", 12));
    }

    @org.junit.Test
    public void transfer() throws Exception {
        System.out.println(accountService.transfer("user", "user1", 5));
    }
}