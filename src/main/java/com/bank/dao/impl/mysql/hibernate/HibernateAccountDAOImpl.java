package com.bank.dao.impl.mysql.hibernate;

import com.bank.dao.AccountDAO;
import com.bank.po.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * @author YiJie 2017/9/27.
 */
public class HibernateAccountDAOImpl implements AccountDAO {
    private static SessionFactory sessionFactory;

    private static Configuration cfg;

    static {
        //加载hibernate配置
        cfg = new Configuration().configure();
        //构建session工厂
        sessionFactory = cfg.buildSessionFactory();
    }

    @Override
    public int addAccount(Account account) {
        //账户已存在则不能添加
        if (queryAccount(account) != null) return 0;
        boolean flag = true;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            session.save(account);
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

    @Override
    public int updateAccount(Account account) {
        //若账户不存在
        if (queryAccount(account) == null) return 0;
        boolean flag = true;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            session.update(account);
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

    @Override
    public int deleteAccount(Account account) {
        //若账户不存在直接返回0
        if (queryAccount(account) == null) return 0;
        boolean flag = true;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(account);
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

    @Override
    public Account queryAccount(Account account) {
        List<Account> result = null;
        //获取session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        try {
            //若用户密码为空，则直接匹配用户名
            if (account.getUser().getPassword() == null || account.getUser().getPassword().equals("")) {
                result = session.createQuery("from Account a where a.name = ?")
                        .setParameter(0, account.getUser().getName())
                        .list();
            } else {
                result = session.createQuery("from User u,Account a where u.name = a.name and u.name = ? and u.password = ?")
                        .setParameter(0, account.getUser().getName())
                        .setParameter(1, account.getUser().getPassword())
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
