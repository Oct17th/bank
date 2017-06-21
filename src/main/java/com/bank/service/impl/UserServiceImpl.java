package com.bank.service.impl;

import com.bank.exception.PropertiesNotFoundException;
import com.bank.exception.UserException;
import com.bank.service.UserService;
import com.bank.util.PropertiesUtil;

import java.io.File;

/**
 * 用户业务实现
 *
 * @author YiJie  2017/6/15
 **/
public class UserServiceImpl implements UserService {
    String path;
    PropertiesUtil propertiesUtil;

    private UserServiceImpl() throws PropertiesNotFoundException {
        path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
        propertiesUtil = new PropertiesUtil(path);
    }

    public UserServiceImpl(PropertiesUtil propertiesUtil) {
        this.propertiesUtil = propertiesUtil;
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
        propertiesUtil.set(name, password);
        propertiesUtil.set(name + ".account", String.valueOf(0));
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
        String pwd = propertiesUtil.get(name);
        if (!pwd.equals(password)) {
            throw new UserException("密码错误！");
        }
    }

    public void exitSystem() {
        System.exit(0);
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

}
