package compent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @ClassName TransparentBackground
 * @Description TODO
 * @Author Finger
 * @Date 11/20/2020
 **/
public class TransparentBackground extends JComponent {
    private JFrame frame;
    private Image background;

    public TransparentBackground(JFrame frame) {
        this.frame = frame;
        updateBackground();
    }

    /**
     * @todo 获取屏幕快照后立即更新窗口背景
     */
    public void updateBackground() {
        try {
            Robot rbt = new Robot();
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension dim = tk.getScreenSize();
            background = rbt.createScreenCapture(new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight()));
        } catch (Exception ex) {
            //p(ex.toString( ));
// 此方法没有申明过，因为无法得知上下文。因为不影响执行效果，先注释掉它
            ex.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Point pos = this.getLocationOnScreen();
        Point offset = new Point(-pos.x, -pos.y);
        g.drawImage(background, offset.x, offset.y, null);
    }
}
