package com.bank.dao.impl.mysql.hibernate;

import com.bank.dao.UserDAO;
import com.bank.po.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


/**
 * Hibernate操作User对象
 *
 * @author YiJie 2017/9/22.
 */
public class HibernateUserDAOImpl implements UserDAO {
    private static SessionFactory sessionFactory;

    private static Configuration cfg;

    static {
        //加载hibernate配置
        cfg = new Configuration().configure();
        //构建session工厂
        sessionFactory = cfg.buildSessionFactory();
    }


    public int addUser(User user) {
        //用户已存在则不能添加
        if (queryUser(user) != null) return 0;
        boolean flag = true;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            flag = false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return flag ? 1 : 0;
    }

    public int updateUser(User old, User user) {
        //若新旧用户非同一个或旧用户不存在
        if ((!old.getName().equals(user.getName())) || queryUser(old) == null) return 0;
        boolean flag = true;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            flag = false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return flag ? 1 : 0;
    }

    public int deleteUser(User user) {
        //若用户不存在直接返回0
        if (queryUser(user) == null) return 0;
        boolean flag = true;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            flag = false;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return flag ? 1 : 0;
    }

    public User queryUser(User user) {
        List<User> result = null;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            //若用户密码为空，则直接匹配用户名
            if (user.getPassword() == null || user.getPassword().equals("")) {
                result = session.createQuery("from User u where u.name = ?")
                        .setParameter(0, user.getName())
                        .list();
            } else {
                result = session.createQuery("from User u where u.name = ? and u.password = ?")
                        .setParameter(0, user.getName())
                        .setParameter(1, user.getPassword())
                        .list();
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return result.size() == 0 ? null : result.get(0);//无论是匹配name还是匹配name和password，都只会得到一个结果
    }
}
