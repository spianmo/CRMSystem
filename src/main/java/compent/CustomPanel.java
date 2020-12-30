package compent;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @ClassName CustomPanel
 * @Description TODO
 * @Author Finger
 * @Date 11/23/2020
 **/
public class CustomPanel extends JPanel {
    private final String path;

    public CustomPanel(String path) {
        this.path = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image bg = null;
        try {
            InputStream inputStream;
            bg = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

    }
}
