package dao;

import entity.User;

/**
 * 登录用户实体的Dao数据访问层
 *
 * @author Finger
 */
public interface UserDao {
    /**
     * 根据账号查询用户实体
     *
     * @param account 账号
     * @return 返回根据账号查询的用户实体
     */
    User findUserByAccount(String account);
}
