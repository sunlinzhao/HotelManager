/*
 * Created by JFormDesigner on Sat Feb 11 16:13:18 CST 2023
 */

package com.coder.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Administrator
 */
public class MainUi extends JFrame {
    public MainUi() {
        initComponents();
    }

    public static void main(String[] args) {
        MainUi mainUi=new MainUi();
        mainUi.setVisible(true);//设置可显示
    }
    //点击按钮操作
    private void login(ActionEvent e) {
        System.out.println("hello,点击了按钮");
    }
    //重置按钮操作
    private void doReset(ActionEvent e) {
        name.setText("");
        pwd.setText("");
    }

    private void nameFocusLost(FocusEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        title = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        name = new JTextField();
        label3 = new JLabel();
        pwd = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf");
        setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
        setForeground(new Color(0x333333));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf");
        title.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        title.setForeground(new Color(0x3333ff));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setToolTipText("\u63d0\u793a");
        contentPane.add(title);
        title.setBounds(260, 20, 260, 60);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u5e10\u6237");
            panel1.add(label2);
            label2.setBounds(80, 35, 24, 17);

            //---- name ----
            name.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    nameFocusLost(e);
                }
            });
            panel1.add(name);
            name.setBounds(135, 25, 220, 30);

            //---- label3 ----
            label3.setText("\u5bc6\u7801");
            panel1.add(label3);
            label3.setBounds(80, 70, 24, 17);
            panel1.add(pwd);
            pwd.setBounds(140, 70, 220, 30);

            //---- button1 ----
            button1.setText("\u767b\u5f55");
            button1.setIcon(new ImageIcon(getClass().getResource("/img/icon.png")));
            button1.addActionListener(e -> login(e));
            panel1.add(button1);
            button1.setBounds(135, 170, 108, 50);

            //---- button2 ----
            button2.setText("\u91cd\u7f6e");
            button2.addActionListener(e -> doReset(e));
            panel1.add(button2);
            button2.setBounds(250, 170, 108, 50);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(145, 100, 495, 255);

        contentPane.setPreferredSize(new Dimension(815, 470));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel title;
    private JPanel panel1;
    private JLabel label2;
    private JTextField name;
    private JLabel label3;
    private JPasswordField pwd;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
