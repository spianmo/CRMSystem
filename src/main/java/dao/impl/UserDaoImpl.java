package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;
import entity.User;
import utils.JDBCUtil;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByAccount(String account) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,account);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String userName = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            short accountLevel = resultSet.getShort("account_level");
            user = new User();
            user.setId(id);
            user.setUsername(userName);
            user.setPassword(password);
            user.setEmail(email);
            if (accountLevel == 1){
                user.setAccountLevel(User.Level.EMPLOYEE);
            }else if (accountLevel == 2){
                user.setAccountLevel(User.Level.ADMIN);
            }else if (accountLevel == 0){
                user.setAccountLevel(User.Level.CUSTOMER);
            }
        }
        resultSet.close();
        preparedStatement.close();
        jdbcUtil.closeConnection();
        return user;
    }
}
