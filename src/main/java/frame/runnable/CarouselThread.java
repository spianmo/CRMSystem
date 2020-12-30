package frame.runnable;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @ClassName CarouselThread
 * @Description TODO
 * @Author Finger
 * @Date 12/7/2020
 **/
public class CarouselThread implements Runnable {

    private final String[] imgs = {"http://pic.netbian.com/uploads/allimg/201028/223855-1603895935efcd.jpg", "http://pic.netbian.com/uploads/allimg/201111/235645-1605110205bc7e.jpg", "http://pic.netbian.com/uploads/allimg/200102/193708-15779650287a6a.jpg", "http://pic.netbian.com/uploads/allimg/200214/112541-158165074199b4.jpg", "http://pic.netbian.com/uploads/allimg/200627/004311-15931897916db2.jpg", "http://pic.netbian.com/uploads/allimg/200623/235026-15929274260112.jpg"};

    private JLabel bgLabel;

    public void setBgLabel(JLabel bgLabel) {
        this.bgLabel = bgLabel;
    }

    @Override
    public void run() {
        int index = 0;
        int len = imgs.length;
        while (true) {
            try {
                URL url = new URL(imgs[index]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }
                byte[] bytes = baos.toByteArray();
                is.read();
                ImageIcon icon = new ImageIcon(bytes);
                this.bgLabel.setIcon(icon);
                Thread.sleep(3800);
                baos.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            index++;
            if (len == index) {
                index = 0;
            }
        }
    }
}
