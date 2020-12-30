package dao.impl;

import org.intellij.lang.annotations.Language;

import dao.UserDao;
import entity.User;
import utils.jdbc.BeanHandler;
import utils.jdbc.Db;

/**
 * @ClassName UserDao的Impl实现类
 * @Description UserDao的Impl实现类
 * @Author Finger
 * @Date 12/30/2020
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByAccount(String account) {
        @Language("SQL") String sql = "SELECT * FROM t_user WHERE username = ?";
        return Db.executeQuery(sql, new BeanHandler<>(User.class), account);
    }
}
