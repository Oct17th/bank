package com.bank.dao;

import com.bank.po.Account;

/**
 * AccountDao接口
 *
 * @author YiJie 2017/9/4.
 */
public interface AccountDAO {
    /**
     * 添加账户数据
     *
     * @param account
     * @return 影响记录数
     */
    public int addAccount(Account account);

    /**
     * 更新账户数据
     *
     * @param account 新账户记录
     * @return 影响记录数
     */
    public int updateAccount(Account account);

    /**
     * 删除账户记录
     *
     * @param account 只需要填充用户唯一标示即可
     * @return 影响记录数
     */
    public int deleteAccount(Account account);

    /**
     * 查询账户记录
     *
     * @param account 只需要填充用户唯一标示即可
     * @return 返回封装好的po对象
     */
    public Account queryAccount(Account account);
}
