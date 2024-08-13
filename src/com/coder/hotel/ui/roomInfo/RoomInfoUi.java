/*
 * Created by JFormDesigner on Sun Feb 19 14:33:16 CST 2023
 */

package com.coder.hotel.ui.roomInfo;

import com.coder.hotel.entity.RoomInfo;
import com.coder.hotel.service.RoomInfoService;
import com.coder.hotel.ui.MainUi;
import com.coder.hotel.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 */
public class RoomInfoUi extends JFrame {
    private RoomInfoUi() {
        initComponents();
    }

    private static final RoomInfoUi UI = new RoomInfoUi();

    public static RoomInfoUi getInstance() {
        return UI;
    }

    /*class CustomModel extends DefaultTableModel {
        public CustomModel(Object[][] data, Object[] column) {
            super(data, column);
        }
        //禁止jtable可编辑
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }*/
    private void page(Page pageInfo, int currentPage, RoomInfo info) {
        column = new String[]{"id", "楼层", "房型id", "房间类型", "房间号", "单价", "押金", "电话", "状态", "备注"};
        data = service.selectExample(info, currentPage);
        model.setDataVector(data, column);
        page.setText(pageInfo.getPage().toString());
        table1.updateUI();
    }

    //处理分页按钮事件
    private void first(ActionEvent e) {
        Page pageInfo = service.getPage(info, 1);
        page(pageInfo, 1, info);
        doHide();
    }

    private void previous(ActionEvent e) {
        Page pageInfo = service.getPage(info, 1);
        String c = page.getText();//获取当前页码
        if (!c.equals("1")) {
            pageInfo.setPage(Long.parseLong(c) - 1);
            long p = pageInfo.getPage();
            page(pageInfo, (int) p, info);
        }
        doHide();
    }

    private void next(ActionEvent e) {
        Page pageInfo = service.getPage(info, 1);
        //获取最后一页
        Long lastPage = pageInfo.getPages();
        String c = page.getText();//获取当前页码
        Long currentPage = Long.parseLong(c);
        if (currentPage < lastPage) {
            pageInfo.setPage(currentPage + 1);
            long p = pageInfo.getPage();
            page(pageInfo, (int) p, info);
        }
        doHide();
    }

    private void last(ActionEvent e) {
        Page pageInfo = service.getPage(info, 1);
        pageInfo.setPage(pageInfo.getPages());
        long p = pageInfo.getPage();
        page(pageInfo, (int) p, info);
        doHide();
    }

    private void doHide() {
        table1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        table1.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        table1.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(0);
    }

    private void add(ActionEvent actionEvent) {
        //界面跳转
        RoomInfoAddUi infoAddUi = RoomInfoAddUi.getInstance();
        infoAddUi.setTable(table1);
        infoAddUi.setTotal(total);
        infoAddUi.setPages(pages);
        UiUtil.indent(UI, infoAddUi);
    }

    private void delete(ActionEvent e) {
        //获取用户选择的表的行数
        TableUtil.delete(table1, model, service);
        Page pageInfo = service.getPage(info, 1);
        total.setText(pageInfo.getTotal().toString());
        pages.setText(pageInfo.getPages().toString());
        first(e);
    }

    private void goBack(ActionEvent e) {
        first(e);
        UiUtil.indent(UI, MainUi.getFrame());
    }

