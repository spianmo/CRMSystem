package service;

import bean.onLoginCallback;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public interface UserService {
    void login(String account, String password, onLoginCallback callback);
}
