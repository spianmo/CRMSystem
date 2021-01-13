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
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import compent.MaterialOptionPane;
import entity.Feedback;
import factory.ServiceFactory;

public class MaterialFeedbackDialog extends JDialog {
    int xOld = 0;
    int yOld = 0;
    private JPanel contentPane;
    private JButton 提交反馈Button;
    private JButton 取消Button;
    private JTextArea feedbackTextArea;

    public MaterialFeedbackDialog(int customerId,int produceId,Callback callback) {
        setContentPane(contentPane);
        feedbackTextArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setUndecorated(true);
        setLocation(940, 420);
        setSize(new Dimension(340, 180));
        RoundRectangle2D shape = new RoundRectangle2D.Double(0.0D, 0.0D, getWidth() + 2, getHeight() + 2, 26.0D, 26.0D);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(254, 254, 254), 1, true));
        AWTUtilities.setWindowShape(this, shape);
        setModal(true);
        getRootPane().setDefaultButton(提交反馈Button);
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
                MaterialFeedbackDialog.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });

        提交反馈Button.addActionListener(e -> {
            dispose();
            if (feedbackTextArea.getText().isEmpty()){
                MaterialOptionPane.showMessageDialog("反馈内容不能为空！");
                return;
            }
            Feedback feedback = Feedback.builder()
                    .customerId(customerId)
                    .produceId(produceId)
                    .dealStatus(Feedback.Status.TO_BE_SOLVED)
                    .content(feedbackTextArea.getText())
                    .createTime(new Date(System.currentTimeMillis()))
                    .build();
            if (ServiceFactory.getFeedbackServiceInstance().insertFeedback(feedback)){
                callback.onSubmitSuccess();
            }else{
                callback.onSubmitFailure();
            }
        });
        取消Button.addActionListener(e -> {
            dispose();
            callback.onSubmitCanel();
        });

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
        void onSubmitSuccess();

        void onSubmitFailure();

        void onSubmitCanel();
    }

}
