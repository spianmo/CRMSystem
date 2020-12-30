package bean;

import entity.User;

public interface onLoginCallback {
    void onLoginSuccess(String msg,User user);
    void onLoginFaild(String msg);
}
