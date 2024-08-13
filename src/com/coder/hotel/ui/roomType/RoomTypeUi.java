/*
 * Created by JFormDesigner on Mon Feb 13 13:59:34 CST 2023
 */

package com.coder.hotel.ui.roomType;

import com.coder.hotel.entity.RoomType;
import com.coder.hotel.service.RoomTypeService;
import com.coder.hotel.ui.MainUi;
import com.coder.hotel.util.CustomModel;
import com.coder.hotel.util.TableStyle;
import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 */
public class RoomTypeUi extends JFrame {
    private RoomTypeUi() {
        initComponents();
    }
    private static final RoomTypeUi UI = new RoomTypeUi();
    public static RoomTypeUi getInstance() {
        return UI;
    }

    //修改操作
    public void update(ActionEvent e){
        //用户选择的行数
        int rowCount = table1.getSelectedRowCount();
        if (rowCount==0){
            JOptionPane.showMessageDialog(table1, "请至少选择一行",
                    "提示信息", JOptionPane.WARNING_MESSAGE);
        }else if (rowCount>1){
            JOptionPane.showMessageDialog(table1, "只能修改一行数据",
                    "提示信息", JOptionPane.WARNING_MESSAGE);
        }else {
            int row = table1.getSelectedRow();
            Object id = table1.getValueAt(row, 0);
            RoomTypeService service=new RoomTypeService();
            RoomType roomType = service.selectId(id);
            RoomTypeUpdateUi typeUpdateUi = RoomTypeUpdateUi.getInstance();
            typeUpdateUi.setRoomType(roomType);
            typeUpdateUi.setTable(table1);
            UiUtil.indent(UI, typeUpdateUi);
        }
    }
    //跳转到增加界面
    private void goAdd(ActionEvent e){
        RoomTypeAddUi typeAddUi = RoomTypeAddUi.getInstance();
        typeAddUi.setTable(table1);
        UiUtil.indent(UI,typeAddUi);
    }
    //执行查询处理
    private void query(ActionEvent e){
        String text=textField1.getText();
        if (text.equals("")){
            //查询全部数据
            objects = service.selectList();
        }else{
            //按给定的值查询
            objects=service.selectByType(text);
        }
        model.setDataVector(objects,column);
        //table1.setModel(model);
        table1.updateUI();
        textField1.setText("");
    }
    //执行删除处理
    private void delete(ActionEvent e) {
        //获取用户选择的表的行数
        int rowCount = table1.getSelectedRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(table1, "请至少选择一行",
                    "提示信息", JOptionPane.WARNING_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(table1, "确定要删除数据吗?", "提示信息",
                    JOptionPane.OK_CANCEL_OPTION);
            if (y == 0) {
                int[] selectedRows = table1.getSelectedRows();
                for (int selectedRow : selectedRows) {
                    Object id = model.getValueAt(selectedRow, 0);
                    //按id查询房间，如果没有查到，就说明这个房型下面没有房间，可以直接删除
                    //否则不能删除，可以给用户一个提示
                    service.deleteId(id);
                }
                for (int i = selectedRows.length; i > 0; i--) {
                    int x = table1.getSelectedRow();
                    model.removeRow(x);
                }
            }
        }
        table1.updateUI();//刷新界面
    }

    private void initComponents() {
        setResizable(false);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        button3 = new JButton();
        button4 = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        button6 = new JButton();
        scrollPane1 = new JScrollPane();
        //获取表格数据
         column = new String[]{"id", "房型", "单价", "押金", "床位数", "备注"};
        service = new RoomTypeService();
        objects = service.selectList();
        model = new CustomModel(objects, column);

        table1 = new JTable(model);
        TableStyle.setStyle(table1);

        button7 = new JButton();
        button1 = new JButton();
        label3 = new JLabel();
        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/img/酒店.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u578b\u7ba1\u7406");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u578b\u7ba1\u7406");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);

        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(20, 5, 740, 55);

        //---- button3 ----
        button3.setText("\u65b0\u589e");
        button3.setIcon(new ImageIcon(getClass().getResource("/img/增加.png")));
        contentPane.add(button3);
        button3.setBounds(55, 130, 95, 40);
        button3.addActionListener(this::goAdd);

        //---- button4 ----
        button4.setText("\u5220\u9664");
        button4.setIcon(new ImageIcon(getClass().getResource("/img/删除.png")));
        contentPane.add(button4);
        button4.setBounds(160, 130, 95, 40);
        button4.addActionListener(this::delete);
        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u623f\u95f4\u7c7b\u578b");
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(20, 22), label2.getPreferredSize()));
            panel1.add(textField1);
            textField1.setBounds(80, 15, 195, 30);

            //---- button6 ----
            button6.setText("\u67e5\u8be2");
            button6.setIcon(new ImageIcon(getClass().getResource("/img/查询.png")));
            panel1.add(button6);
            button6.setBounds(285, 10, 95, 40);
            button6.addActionListener(this::query);
        }
        contentPane.add(panel1);
        panel1.setBounds(55, 65, 685, 60);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(55, 175, 690, 270);

        //---- button7 ----
        button7.setText("\u4fee\u6539");
        button7.setIcon(new ImageIcon(getClass().getResource("/img/修改.png")));
        contentPane.add(button7);
        button7.setBounds(265, 130, 95, 40);
        button7.addActionListener(this::update);
        //---- button1 ----
        button1.setText("\u8fd4\u56de");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(690, 455), button1.getPreferredSize()));

        button1.addActionListener(e -> goBack(e));

        label3.setIcon(new ImageIcon(getClass().getResource("/img/bg.jpg")));
        contentPane.add(label3);
        label3.setBounds(0, 0, 800, 530);
        contentPane.setPreferredSize(new Dimension(800, 530));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void goBack(ActionEvent e) {
        UiUtil.indent(UI, MainUi.getFrame());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JButton button3;
    private JButton button4;
    private JPanel panel1;
    private JLabel label2;
    private JTextField textField1;
    private JButton button6;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button7;
    private JButton button1;
    private CustomModel model;
    private JLabel label3;
    private Object[][] objects;
    private RoomTypeService service;
    private String[] column;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