    private void update(ActionEvent e) {
        int rowCount = table1.getSelectedRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(table1, "请至少选择一行",
                    "提示信息", JOptionPane.WARNING_MESSAGE);
        } else if (rowCount > 1) {
            JOptionPane.showMessageDialog(table1, "只能修改一行数据",
                    "提示信息", JOptionPane.WARNING_MESSAGE);
        } else {
            int row = table1.getSelectedRow();
            Object id = table1.getValueAt(row, 0);
            RoomInfoService service = new RoomInfoService();
            RoomInfo info = service.selectId(id);
            System.out.println(info);
            RoomInfoUpdateUi infoUpdateUi = RoomInfoUpdateUi.getInstance();
            infoUpdateUi.setInfo(info);
            infoUpdateUi.setTable(table1);
            UiUtil.indent(UI, infoUpdateUi);
        }

    }

    private void query(ActionEvent e) {
        String type = textField1.getText();
        String level = StringUtil.isEmpty(textField2.getText()) ? "0" : textField2.getText();
        String roomnum = textField3.getText();
        //将数据封装成一个RoomInfo对象，传给service层，去做查询处理
        info = new RoomInfo();
        info.setType(type);
        info.setLevel(Integer.valueOf(level));
        info.setRoomnum(roomnum);
        Page pageInfo = service.getPage(info, 1);
        page(pageInfo, 1, info);
        total.setText(pageInfo.getTotal().toString());
        pages.setText(pageInfo.getPages().toString());
        doHide();
    }

    private void initComponents() {
        info = new RoomInfo();
        info.setType("");
        info.setLevel(0);
        info.setRoomnum("");
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        addBtn = new JButton();
        deleteBtn = new JButton();
        updateBtn = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        queryBtn = new JButton();
        label4 = new JLabel();
        textField2 = new JTextField();
        label5 = new JLabel();
        textField3 = new JTextField();
        backBtn = new JButton();
        scrollPane1 = new JScrollPane();
        //初始化jtable数据
        column = new String[]{"id", "楼层", "房型id", "房间类型", "房间号", "单价", "押金", "电话", "状态", "备注"};
        service = new RoomInfoService();
        data = service.selectList(1);
        model = new CustomModel(data, column);
        table1 = new JTable(model);
        TableStyle.setStyle(table1);
        //删除表格列
        //table1.removeColumn(table1.getColumnModel().getColumn(2));
        //隐藏表格列
        table1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        table1.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        table1.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(0);

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        label6 = new JLabel();
        total = new JLabel();
        label8 = new JLabel();
        page = new JLabel();
        label10 = new JLabel();
        pages = new JLabel();
        label3 = new JLabel();

        //获取页码相关信息
        Page pageInfo = service.getPage(info, 1);
        total.setText(pageInfo.getTotal().toString());
        //当前页
        page.setText(pageInfo.getPage().toString());
        pages.setText(pageInfo.getPages().toString());

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/img/\u9152\u5e97.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u95f4\u7ba1\u7406");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u623f\u95f4\u7ba1\u7406");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(0, 0, 740, 55);

        //---- addBtn ----
        addBtn.setText("\u65b0\u589e");
        addBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u589e\u52a0.png")));
        contentPane.add(addBtn);
        addBtn.setBounds(55, 140, 95, 40);
        addBtn.addActionListener(this::add);

        //---- deleteBtn ----
        deleteBtn.setText("\u5220\u9664");
        deleteBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u5220\u9664.png")));
        deleteBtn.addActionListener(e -> delete(e));
        contentPane.add(deleteBtn);
        deleteBtn.setBounds(160, 140, 95, 40);

        //---- updateBtn ----
        updateBtn.setText("\u4fee\u6539");
        updateBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u4fee\u6539.png")));
        contentPane.add(updateBtn);
        updateBtn.setBounds(265, 140, 95, 40);
        updateBtn.addActionListener(this::update);
        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u623f\u95f4\u7c7b\u578b");
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(20, 22), label2.getPreferredSize()));
            panel1.add(textField1);
            textField1.setBounds(75, 15, 100, 30);

            //---- queryBtn ----
            queryBtn.setText("\u67e5\u8be2");
            queryBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u67e5\u8be2.png")));
            panel1.add(queryBtn);
            queryBtn.setBounds(510, 10, 95, 40);
            queryBtn.addActionListener(this::query);
            //---- label4 ----
            label4.setText("\u623f\u95f4\u697c\u5c42");
            panel1.add(label4);
            label4.setBounds(185, 22, 48, 17);
            panel1.add(textField2);
            textField2.setBounds(235, 15, 100, 30);

            //---- label5 ----
            label5.setText("\u623f\u95f4\u53f7\u7801");
            panel1.add(label5);
            label5.setBounds(345, 22, 48, 17);
            panel1.add(textField3);
            textField3.setBounds(400, 15, 100, 30);
        }
        contentPane.add(panel1);
        panel1.setBounds(55, 65, 685, 60);

        //---- backBtn ----
        backBtn.setText("\u8fd4\u56de");
        backBtn.addActionListener(e -> {
            goBack(e);
            goBack(e);
        });
        contentPane.add(backBtn);
        backBtn.setBounds(685, 465, 78, 30);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(55, 195, 680, 245);

        //---- button1 ----
        button1.setText("\u9996\u9875");
        contentPane.add(button1);
        button1.setBounds(365, 465, 78, 30);

        //---- button2 ----
        button2.setText("\u4e0a\u4e00\u9875");
        contentPane.add(button2);
        button2.setBounds(445, 465, 78, 30);

        //---- button3 ----
        button3.setText("\u4e0b\u4e00\u9875");
        contentPane.add(button3);
        button3.setBounds(525, 465, 78, 30);

        //---- button4 ----
        button4.setText("\u5c3e\u9875");
        contentPane.add(button4);
        button4.setBounds(605, 465, 78, 30);

        //调用分页处理按钮
        button1.addActionListener(this::first);
        button2.addActionListener(this::previous);
        button3.addActionListener(this::next);
        button4.addActionListener(this::last);

        //---- label6 ----
        label6.setText("\u603b\u8bb0\u5f55\uff1a");
        label6.setForeground(Color.white);
        label6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(label6);
        label6.setBounds(60, 470, 55, label6.getPreferredSize().height);

        //---- total ----

        total.setForeground(Color.white);
        total.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(total);
        total.setBounds(110, 470, 40, total.getPreferredSize().height);

        //---- label8 ----
        label8.setText("\u5f53\u524d\u9875\uff1a");
        label8.setForeground(Color.white);
        label8.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(150, 470), label8.getPreferredSize()));

        //---- page ----

        page.setForeground(Color.white);
        page.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(page);
        page.setBounds(205, 470, 22, page.getPreferredSize().height);

        //---- label10 ----
        label10.setText("\u603b\u9875\u6570\uff1a");
        label10.setForeground(Color.white);
        label10.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(label10);
        label10.setBounds(new Rectangle(new Point(225, 470), label10.getPreferredSize()));

        //---- pages ----

        pages.setForeground(Color.white);
        pages.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(pages);
        pages.setBounds(285, 470, 29, pages.getPreferredSize().height);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/img/bg.jpg")));
        contentPane.add(label3);
        label3.setBounds(0, 0, label3.getPreferredSize().width, 530);

        contentPane.setPreferredSize(new Dimension(800, 530));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JPanel panel1;
    private JLabel label2;
    private JTextField textField1;
    private JButton queryBtn;
    private JLabel label4;
    private JTextField textField2;
    private JLabel label5;
    private JTextField textField3;
    private JButton backBtn;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label6;
    private JLabel total;
    private JLabel label8;
    private JLabel page;
    private JLabel label10;
    private JLabel pages;
    private JLabel label3;
    private String[] column;
    private Object[][] data;
    private RoomInfoService service;
    private CustomModel model;
    private RoomInfo info;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
