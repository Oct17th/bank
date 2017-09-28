package com.bank.dao.impl.mysql.mybatis;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author YiJie 2017/9/28.
 */
public abstract class MybatisSupport {

    private static ApplicationContext applicationContext;

    protected static final UserDAO userDAO;

    protected static final AccountDAO accountDAO;

    static {
        applicationContext = new ClassPathXmlApplicationContext("/mybatis/applicationContext_mybatis.xml");
        accountDAO = (AccountDAO) applicationContext.getBean("accountDAO");
        userDAO = (UserDAO) applicationContext.getBean("userDAO");
    }

}
