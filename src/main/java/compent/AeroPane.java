package compent;

import java.awt.AlphaComposite;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class AeroPane extends JPanel {

    private BufferedImage contentBuffer;
    private BufferedImage aeroBuffer;
    private Graphics2D contentGraphics;
    private Graphics2D aeroGraphics;
    private int radius = 20;

    private StackBlurFilter stackBlurFilter;

    public AeroPane() {
        // TODO Auto-generated constructor stub
        stackBlurFilter = new StackBlurFilter(radius);
        installRepaintManager();
    }

    public static BufferedImage changeImageWidth(BufferedImage image, int width) {
        float ratio = (float) image.getWidth() / (float) image.getHeight();
        int height = (int) (width / ratio);

        BufferedImage temp = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = temp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
        g2.dispose();

        return temp;
    }

    @Override
    public void paint(Graphics g) {

        paintContent(g);
        System.out.println("draw");
        paintAero(g);
    }

    private void paintAero(Graphics g) {

        Rectangle aeroRect = AeroDemo.topPanel.getBounds();

        if (aeroBuffer == null || aeroBuffer.getWidth() != getWidth() || aeroBuffer.getHeight() != getHeight()) {

            if (aeroBuffer != null) {

                aeroBuffer.flush();

                aeroGraphics.dispose();
            }

            aeroBuffer = new BufferedImage(aeroRect.width + radius, aeroRect.height + radius, BufferedImage.TRANSLUCENT);

        }

        aeroGraphics = aeroBuffer.createGraphics();

        Graphics2D g2 = aeroGraphics;

        g2.setComposite(AlphaComposite.Src);

        g2.drawImage(contentBuffer, 0, 0, (aeroBuffer.getWidth()), (aeroBuffer.getHeight()), aeroRect.x, aeroRect.y, (aeroRect.x + aeroRect.width), (aeroRect.y + aeroRect.height), null);

        aeroBuffer = stackBlurFilter.filter(aeroBuffer, null);

        g2 = (Graphics2D) g.create();

        g2.drawImage(aeroBuffer, aeroRect.x, aeroRect.y, (aeroRect.x + aeroRect.width), (aeroRect.y + aeroRect.height), radius / 2, radius / 2, (radius / 2 + aeroRect.width), (radius / 2 + aeroRect.height), null);

        g2.dispose();
    }

    private void paintContent(Graphics g) {

        if (contentBuffer == null || contentBuffer.getWidth() != getWidth() || contentBuffer.getHeight() != getHeight()) {

            if (contentBuffer != null) {

                contentBuffer.flush();

                contentGraphics.dispose();

            }

            contentBuffer = GraphicsUtilities.createCompatibleTranslucentImage(getWidth(), getHeight());

            contentGraphics = contentBuffer.createGraphics();
        }

        Graphics2D g2 = contentGraphics;

        g2.clipRect(getX(), getY(), getWidth(), getHeight());

        g2.setComposite(AlphaComposite.Clear);

        Rectangle clip = g.getClipBounds();

        g2.fillRect(clip.x, clip.y, clip.width, clip.height);

        g2.setComposite(AlphaComposite.SrcOver);

        g2.setColor(g.getColor());

        g2.setFont(g.getFont());

        super.paint(g2);

        g.drawImage(contentBuffer, 0, 0, null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.dispose();
    }

    private void installRepaintManager() {
        ReflectionRepaintManager manager = new ReflectionRepaintManager();
        RepaintManager.setCurrentManager(manager);
    }

    private class ReflectionRepaintManager extends RepaintManager {
        @Override
        public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {
            Rectangle dirtyRegion = getDirtyRegion(c);

            int lastDeltaX = c.getX();
            int lastDeltaY = c.getY();

            Container parent = c.getParent();

            Container layeredPane = null;

            while (parent instanceof JComponent) {
                if (!parent.isVisible()) {
                    return;
                }

                if (parent instanceof AeroPane) {
                    x += lastDeltaX;
                    y += lastDeltaY;

                    lastDeltaX = lastDeltaY = 0;

                    c = (JComponent) parent;
                }

                if (parent instanceof JLayeredPane) {

                    layeredPane = parent;
                }

                lastDeltaX += parent.getX();
                lastDeltaY += parent.getY();

                parent = parent.getParent();
            }

            super.addDirtyRegion(c, x, y, w, h);

            if (layeredPane != null) {

                super.addDirtyRegion((JComponent) layeredPane, 0, 0, layeredPane.getWidth(), layeredPane.getHeight());
            }
        }
    }

}
