/*
 * Created by JFormDesigner on Sun Mar 12 14:38:42 CST 2023
 */

package com.coder.test;

import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author Administrator
 */
public class TimeTestUI extends JFrame {
    public TimeTestUI() {
        initComponents();
    }

    public static void main(String[] args) {
        UiUtil.indent(null,new TimeTestUI());
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        contentPane.add(label1);
        label1.setBounds(25, 60, 350, 60);
        contentPane.add(textField1);
        textField1.setBounds(35, 165, 285, 45);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
