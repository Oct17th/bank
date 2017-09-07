package com.bank.dao;

/**
 * DAO工厂
 * 负责创建UserDAO和AccountDAO
 *
 * @author YiJie 2017/9/5.
 */
public class DAOFactory {
    /**
     * DAO实现类包名
     */
    private static final String implPackageName = "com.bank.dao.impl.";//TODO 这里把包路径写死了
    /**
     * UserDAO实现类名称
     */
    private String userDaoName;
    /**
     * AccountDAO实现类名称
     */
    private String accountDaoName;

    /**
     * 初始化DAO工厂
     *
     * @param type DAO实现类型
     */
    public DAOFactory(DAOTYPE type) {
        this.userDaoName = this.implPackageName + type + "UserDAOImpl";
        this.accountDaoName = this.implPackageName + type + "AccountDAOImpl";
    }

    /**
     * 创建UserDAO
     *
     * @return
     */
    public UserDAO createUserDAO() {
        try {
            return (UserDAO) Class.forName(userDaoName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建AccountDAO
     *
     * @return
     */
    public AccountDAO createAccountDAO() {
        try {
            return (AccountDAO) Class.forName(accountDaoName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
