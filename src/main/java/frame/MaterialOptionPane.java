package frame;


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

    public static void showMessageDialog(String message, MaterialDialog.Callback callback) {
        MaterialDialog frame = new MaterialDialog("<html>" + message + "</html>", callback);
        frame.setVisible(true);
    }

    public static void showFeedbackDialog(int customerId, int produceId, MaterialFeedbackDialog.Callback callback) {
        MaterialFeedbackDialog frame = new MaterialFeedbackDialog(customerId, produceId, callback);
        frame.setVisible(true);
    }

    public static void showTaskAddPanel(int employeeId, TaskAddPanel.Callback callback) {
        TaskAddPanel frame = new TaskAddPanel(employeeId, callback);
        frame.setVisible(true);
    }

}
