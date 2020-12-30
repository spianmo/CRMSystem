package compent;

import java.awt.Adjustable;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * @ClassName MyJScrollBar
 * @Description TODO
 * @Author Finger
 * @Date 11/19/2020
 **/
public class MyJScrollBar extends BasicScrollBarUI {

    // 手柄宽度
    private static final int thumbWidth = 8;

    //手柄透明度
    private static final float opaque = 0.8f;
    // 滑道颜色
    private static final Color backColorFrom = new Color(255, 255, 255);
    private static final Color backColorTo = new Color(255, 255, 255);
    // 手柄边框颜色
    private Color thumbColor = new Color(61, 90, 254);
    // 手柄颜色
    private Color thumbColorFrom = new Color(61, 90, 254);
    private Color thumbColorTo = new Color(61, 90, 254);

    public MyJScrollBar() {

    }

    public MyJScrollBar(Color color) {
        this.thumbColor = color;
        this.thumbColorFrom = color;
        this.thumbColorTo = color;
    }

    @Override
    protected void configureScrollBarColors() {

        // 把手

        // thumbColor = Color.GRAY;

        // thumbHighlightColor = Color.BLUE;

        // thumbDarkShadowColor = Color.BLACK;

        // thumbLightShadowColor = Color.YELLOW;

        // 滑道

        //        trackColor = Color.black;

        setThumbBounds(0, 0, 3, 10);

        // trackHighlightColor = Color.GREEN;

    }

    /**
     * 设置滚动条的宽度
     */

    @Override
    public Dimension getPreferredSize(final JComponent c) {

        // TODO Auto-generated method stub

        //        c.setPreferredSize(new Dimension(thumbWidth, 0));
        c.setPreferredSize(new Dimension(thumbWidth, thumbWidth));

        return super.getPreferredSize(c);

    }

    // 重绘滑块的滑动区域背景

    @Override
    public void paintTrack(final Graphics g, final JComponent c, final Rectangle trackBounds) {

        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gp = null;

        //判断滚动条是垂直的 还是水平的

        if (this.scrollbar.getOrientation() == Adjustable.VERTICAL) {
            //设置画笔
            // 颜色渐变
            gp = new GradientPaint(0, 0, backColorFrom, 0, trackBounds.height, backColorTo);

        }

        if (this.scrollbar.getOrientation() == Adjustable.HORIZONTAL) {
            gp = new GradientPaint(0, 0, backColorFrom, trackBounds.width, 0, backColorTo);
        }

        g2.setPaint(gp);

        //填充Track

        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

        //绘制Track的边框
        /*       g2.setColor(new Color(175, 155, 95));
         g2.drawRect(trackBounds.x, trackBounds.y, trackBounds.width - 1,
                trackBounds.height - 1);
                */

        if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT) {
            this.paintDecreaseHighlight(g);
        }

        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT) {
            this.paintIncreaseHighlight(g);
        }

    }

    @Override
    protected void paintThumb(final Graphics g, final JComponent c, final Rectangle thumbBounds) {

        // 把绘制区的x，y点坐标定义为坐标系的原点

        // 这句一定一定要加上啊，不然拖动就失效了

        g.translate(thumbBounds.x, thumbBounds.y);

        // 设置把手颜色

        //        g.setColor(new Color(230, 230, 250));
        g.setColor(thumbColor);

        // 画一个圆角矩形

        // 这里面前四个参数就不多讲了，坐标和宽高

        // 后两个参数需要注意一下，是用来控制角落的圆角弧度

        //         g.drawRoundRect(0, 0, 5, thumbBounds.height - 1, 5, 5);
        g.drawRoundRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1, 5, 5);

        // 消除锯齿

        Graphics2D g2 = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.addRenderingHints(rh);

        // 半透明
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));

        // 设置填充颜色，这里设置了渐变，由下往上
        g2.setPaint(new GradientPaint(c.getWidth() / 2, 1, thumbColorFrom, c.getWidth() / 2, c.getHeight(), thumbColorTo));

        // 填充圆角矩形
        //        g2.fillRoundRect(0, 0, thumbWidth, thumbBounds.height - 1, 5, 5);
        g2.fillRoundRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1, 5, 5);

    }

    /**
     * 创建滚动条上方的按钮
     */

    @Override
    protected JButton createIncreaseButton(final int orientation) {

        JButton button = new JButton();

        button.setBorderPainted(true);

        button.setContentAreaFilled(true);

        // 设置为null, 禁止上方按钮
        button.setBorder(null);

        return button;

    }

    /**
     * 创建滚动条下方的按钮
     */

    @Override
    protected JButton createDecreaseButton(final int orientation) {

        JButton button = new JButton();

        button.setBorderPainted(true);

        button.setContentAreaFilled(true);

        button.setFocusable(false);

        // 设置为null, 禁止上方按钮
        button.setBorder(null);

        return button;

    }
}