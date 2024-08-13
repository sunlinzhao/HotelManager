/*
 * Created by JFormDesigner on Fri Feb 24 14:41:13 CST 2023
 */

package com.coder.hotel.ui.roomInfo;

import com.coder.hotel.entity.RoomInfo;
import com.coder.hotel.entity.RoomType;
import com.coder.hotel.service.RoomInfoService;
import com.coder.hotel.service.RoomTypeService;
import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;

/**
 * @author Administrator
 */
public class RoomInfoUpdateUi extends JFrame {
    private RoomInfoUpdateUi() {
        initComponents();
    }
    private static final RoomInfoUpdateUi UI=new RoomInfoUpdateUi();
    public static RoomInfoUpdateUi getInstance(){
        return UI;
    }
    private void getTypeInfo(ItemEvent e) {
        // TODO add your code here
        String item = type.getSelectedItem().toString();
        int index = type.getSelectedIndex();
        if (index != 0) {
            roomType = typeService.selectType(item);
            price.setText(roomType.getPrice().toString());
            deposit.setText(roomType.getDeposit().toString());
        }
    }

    private void getTel(FocusEvent e) {
        // TODO add your code here
        tel.setText(roomnum.getText());
    }

    private void submit(ActionEvent e) {
        //数据校验处理
        String levelVal = level.getText();
        int index = type.getSelectedIndex();
        String roomnumVal = roomnum.getText();
        String priceVal = price.getText();
        String depositVal = deposit.getText();
        String telVal = tel.getText();
        //执行到这里，就说明所有的校验全部通过
        //RoomInfo info = new RoomInfo();
        info.setLevel(Integer.parseInt(levelVal));
        info.setTid(roomType.getId());
        info.setRoomnum(roomnumVal);
        info.setPrice(Integer.parseInt(priceVal.replace(",","")));
        info.setDeposit(Integer.parseInt(depositVal.replace(",","")));
        info.setTel(telVal);
        if (s1.isSelected())
            info.setStatus("空");
        if (s2.isSelected())
            info.setStatus("有客");
        if (s3.isSelected())
            info.setStatus("空脏");
        info.setRemark(remark.getText());
        //info.setType();
        RoomInfoService service = new RoomInfoService();
        int i = service.update(info);
        if (i > 0) {
            DefaultTableModel model= (DefaultTableModel) table.getModel();
            //重新查询一下数据库
            Object[][] objects = service.selectList(1);
            String[] column=new String[]{"id","楼层","房型id","房间类型","房间号","单价","押金","电话","状态","备注"};
            model.setDataVector(objects,column);
            /*Page pageInfo=service.getPage(1);
            total.setText(pageInfo.getTotal().toString());
            //当前页
            pages.setText(pageInfo.getPages().toString());*/
            table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
            table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
            table.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(0);
            table.updateUI();
            JOptionPane.showMessageDialog(this, "更新成功");
            goBack(e);
        }
    }

    private void reset(ActionEvent e) {
        // TODO add your code here
        init();

    }
    private  void init(){
        //回显数据
        level.setText(info.getLevel().toString());
        roomnum.setText(info.getRoomnum());
        price.setText(info.getPrice().toString());
        deposit.setText(info.getDeposit().toString());
        tel.setText(info.getTel());
        remark.setText(info.getRemark());
        String status = info.getStatus();
        //处理回显状态
        switch (status){
            case "空":
                s1.setSelected(true);break;
            case "有客":
                s2.setSelected(true);break;
            case "空脏":
                s3.setSelected(true);break;
        }
        //处理回显房间类型
        Integer tid = info.getTid();
        RoomType roomType=typeService.selectId(tid);
        type.setSelectedItem(roomType.getType());
    }

