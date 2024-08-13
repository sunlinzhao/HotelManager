/*
 * Created by JFormDesigner on Tue Feb 14 13:27:46 CST 2023
 */

package com.coder.hotel.ui.roomType;

import com.coder.hotel.entity.RoomType;
import com.coder.hotel.service.RoomTypeService;
import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

/**
 * @author Administrator
 */
public class RoomTypeAddUi extends JFrame {
    private RoomTypeAddUi() {
        initComponents();
    }
    private static final RoomTypeAddUi UI=new RoomTypeAddUi();
    public static RoomTypeAddUi getInstance(){
        return UI;
    }

    private void save(ActionEvent e) {
        // TODO add your code here
        String typeValue=type.getText();
        if (typeValue.equals("")){
            JOptionPane.showMessageDialog(this,"请填写房型");
            return;
        }
        String priceValue=price.getText();
        if (priceValue.equals("")){
            JOptionPane.showMessageDialog(this,"请填写单价");
            return;
        }
        String depositValue=deposit.getText();
        if (depositValue.equals("")){
            JOptionPane.showMessageDialog(this,"请填写押金");
            return;
        }
        String bednumValue=bednum.getText();
        if (bednumValue.equals("")){
            JOptionPane.showMessageDialog(this,"请填写床位数");
            return;
        }
        String remarkValue=remark.getText();
        RoomType roomType=new RoomType();
        roomType.setType(typeValue);
        roomType.setPrice(Integer.parseInt(priceValue));
        roomType.setDeposit(Integer.parseInt(depositValue));
        roomType.setBednum(Integer.parseInt(bednumValue));
        roomType.setRemark(remarkValue);
        RoomTypeService service=new RoomTypeService();
        int i=service.save(roomType);
        if (i>0){
            //将RoomTypeUi传过来的JTable进行刷新
            DefaultTableModel model= (DefaultTableModel) table.getModel();
            //重新查询一下数据库
            Object[][] objects = service.selectList();
            model.setDataVector(objects,new String[]{"id", "房型", "单价", "押金", "床位数", "备注"});
            table.updateUI();
            JOptionPane.showMessageDialog(this,"保存成功",
                    "提示消息",JOptionPane.INFORMATION_MESSAGE);
            goBack(e);
        }

    }

    private void reset(ActionEvent e) {
        // TODO add your code here
        type.setText("");
        price.setText("");
        deposit.setText("");
        bednum.setText("");
        remark.setText("");
    }

    private void goBack(ActionEvent e) {
        reset(e);
        UiUtil.indent(UI,RoomTypeUi.getInstance());
    }
    private void initComponents() {
        setResizable(false);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        type = new JTextField();
        price = new JFormattedTextField(NumberFormat.getInstance());
        deposit = new JFormattedTextField(NumberFormat.getInstance());
        bednum = new JFormattedTextField(NumberFormat.getInstance());
        scrollPane1 = new JScrollPane();
        remark = new JTextArea();
        okBtn = new JButton();
        resetBtn = new JButton();
        backBtn = new JButton();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label7 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/酒店.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u95f4\u7c7b\u578b_\u65b0\u589e");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u578b\u7ba1\u7406");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(25, 10, 740, 55);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u623f\u578b");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2);
            label2.setBounds(45, 35, 65, 30);

            //---- label3 ----
            label3.setText("\u5355\u4ef7");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label3);
            label3.setBounds(45, 80, 65, 30);

            //---- label4 ----
            label4.setText("\u62bc\u91d1");
            label4.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label4);
            label4.setBounds(45, 125, 65, 30);

            //---- label5 ----
            label5.setText("\u5e8a\u4f4d\u6570");
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label5);
            label5.setBounds(45, 170, 65, 30);

            //---- label6 ----
            label6.setText("\u5907\u6ce8");
            label6.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label6);
            label6.setBounds(45, 220, 65, 30);
            panel1.add(type);
            type.setBounds(120, 35, 285, 30);
            panel1.add(price);
            price.setBounds(120, 80, 285, 30);
            panel1.add(deposit);
            deposit.setBounds(120, 125, 285, 30);
            panel1.add(bednum);
            bednum.setBounds(120, 170, 285, 30);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(remark);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(120, 220, 280, 75);

            //---- okBtn ----
            okBtn.setText("\u786e\u5b9a");
            okBtn.addActionListener(e -> save(e));
            panel1.add(okBtn);
            okBtn.setBounds(new Rectangle(new Point(240, 310), okBtn.getPreferredSize()));

            //---- resetBtn ----
            resetBtn.setText("\u91cd\u7f6e");
            resetBtn.addActionListener(e -> reset(e));
            panel1.add(resetBtn);
            resetBtn.setBounds(new Rectangle(new Point(325, 310), resetBtn.getPreferredSize()));

            //---- backBtn ----
            backBtn.setText("\u8fd4\u56de");
            backBtn.addActionListener(e -> goBack(e));
            panel1.add(backBtn);
            backBtn.setBounds(new Rectangle(new Point(410, 310), backBtn.getPreferredSize()));

            //---- label8 ----
            label8.setText("*");
            label8.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
            label8.setForeground(Color.red);
            panel1.add(label8);
            label8.setBounds(new Rectangle(new Point(410, 40), label8.getPreferredSize()));

            //---- label9 ----
            label9.setText("*");
            label9.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
            label9.setForeground(Color.red);
            panel1.add(label9);
            label9.setBounds(new Rectangle(new Point(410, 85), label9.getPreferredSize()));

            //---- label10 ----
            label10.setText("*");
            label10.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
            label10.setForeground(Color.red);
            panel1.add(label10);
            label10.setBounds(new Rectangle(new Point(410, 130), label10.getPreferredSize()));

            //---- label11 ----
            label11.setText("*");
            label11.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
            label11.setForeground(Color.red);
            panel1.add(label11);
            label11.setBounds(new Rectangle(new Point(410, 175), label11.getPreferredSize()));
        }
        contentPane.add(panel1);
        panel1.setBounds(145, 90, 505, 355);

        //---- label7 ----
        label7.setIcon(new ImageIcon(getClass().getResource("/img/bg.jpg")));
        contentPane.add(label7);
        label7.setBounds(0, 0, 800, 530);

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
    private JLabel label6;
    private JTextField type;
    private JFormattedTextField price;
    private JFormattedTextField deposit;
    private JFormattedTextField bednum;
    private JScrollPane scrollPane1;
    private JTextArea remark;
    private JButton okBtn;
    private JButton resetBtn;
    private JButton backBtn;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label7;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
