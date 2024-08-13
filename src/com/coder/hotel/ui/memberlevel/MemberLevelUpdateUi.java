/*
 * Created by JFormDesigner on Sun Mar 05 12:26:31 CST 2023
 */

package com.coder.hotel.ui.memberlevel;

import com.coder.hotel.entity.MemberLevel;
import com.coder.hotel.service.MemberLevelService;
import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 */
public class MemberLevelUpdateUi extends JFrame {
    private  MemberLevelUpdateUi() {
        initComponents();
    }
    private static final MemberLevelUpdateUi UI=new MemberLevelUpdateUi();
    public static MemberLevelUpdateUi getInstance(){
        return UI;
    }
    private void submit(ActionEvent e) {
        String level=levelVal.getText();
        String low=lowVal.getText();
        String high=highVal.getText();
        String discount=discountVal.getText();
        memberLevel.setLevel(level);
        memberLevel.setLow(Integer.valueOf(low));
        memberLevel.setHigh(Integer.valueOf(high));
        memberLevel.setDiscount(Double.valueOf(discount));
        MemberLevelService service=new MemberLevelService();
        int i=service.update(memberLevel);
        if (i>0){
            DefaultTableModel model= (DefaultTableModel) table.getModel();
            Object[][] objects = service.selectList();
            model.setDataVector(objects,new String[]{"id", "会员等级", "最低金额", "最高金额",  "折扣"});
            JOptionPane.showMessageDialog(this,"更新成功");
            goBack(e);
        }else{
            JOptionPane.showMessageDialog(this,"更新失败");
        }
    }

    private void reset(ActionEvent e) {
        // TODO add your code here
        init();
    }

    private void goBack(ActionEvent e) {
        // TODO add your code here
        UiUtil.indent(UI, MemberLevelUi.getInstance());
    }
    private void init(){
        levelVal.setText(memberLevel.getLevel());
        lowVal.setText(memberLevel.getLow().toString());
        highVal.setText(memberLevel.getHigh().toString());
        discountVal.setText(memberLevel.getDiscount().toString());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        levelVal = new JTextField();
        lowVal = new JFormattedTextField();
        highVal = new JFormattedTextField();
        discountVal = new JFormattedTextField();
        okBtn = new JButton();
        resetBtn = new JButton();
        backBtn = new JButton();
        label10 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/img/\u9152\u5e97.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u4f1a\u5458\u7b49\u7ea7");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(0, 20, 800, 55);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u7b49\u7ea7\u540d\u79f0");
            label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label2);
            label2.setBounds(90, 40, 70, 30);

            //---- label3 ----
            label3.setText("\u6d88\u8d39\u4f4e\u503c");
            label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label3);
            label3.setBounds(90, 90, 70, 30);

            //---- label4 ----
            label4.setText("\u6d88\u8d39\u9ad8\u503c");
            label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label4);
            label4.setBounds(90, 140, 70, 30);

            //---- label5 ----
            label5.setText("\u4eab\u53d7\u6298\u6263");
            label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label5);
            label5.setBounds(90, 195, 70, 30);
            panel1.add(levelVal);
            levelVal.setBounds(180, 38, 280, 35);
            panel1.add(lowVal);
            lowVal.setBounds(180, 88, 280, 35);
            panel1.add(highVal);
            highVal.setBounds(180, 138, 280, 35);
            panel1.add(discountVal);
            discountVal.setBounds(180, 193, 280, 35);

            //---- okBtn ----
            okBtn.setText("\u786e\u5b9a");
            okBtn.addActionListener(e -> submit(e));
            panel1.add(okBtn);
            okBtn.setBounds(new Rectangle(new Point(235, 260), okBtn.getPreferredSize()));

            //---- resetBtn ----
            resetBtn.setText("\u91cd\u7f6e");
            resetBtn.addActionListener(e -> reset(e));
            panel1.add(resetBtn);
            resetBtn.setBounds(new Rectangle(new Point(310, 260), resetBtn.getPreferredSize()));

            //---- backBtn ----
            backBtn.setText("\u8fd4\u56de");
            backBtn.addActionListener(e -> goBack(e));
            panel1.add(backBtn);
            backBtn.setBounds(new Rectangle(new Point(385, 260), backBtn.getPreferredSize()));
        }
        contentPane.add(panel1);
        panel1.setBounds(110, 110, 580, 315);

        //---- label10 ----
        label10.setIcon(new ImageIcon(getClass().getResource("/img/bg.jpg")));
        contentPane.add(label10);
        label10.setBounds(0, 0, 800, 530);

        contentPane.setPreferredSize(new Dimension(800, 530));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField levelVal;
    private JFormattedTextField lowVal;
    private JFormattedTextField highVal;
    private JFormattedTextField discountVal;
    private JButton okBtn;
    private JButton resetBtn;
    private JButton backBtn;
    private JLabel label10;
    private JTable table;
    private MemberLevel memberLevel;

    public MemberLevel getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberLevel memberLevel) {
        this.memberLevel = memberLevel;
        init();
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
