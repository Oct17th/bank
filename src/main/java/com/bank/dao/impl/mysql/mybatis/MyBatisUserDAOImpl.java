package com.bank.dao.impl.mysql.mybatis;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import com.bank.po.Account;
import com.bank.po.User;

/**
 * @author YiJie 2017/9/27.
 */
public class MyBatisUserDAOImpl implements UserDAO {

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User old, User user) {
        return 0;
    }

    @Override
    public int deleteUser(User user) {
        return 0;
    }

    @Override
    public User queryUser(User user) {
        return null;
    }
}
