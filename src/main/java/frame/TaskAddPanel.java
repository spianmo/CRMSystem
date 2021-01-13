package frame;

import com.sun.awt.AWTUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import compent.JTextFieldHintListener;
import compent.RoundBorder;
import entity.Task;
import factory.ServiceFactory;
import lombok.SneakyThrows;

public class TaskAddPanel extends JFrame {
    private JPanel contentPane;
    private JButton 确定Button;
    private JButton 取消Button;
    private JTextField 计划内容textField;
    private JTextField 客户数textField;
    private JTextField 计划时间textField;
    private JTextField 员工编号textField;

    int xOld = 0;
    int yOld = 0;

    public TaskAddPanel(int employeeId, Callback callback) {
        setContentPane(contentPane);
        计划内容textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        客户数textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        计划时间textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        员工编号textField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        计划时间textField.addFocusListener(new JTextFieldHintListener(计划时间textField, " yyyy-MM-dd", Font.ITALIC));

        员工编号textField.setText(String.valueOf(employeeId));
        DateChooser dateChooser = DateChooser.getInstance();
        dateChooser.showDate = 计划时间textField;
        计划时间textField.setEditable(false);
        计划时间textField.setBounds(10, 10, 200, 30);
        计划时间textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                dateChooser.showPanel(计划时间textField);
            }
        });
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
                TaskAddPanel.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });

        确定Button.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                if (客户数textField.getText().trim().isEmpty()) {
                    MaterialOptionPane.showMessageDialog("预期客户数不能为空。");
                    return;
                }
                if (计划内容textField.getText().trim().isEmpty()) {
                    MaterialOptionPane.showMessageDialog("计划内容不能为空。");
                    return;
                }
                if (计划时间textField.getText().trim().isEmpty()) {
                    MaterialOptionPane.showMessageDialog("计划时间不能为空。");
                    return;
                }
                try {
                    Date.valueOf(计划时间textField.getText().trim());
                } catch (Exception exception) {
                    MaterialOptionPane.showMessageDialog("计划时间不合法。");
                    return;
                }
                Task task = Task.builder().taskDesc(计划内容textField.getText()).taskTime(Date.valueOf(计划时间textField.getText().trim())).customerNum(Integer.parseInt(客户数textField.getText())).employeeId(employeeId).build();
                if (ServiceFactory.getTaskServiceInstance().insertTask(task)) {
                    dispose();
                    MaterialOptionPane.showMessageDialog("计划添加成功");
                    callback.onFinish();
                } else {
                    MaterialOptionPane.showMessageDialog("计划添加失败");
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
        TaskAddPanel frame = new TaskAddPanel(1, new Callback() {
            @Override
            public void onFinish() {
                System.out.println("============>onFinish");
            }
        });
        frame.setVisible(true);
    }
}
