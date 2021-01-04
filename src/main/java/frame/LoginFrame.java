package frame;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import bean.onLoginCallback;
import compent.MaterialOptionPane;
import compent.NoBorderJFrame;
import compent.RoundBorder;
import entity.User;
import factory.ServiceFactory;

/**
 * @ClassName LoginFrame
 * @Description TODO
 * @Author Finger
 * @Date 11/9/2020
 **/
public class LoginFrame extends NoBorderJFrame {
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel logoPanel;
    private JPanel formPanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginBtn;


    public LoginFrame() {
        initComponent();
        loginBtn.addActionListener(e -> {
            String account = accountField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (account.isEmpty() || password.isEmpty()) {
                MaterialOptionPane.showMessageDialog("账号密码不能为空");
                return;
            }
            ServiceFactory.getUserServiceInstance().login(account, password, new onLoginCallback() {
                @Override
                public void onLoginSuccess(String msg, User user) {
                    LoginFrame.this.dispose();
                    LoginFrame.this.toBack();
                    MaterialOptionPane.showMessageDialog(msg);
                    switch (user.getAccountLevel()) {
                        case ADMIN:
                            new AdminMainFrame("管理员:" + user.getUsername());
                            break;
                        case EMPLOYEE:
                            new EmployeeMainFrame("员工:" + user.getUsername(),ServiceFactory.getEmployeeServiceInstance().selectByUserId(String.valueOf(user.getId())));
                            break;
                        case CUSTOMER:
                            new CustomerMainFrame("客户:" + user.getUsername(),ServiceFactory.getCustomerServiceInstance().selectByUserId(String.valueOf(user.getId())));
                            break;
                        default:
                            MaterialOptionPane.showMessageDialog("账号身份异常！");
                            break;
                    }
                }

                @Override
                public void onLoginFaild(String msg) {
                    MaterialOptionPane.showMessageDialog(msg);
                    accountField.setText("");
                    passwordField.setText("");
                }
            });
        });

    }

    public static void main(String[] args) {
        new LoginFrame();
    }

    public void initComponent() {
        setLocation(500, 240);
        setTitle("CRMLoginFrame");
        setContentPane(mainPanel);
        setSize(1000, 600);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        accountField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        passwordField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        loginBtn.setBorder(new RoundBorder(Color.LIGHT_GRAY));
    }
}
