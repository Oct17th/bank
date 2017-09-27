package com.bank.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author YiJie 2017/9/27.
 */
public class ServiceFactory {

    private static UserService userService;
    private static AccountService accountService;
    private static ApplicationContext ac;

    static {
        ac = new ClassPathXmlApplicationContext("daoimpl.xml");
        userService = (UserService) ac.getBean("userService");
        accountService = (AccountService) ac.getBean("accountService");
    }

    public static UserService getUserService() {
        return userService;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

}
