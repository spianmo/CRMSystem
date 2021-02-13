package frame;

import com.sun.awt.AWTUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import compent.RoundBorder;
import entity.Customer;
import factory.ServiceFactory;
import lombok.SneakyThrows;

public class CustomerAddPanel extends JFrame {
    private JPanel contentPane;
    private JButton 确定Button;
    private JButton 取消Button;
    private JTextField 手机号码textField;
    private JTextField 账号IDtextField;
    private JTextField 姓名textField;
    private JTextField 客户住址extField;
    private JTextField 所属员工textField;
    private JTextField 信用级别textField;

    int xOld = 0;
    int yOld = 0;


    public CustomerAddPanel(CustomerAddPanel.Callback callback) {
        setContentPane(contentPane);
        手机号码textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        账号IDtextField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        姓名textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        客户住址extField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        所属员工textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        信用级别textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        setUndecorated(true);
        setLocation(700, 320);
        setSize(new Dimension(600, 300));
        RoundRectangle2D shape = new RoundRectangle2D.Double(0.0D, 0.0D, getWidth() + 2, getHeight() + 2, 34.0D, 34.0D);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(254, 254, 254), 1, true));
        AWTUtilities.setWindowShape(this, shape);
        //setModal(true);
        getRootPane().setDefaultButton(确定Button);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();//记录鼠标按下时的坐标
                yOld = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                CustomerAddPanel.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });

        确定Button.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                if (客户住址extField.getText().trim().isEmpty()) {
                    MaterialOptionPane.showMessageDialog("客户住在不能为空。");
                    return;
                }
                if (账号IDtextField.getText().trim().isEmpty()) {
                    MaterialOptionPane.showMessageDialog("账号ID不能为空。");
                    return;
                }
                if (姓名textField.getText().trim().isEmpty()) {
                    MaterialOptionPane.showMessageDialog("姓名不能为空。");
                    return;
                }
                if (手机号码textField.getText().trim().isEmpty()) {
                    MaterialOptionPane.showMessageDialog("手机号码不能为空。");
                    return;
                }
                Customer customer = Customer.builder().customerId(Integer.parseInt(账号IDtextField.getText().trim())).name(姓名textField.getText().trim()).employeeId(Integer.parseInt(所属员工textField.getText().trim())).address(客户住址extField.getText().trim()).phone(手机号码textField.getText().trim()).credit(Integer.parseInt(信用级别textField.getText().trim())).userId(Integer.parseInt(账号IDtextField.getText().trim())).build();
                if (ServiceFactory.getCustomerServiceInstance().insertCustomer(customer)) {
                    dispose();
                    MaterialOptionPane.showMessageDialog("添加成功");
                    callback.onFinish();
                } else {
                    MaterialOptionPane.showMessageDialog("添加失败");
                }
            }
        });
        取消Button.addActionListener(e -> dispose());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public interface Callback {
        void onFinish();
    }


    public static void main(String[] args) {
        CustomerAddPanel frame = new CustomerAddPanel(new CustomerAddPanel.Callback() {
            @Override
            public void onFinish() {

            }
        });
        frame.setVisible(true);
    }
}
