package service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

import bean.onLoginCallback;
import dao.UserDao;
import entity.User;
import factory.DaoFactory;
import service.UserService;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoFactory.getUserDaoInstance();
    @Override
    public void login(String account, String password, onLoginCallback callback) {
        User user = null;
        try{
            user = userDao.findUserByAccount(account);
        }catch (SQLException e){
            callback.onLoginFaild("根据账号查找管理员信息出现SQL异常");
            System.err.println("根据账号查找管理员信息出现SQL异常");
        }
        if (user != null){
            if (DigestUtils.md5Hex(password).equals(user.getPassword())){
                callback.onLoginSuccess("登陆成功",user);
            }else{
                callback.onLoginFaild("密码错误");
            }
        }else{
            callback.onLoginFaild("账号不存在");
        }
    }
}
