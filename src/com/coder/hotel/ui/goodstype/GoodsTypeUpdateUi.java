/*
 * Created by JFormDesigner on Mon Mar 06 16:58:34 CST 2023
 */

package com.coder.hotel.ui.goodstype;

import com.coder.hotel.entity.GoodsType;
import com.coder.hotel.service.GoodsTypeService;
import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 */
public class GoodsTypeUpdateUi extends JFrame {
    private GoodsTypeUpdateUi() {
        initComponents();
    }
    private static final GoodsTypeUpdateUi UI=new GoodsTypeUpdateUi();
    public static GoodsTypeUpdateUi getInstance(){
        return UI;
    }
    private void submit(ActionEvent e) {
        // TODO add your code here
        String text = typeNameVal.getText();
        goodsType.setTypeName(text);
        GoodsTypeService service=new GoodsTypeService();
        int i = service.update(goodsType);
        if (i>0){
            DefaultTableModel model= (DefaultTableModel) table.getModel();
            //重新查询一下数据库
            Object[][] objects = service.selectList();
            model.setDataVector(objects,new String[]{"id", "商品类别"});
            table.updateUI();
            JOptionPane.showMessageDialog(this,"更新成功",
                    "提示消息",JOptionPane.INFORMATION_MESSAGE);
            goBack(e);
        }
    }

    private void reset(ActionEvent e) {
        // TODO add your code here
        init();
    }

    private void goBack(ActionEvent e) {
        // TODO add your code here
        UiUtil.indent(UI,GoodsTypeUi.getInstance());
    }
    private void init(){
        typeNameVal.setText(goodsType.getTypeName());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        typeNameVal = new JTextField();
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
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u5546\u54c1\u7c7b\u522b");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(0, 10, 800, 55);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u7c7b\u522b\u540d\u79f0");
            panel1.add(label2);
            label2.setBounds(85, 60, 80, 30);
            panel1.add(typeNameVal);
            typeNameVal.setBounds(165, 50, 235, 45);
        }
        contentPane.add(panel1);
        panel1.setBounds(145, 145, 495, 140);

        //---- okBtn ----
        okBtn.setText("\u786e\u5b9a");
        okBtn.addActionListener(e -> submit(e));
        contentPane.add(okBtn);
        okBtn.setBounds(420, 340, 78, 30);

        //---- resetBtn ----
        resetBtn.setText("\u91cd\u7f6e");
        resetBtn.addActionListener(e -> reset(e));
        contentPane.add(resetBtn);
        resetBtn.setBounds(490, 340, 78, 30);

        //---- backBtn ----
        backBtn.setText("\u8fd4\u56de");
        backBtn.addActionListener(e -> goBack(e));
        contentPane.add(backBtn);
        backBtn.setBounds(565, 340, 78, 30);

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
    private JTextField typeNameVal;
    private JButton okBtn;
    private JButton resetBtn;
    private JButton backBtn;
    private JLabel label10;
    private JTable table;
    private GoodsType goodsType;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
        init();
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
