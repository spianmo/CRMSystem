package dao;

import java.sql.SQLException;

import entity.User;

public interface UserDao {
    User findUserByAccount(String account) throws SQLException;
}
