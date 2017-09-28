package com.bank.controller.springmvc;

import com.bank.service.AccountService;
import com.bank.service.ServiceFactory;
import com.bank.service.UserService;

/**
 * @author YiJie 2017/9/28.
 */
public abstract class ControllerSupport {
    protected static AccountService accountService;
    protected static UserService userService;

    static {
        accountService = ServiceFactory.getAccountService();
        userService = ServiceFactory.getUserService();
    }
}
