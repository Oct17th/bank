package com.bank.dao.impl.mysql.mybatis;

import com.bank.dao.AccountDAO;
import com.bank.po.Account;

/**
 * @author YiJie 2017/9/27.
 */
public class MyBatisAccountDAOImpl extends MybatisSupport implements AccountDAO {

    @Override
    public int addAccount(Account account) {
        if (queryAccount(account) != null) return 0;
        return accountDAO.addAccount(account);
    }

    @Override
    public int updateAccount(Account account) {
        if (queryAccount(account) == null) return 0;
        return accountDAO.updateAccount(account);
    }

    @Override
    public int deleteAccount(Account account) {
        if (queryAccount(account) == null) return 0;
        return accountDAO.deleteAccount(account);
    }

    @Override
    public Account queryAccount(Account account) {
        return accountDAO.queryAccount(account);//TODO 是否匹配密码，在xml文件中用if标签做
    }
}
