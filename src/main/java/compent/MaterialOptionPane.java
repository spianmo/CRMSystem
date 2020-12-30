package compent;


import frame.MaterialDialog;

/**
 * @ClassName MaterialOptionPane
 * @Description TODO
 * @Author Finger
 * @Date 11/20/2020
 **/
public class MaterialOptionPane {
    public static void showMessageDialog(String message) {
        MaterialDialog frame = new MaterialDialog("<html>" + message + "</html>");
        frame.setVisible(true);
    }

    public static void showMessageDialog(String message, DialogActionListener actionListener) {
        MaterialDialog frame = new MaterialDialog("<html>" + message + "</html>", actionListener);
        frame.setVisible(true);
    }

}
