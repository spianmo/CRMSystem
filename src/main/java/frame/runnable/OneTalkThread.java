package frame.runnable;


import javax.swing.JLabel;

import utils.RandomLineUtil;

/**
 * @ClassName OneTalkThread
 * @Description TODO
 * @Author Finger
 * @Date 12/7/2020
 **/
public class OneTalkThread implements Runnable {
    private JLabel oneTalkLabel;
    private RandomLineUtil oneTalk;

    public OneTalkThread() {
        oneTalk = new RandomLineUtil("hitokoto.txt");
    }

    public void setOneTalkLabel(JLabel oneTalkLabel) {
        this.oneTalkLabel = oneTalkLabel;
    }

    @Override
    public void run() {
        while (true) {
            oneTalkLabel.setText(oneTalk.getRandomLine());
            try {
                Thread.sleep(3800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

