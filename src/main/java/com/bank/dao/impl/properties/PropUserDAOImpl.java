package com.bank.dao.impl.properties;

import com.bank.dao.UserDAO;
import com.bank.dao.impl.properties.util.PropertiesUtil;
import com.bank.exception.PropertiesNotFoundException;
import com.bank.po.User;

/**
 * 利用properties文件存储实现用户接口
 *
 * @author YiJie 2017/9/4.
 */
public class PropUserDAOImpl implements UserDAO {
    private String path;
    private PropertiesUtil propertiesUtil;

    public PropUserDAOImpl() {
        //TODO 在工厂类里解决这个路径定义的问题
//        path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
        try {
//            propertiesUtil = new PropertiesUtil(path);
            propertiesUtil = new PropertiesUtil();
        } catch (PropertiesNotFoundException e) {//TODO 抛出DAO异常
            //不会找不到文件的叭~
            e.printStackTrace();
        }
    }

    @Override
    public int addUser(User user) {
        if (queryUser(user) != null) {
            return 0;//该用户已存在
        }
        propertiesUtil.set(user.getName(), user.getPassword());
//        propertiesUtil.set(user.getName() + ".account", "0.0");//TODO 此操作不符合高内聚低耦合原则
        return 1;
    }

    @Override
    public int updateUser(User old, User user) {
        if (queryUser(old) == null || queryUser(user) != null || old.equals(user)) {
            propertiesUtil.remove(old.getName());
            propertiesUtil.set(user.getName(), user.getPassword());
            return 1;
        }
        return 0;//旧用户不存在，新用户名已存在，旧用户等于新用户
    }

    @Override
    public int deleteUser(User user) {
        if (queryUser(user) != null) {
            propertiesUtil.remove(user.getName());
            return 1;
        }
        return 0;
    }

    @Override
    public User queryUser(User user) {
        String password = propertiesUtil.get(user.getName());
        if (password != null) {
            User result = new User(user.getName(), password);
            return result;
        }
        return null;
    }
}
