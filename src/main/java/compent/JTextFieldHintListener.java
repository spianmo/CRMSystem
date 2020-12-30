package compent;

/**
 * @ClassName JTextFieldHintListener
 * @Description TODO
 * @Author Finger
 * @Date 11/23/2020
 **/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class JTextFieldHintListener implements FocusListener {
    private String hintText;
    private JTextField textField;

    public JTextFieldHintListener(JTextField jTextField, String hintText, int FontStyle) {
        this.textField = jTextField;
        this.hintText = hintText;
        jTextField.setText(hintText);
        jTextField.setFont(new Font("Microsoft YaHei", FontStyle, 16));
        jTextField.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        //获取焦点时，清空提示内容
        String temp = textField.getText();
        if (temp.equals(hintText)) {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        //失去焦点时，没有输入内容，显示提示内容
        String temp = textField.getText();
        if (temp.equals("")) {
            textField.setForeground(Color.GRAY);
            textField.setText(hintText);
        }

    }

}