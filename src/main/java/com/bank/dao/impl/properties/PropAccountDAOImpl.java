package com.bank.dao.impl.properties;

import com.bank.dao.AccountDAO;
import com.bank.dao.util.PropertiesUtil;
import com.bank.exception.PropertiesNotFoundException;
import com.bank.po.Account;
import com.bank.po.User;

/**
 * 利用properties文件存储实现用户接口
 *
 * @author YiJie 2017/9/4.
 */
public class PropAccountDAOImpl implements AccountDAO {
    private PropertiesUtil propertiesUtil;

    public PropAccountDAOImpl() {
        try {
            propertiesUtil = new PropertiesUtil();
        } catch (PropertiesNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int addAccount(Account account) {
        if (propertiesUtil.get(account.getName()) != null) {
            return 0;
        }
        propertiesUtil.set(account.getName() + ".account", String.valueOf(account.getBalance()));
        return 1;
    }

    public int updateAccount(Account account) {
        if (propertiesUtil.get(account.getName()) != null && propertiesUtil.get(account.getName() + ".account") != null) {
            propertiesUtil.set(account.getName() + ".account", String.valueOf(account.getBalance()));
            return 1;
        }
        return 0;
    }

    public int deleteAccount(Account account) {
        if (propertiesUtil.get(account.getName()) != null && propertiesUtil.get(account.getName() + ".account") != null) {
            propertiesUtil.remove(account.getName() + ".account");
            return 1;
        }
        return 0;
    }

    public Account queryAccount(Account account) {
        if (propertiesUtil.get(account.getName()) != null && propertiesUtil.get(account.getName() + ".account") != null) {
            Account result = new Account();
            User user = new User(account.getName(), propertiesUtil.get(account.getName()));
            account.setUser(user);
            account.setBalance(Float.parseFloat(propertiesUtil.get(account.getName() + ".account")));
            return account;
        }
        return null;
    }
}
