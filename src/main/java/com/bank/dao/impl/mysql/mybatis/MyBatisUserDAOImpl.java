package com.bank.dao.impl.mysql.mybatis;

import com.bank.dao.UserDAO;
import com.bank.po.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YiJie 2017/9/27.
 */
public class MyBatisUserDAOImpl extends MybatisSupport implements UserDAO {

    @Override
    public int addUser(User user) {
//        if (user == null || user.getName() == null || user.getPassword() == null) return 0;
        //已该用户则不能添加
        if (queryUser(user) != null) return 0;
        return userDAO.addUser(user);
    }

    @Override
    public int updateUser(User old, User user) {
        if ((!old.getName().equals(user.getName())) || queryUser(old) == null) return 0;
        return userDAO.updateUser(old, user);
    }

    @Override
    public int deleteUser(User user) {
        if (queryUser(user) == null) return 0;
        return userDAO.deleteUser(user);
    }

    @Override
    public User queryUser(User user) {
        return userDAO.queryUser(user);//TODO 是否匹配密码，在xml文件中用if标签做
    }
}
