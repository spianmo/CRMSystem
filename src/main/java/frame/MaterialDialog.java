package frame;

import com.sun.awt.AWTUtilities;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import compent.DialogActionListener;

public class MaterialDialog extends JDialog {
    int xOld = 0;
    int yOld = 0;
    private JPanel contentPane;
    private JButton 确定Button;
    private JButton 取消Button;
    private JLabel messageField;

    public MaterialDialog(String message, DialogActionListener actionListener) {
        setContentPane(contentPane);
        messageField.setText(message);
        setUndecorated(true);
        setLocation(940, 420);
        setSize(new Dimension(300, 160));
        RoundRectangle2D shape = new RoundRectangle2D.Double(0.0D, 0.0D, getWidth() + 2, getHeight() + 2, 26.0D, 26.0D);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(254, 254, 254), 1, true));
        AWTUtilities.setWindowShape(this, shape);
        setModal(true);
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
                MaterialDialog.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });

        确定Button.addActionListener(actionListener.onProcess(MaterialDialog.this));
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

    public MaterialDialog(String message) {
        setContentPane(contentPane);
        messageField.setText(message);
        setUndecorated(true);
        setLocation(940, 420);
        setSize(new Dimension(300, 160));
        RoundRectangle2D shape = new RoundRectangle2D.Double(0.0D, 0.0D, getWidth() + 2, getHeight() + 2, 26.0D, 26.0D);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(254, 254, 254), 1, true));
        AWTUtilities.setWindowShape(this, shape);
        setModal(true);
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
                MaterialDialog.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });

        确定Button.addActionListener(e -> dispose());
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


    public static void main(String[] args) {
        MaterialDialog frame = new MaterialDialog("<html>当前网络环境：弱网 图片加载失败</html>");
        frame.setVisible(true);
    }
}