    private void goBack(ActionEvent e) {
        // TODO add your code here
        UiUtil.indent(UI,RoomInfoUi.getInstance());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        level = new JFormattedTextField(NumberFormat.getInstance());
        label3 = new JLabel();
        typeService=new RoomTypeService();
        Object[] types = typeService.getTypes();
        type = new JComboBox(types);
        label4 = new JLabel();
        roomnum = new JTextField();
        label5 = new JLabel();
        price = new JFormattedTextField(NumberFormat.getInstance());
        label6 = new JLabel();
        deposit = new JFormattedTextField(NumberFormat.getInstance());
        label7 = new JLabel();
        tel = new JTextField();
        label8 = new JLabel();
        group=new ButtonGroup();
        s1 = new JRadioButton();
        s2 = new JRadioButton();
        s3 = new JRadioButton();
        group.add(s1);
        group.add(s2);
        group.add(s3);
        label9 = new JLabel();
        scrollPane1 = new JScrollPane();
        remark = new JTextArea();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label10 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/\u9152\u5e97.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u95f4\u7ba1\u7406");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u95f4\u7ba1\u7406");
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
            label2.setText("\u6240\u5728\u697c\u5c42");
            label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label2);
            label2.setBounds(55, 15, 70, 35);
            panel1.add(level);
            level.setBounds(125, 17, 170, 30);

            //---- label3 ----
            label3.setText("\u623f\u95f4\u7c7b\u578b");
            label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label3);
            label3.setBounds(345, 15, 70, 35);

            //---- type ----
            type.addItemListener(e -> getTypeInfo(e));
            panel1.add(type);
            type.setBounds(410, 17, 170, 30);

            //---- label4 ----
            label4.setText("\u623f\u95f4\u53f7\u7801");
            label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label4);
            label4.setBounds(55, 65, 70, 35);

            //---- roomnum ----
            roomnum.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    getTel(e);
                }
            });
            panel1.add(roomnum);
            roomnum.setBounds(125, 67, 170, 30);

            //---- label5 ----
            label5.setText("\u623f\u95f4\u4ef7\u683c");
            label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label5);
            label5.setBounds(345, 65, 70, 35);
            panel1.add(price);
            price.setBounds(410, 67, 170, 30);

            //---- label6 ----
            label6.setText("\u623f\u95f4\u62bc\u91d1");
            label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label6);
            label6.setBounds(55, 118, 70, 35);
            panel1.add(deposit);
            deposit.setBounds(125, 120, 170, 30);

            //---- label7 ----
            label7.setText("\u623f\u95f4\u7535\u8bdd");
            label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label7);
            label7.setBounds(345, 118, 70, 35);
            panel1.add(tel);
            tel.setBounds(410, 120, 170, 30);

            //---- label8 ----
            label8.setText("\u623f\u95f4\u72b6\u6001");
            label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label8);
            label8.setBounds(55, 168, 70, 35);

            //---- s1 ----
            s1.setText("\u7a7a");
            s1.setBackground(Color.white);
            s1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(s1);
            s1.setBounds(new Rectangle(new Point(130, 175), s1.getPreferredSize()));

            //---- s2 ----
            s2.setText("\u6709\u5ba2");
            s2.setBackground(Color.white);
            s2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(s2);
            s2.setBounds(new Rectangle(new Point(180, 175), s2.getPreferredSize()));

            //---- s3 ----
            s3.setText("\u7a7a\u810f");
            s3.setBackground(Color.white);
            s3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(s3);
            s3.setBounds(new Rectangle(new Point(240, 175), s3.getPreferredSize()));

            //---- label9 ----
            label9.setText("\u5907\u6ce8");
            label9.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label9);
            label9.setBounds(55, 210, 70, 35);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(remark);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(130, 220, 445, 65);

            //---- button1 ----
            button1.setText("\u786e\u5b9a");
            button1.addActionListener(e -> submit(e));
            panel1.add(button1);
            button1.setBounds(new Rectangle(new Point(340, 300), button1.getPreferredSize()));

            //---- button2 ----
            button2.setText("\u91cd\u7f6e");
            button2.addActionListener(e -> reset(e));
            panel1.add(button2);
            button2.setBounds(new Rectangle(new Point(430, 300), button2.getPreferredSize()));

            //---- button3 ----
            button3.setText("\u8fd4\u56de");
            button3.addActionListener(e -> goBack(e));
            panel1.add(button3);
            button3.setBounds(new Rectangle(new Point(520, 300), button3.getPreferredSize()));
        }
        contentPane.add(panel1);
        panel1.setBounds(90, 85, 630, 350);

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
    private JFormattedTextField level;
    private JLabel label3;
    private JComboBox type;
    private JLabel label4;
    private JTextField roomnum;
    private JLabel label5;
    private JFormattedTextField price;
    private JLabel label6;
    private JFormattedTextField deposit;
    private JLabel label7;
    private JTextField tel;
    private JLabel label8;
    private JRadioButton s1;
    private JRadioButton s2;
    private JRadioButton s3;
    private JLabel label9;
    private JScrollPane scrollPane1;
    private JTextArea remark;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label10;
    private RoomInfo info;
    private JTable table;
    private ButtonGroup group;
    private RoomTypeService typeService;
    private RoomType roomType;

    public RoomInfo getInfo() {
        return info;
    }

    public void setInfo(RoomInfo info) {
        this.info = info;
        //初始数据
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
