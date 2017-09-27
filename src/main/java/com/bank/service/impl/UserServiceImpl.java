package com.bank.service.impl;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import com.bank.exception.UserException;
import com.bank.po.Account;
import com.bank.po.User;
import com.bank.service.UserService;

/**
 * 用户业务实现
 *
 * @author YiJie  2017/6/15
 **/
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private AccountDAO accountDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void register(String name, String password) throws UserException {
        //TODO 输入参数合法性不应该在业务里判断
        if (name == null || "".equals(name)) {//TODO 可以不判断null值情况，name和password由Scanner.next()读入，不可能为null
            throw new UserException("用户名不能为空！");
        }
        if (password == null || "".equals(password)) {
            throw new UserException("密码不能为空！");
        }

//        checkUser(name);//TODO 外部已做判断
        //TODO 事务处理
        User user = new User(name, password);
        if (userDAO.queryUser(user) != null) {
            throw new UserException("该用户已存在！");
        }
        userDAO.addUser(user);
        //在userDao里面操作关联属性
        Account account = new Account(user, new Float(0));
        accountDAO.addAccount(account);
    }

    public void login(String name, String password) throws UserException {
        //TODO 输入参数合法性不应该在业务里判断
        if (name == null || "".equals(name)) {
            throw new UserException("用户名不能为空！");
        }
        if (password == null || "".equals(password)) {
            throw new UserException("密码不能为空！");
        }

        checkUser(name);//TODO 不判断空串情况，文件是系统写入的，断言不会出现空串情况
        String pwd = userDAO.queryUser(new User(name)).getPassword();
        if (!pwd.equals(password)) {
            throw new UserException("密码错误！");
        }
    }

    public void logout() {
//        System.exit(0);
    }

    /**
     * 校验用户是否存在
     *
     * @param userName
     * @throws UserException
     */
    public void checkUser(String userName) throws UserException {
        if (userDAO.queryUser(new User(userName)) == null) {
            throw new UserException("不存在用户" + userName + "！");
        }
    }

}
