package compent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

/**
 * @ClassName JFrameNoBorder
 * @Description TODO
 * @Author Finger
 * @Date 11/19/2020
 **/
public class NoBorderJFrame extends JFrame {
    int xOld = 0;
    int yOld = 0;

    public NoBorderJFrame() {

        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();
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
                NoBorderJFrame.this.setLocation(xx, yy);
            }
        });
        this.setUndecorated(true);
    }

}
