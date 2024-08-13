/*
 * Created by JFormDesigner on Sun Mar 12 13:12:52 CST 2023
 */

package com.coder.hotel.ui.goodsInfo;

import com.coder.hotel.entity.GoodsInfo;
import com.coder.hotel.entity.GoodsInfoQuery;
import com.coder.hotel.entity.GoodsType;
import com.coder.hotel.service.GoodsInfoService;
import com.coder.hotel.service.GoodsTypeService;
import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class GoodsInfoUpdateUi extends JFrame {
    private GoodsInfoUpdateUi() {
        initComponents();
    }

    private static final GoodsInfoUpdateUi UI=new GoodsInfoUpdateUi();
    public static GoodsInfoUpdateUi getInstance(){
        return UI;
    }


    private void submit(ActionEvent e) {
        // TODO add your code here
        String name=nameVal.getText();
        String price=priceVal.getText();
        String num=numVal.getText();
        String type=typeVal.getSelectedItem().toString();
        goodsInfo.setName(name);
        goodsInfo.setPrice(Integer.valueOf(price));
        goodsInfo.setNum(Integer.valueOf(num));
        goodsInfo.setType(type);
        GoodsTypeService typeService=new GoodsTypeService();
        Integer tid = typeService.getIdByType(type);
        goodsInfo.setTid(tid);
        GoodsInfoService service=new GoodsInfoService();
        int i = service.update(goodsInfo);
        if (i>0){
            DefaultTableModel model= (DefaultTableModel) table.getModel();
            //重新查询一下数据库
            GoodsInfoQuery infoQuery=new GoodsInfoQuery();
            infoQuery.setName("");
            infoQuery.setType("请选择");
            infoQuery.setLowPrice(0);
            infoQuery.setHighPrice(0);
            Object[][] objects = service.selectExample(infoQuery,1);
            String[] column=new String[]{"id","品名","单价","数量","类别id","类别名称"};
            model.setDataVector(objects,column);
            table.updateUI();
            JOptionPane.showMessageDialog(this, "修改成功");
            goBack(e);
        }
    }

    private void reset(ActionEvent e) {
        // TODO add your code here
        init();
    }

    private void goBack(ActionEvent e) {
        // TODO add your code here
        UiUtil.indent(UI,GoodsInfoUi.getInstance());
    }

    private  void init(){
        //回显数据
        nameVal.setText(goodsInfo.getName());
        priceVal.setText(goodsInfo.getPrice().toString());
        numVal.setText(goodsInfo.getNum().toString());
        typeVal.setSelectedItem(goodsInfo.getType());
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label7 = new JLabel();
        nameVal = new JTextField();
        GoodsTypeService typeService=new GoodsTypeService();
        java.util.List<GoodsType> list = typeService.getList();
        List<String> types = list.stream().map(GoodsType::getTypeName).collect(Collectors.toList());
        types.add(0,"请选择");
        Object[] objects =types.toArray();
        typeVal = new JComboBox(objects);
        priceVal = new JTextField();
        numVal = new JTextField();
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
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u5546\u54c1\u4fe1\u606f");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(0, 15, 800, 55);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u5546\u54c1\u540d\u79f0");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label2);
            label2.setBounds(75, 30, 80, 35);

            //---- label3 ----
            label3.setText("\u5546\u54c1\u5355\u4ef7");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label3);
            label3.setBounds(75, 130, 80, 35);

            //---- label4 ----
            label4.setText("\u5546\u54c1\u6570\u91cf");
            label4.setHorizontalAlignment(SwingConstants.RIGHT);
            label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label4);
            label4.setBounds(75, 180, 80, 35);

            //---- label7 ----
            label7.setText("\u5546\u54c1\u7c7b\u522b");
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label7);
            label7.setBounds(75, 80, 80, 35);
            panel1.add(nameVal);
            nameVal.setBounds(180, 30, 310, 35);
            panel1.add(typeVal);
            typeVal.setBounds(180, 80, 310, 35);

            //---- priceVal ----
            panel1.add(priceVal);
            priceVal.setBounds(180, 130, 310, 35);
            panel1.add(numVal);
            numVal.setBounds(180, 180, 310, 35);
        }
        contentPane.add(panel1);
        panel1.setBounds(105, 120, 600, 250);

        //---- okBtn ----
        okBtn.setText("\u786e\u5b9a");
        okBtn.addActionListener(e -> submit(e));
        contentPane.add(okBtn);
        okBtn.setBounds(480, 415, 78, 30);

        //---- resetBtn ----
        resetBtn.setText("\u91cd\u7f6e");
        resetBtn.addActionListener(e -> reset(e));
        contentPane.add(resetBtn);
        resetBtn.setBounds(555, 415, 78, 30);

        //---- backBtn ----
        backBtn.setText("\u8fd4\u56de");
        backBtn.addActionListener(e -> goBack(e));
        contentPane.add(backBtn);
        backBtn.setBounds(625, 415, 78, 30);

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
    private JLabel label7;
    private JTextField nameVal;
    private JComboBox typeVal;
    private JTextField priceVal;
    private JTextField numVal;
    private JButton okBtn;
    private JButton resetBtn;
    private JButton backBtn;
    private JLabel label10;
    private JTable table;
    private GoodsInfo goodsInfo;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
        init();
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
