package frame.runnable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;

/**
 * @ClassName TimeThread
 * @Description TODO
 * @Author Finger
 * @Date 12/7/2020
 **/
public class TimeThread implements Runnable {
    private JLabel timeLabel;

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    @Override
    public void run() {
        while (true) {
            timeLabel.setText(DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm:ss ").format(LocalDateTime.now()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
