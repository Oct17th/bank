package com.bank.dao;

import com.bank.po.User;

/**
 * UserDao接口
 *
 * @author YiJie 2017/9/4.
 */
public interface UserDAO {
    /**
     * 添加用户数据
     *
     * @param user
     * @return 影响记录数
     */
    public int addUser(User user);

    /**
     * 更新用户数据
     *
     * @param old  旧用户记录
     * @param user 新用户记录
     * @return 影响记录数
     */
    public int updateUser(User old, User user);

    /**
     * 删除此条用户数据，账户数据也会被删除
     *
     * @param user user只需要填充name或id字段即可
     * @return 影响记录数
     */
    public int deleteUser(User user);

    /**
     * 查询用户数据
     *
     * @param user user只需要填充name或id字段即可
     * @return 返回封装好的po对象
     */
    public User queryUser(User user);

//    public List<User> queryUser(User user);
}
