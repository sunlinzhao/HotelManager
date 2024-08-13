/*
 * Created by JFormDesigner on Sat Mar 11 13:20:28 CST 2023
 */

package com.coder.hotel.ui.goodsInfo;

import com.coder.hotel.entity.GoodsInfo;
import com.coder.hotel.entity.GoodsInfoQuery;
import com.coder.hotel.entity.GoodsType;
import com.coder.hotel.service.GoodsInfoService;
import com.coder.hotel.service.GoodsTypeService;
import com.coder.hotel.ui.MainUi;
import com.coder.hotel.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class GoodsInfoUi extends JFrame {
    //自定义控件
    private String[] column;

    private GoodsInfoService service;
    private GoodsInfoQuery info;
    private Object[][] data;
    private CustomModel model;

    private GoodsInfoUi() {
        initComponents();
    }
    private static final GoodsInfoUi UI=new GoodsInfoUi();
    public static GoodsInfoUi getInstance(){
        return UI;
    }
    private void delete(ActionEvent e) {
        // TODO add your code here
        TableUtil.delete(table1,model,service);
        init();
    }

    private void goBack(ActionEvent e) {
        // TODO add your code here
        first(e);
        UiUtil.indent(UI, MainUi.getFrame());
    }

    private void query(ActionEvent e) {
        // TODO add your code here
        info=new GoodsInfoQuery();
        info.setName(nameVal.getText());
        info.setType(typeVal.getSelectedItem().toString());
        String lowPriceText = lowPriceVal.getText();
        info.setLowPrice(Integer.valueOf(StringUtil.isEmpty(lowPriceText)?"0":lowPriceText));
        String highPriceText = hiPriceVal.getText();
        info.setHighPrice(Integer.valueOf(StringUtil.isEmpty(highPriceText)?"0":highPriceText));
        Page pageInfo=service.getPage(info,1);
        page(pageInfo,1,info);
        total.setText(pageInfo.getTotal().toString());
        pages.setText(pageInfo.getPages().toString());
    }
    private void page(Page pageInfo, int page1, GoodsInfoQuery info){
        data=service.selectExample(info,page1);
        model.setDataVector(data,column);
        page.setText(pageInfo.getPage().toString());
        table1.updateUI();
    }

    private void clear(ActionEvent e) {
        // TODO add your code here
        typeVal.setSelectedIndex(0);
        nameVal.setText("");
        lowPriceVal.setText("");
        hiPriceVal.setText("");
        init();

    }

    private void save(ActionEvent e) {
        // TODO add your code here
        GoodsInfoAddUi addUi = GoodsInfoAddUi.getInstance();
        addUi.setTable(table1);
        addUi.setTotal(total);
        addUi.setPages(pages);
        UiUtil.indent(UI,addUi);
    }

    private void update(ActionEvent e) {
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
            GoodsInfoService service=new GoodsInfoService();
            GoodsInfo goodsInfo = service.selectId(id);
            GoodsInfoUpdateUi updateUi=GoodsInfoUpdateUi.getInstance();
            updateUi.setGoodsInfo(goodsInfo);
            updateUi.setTable(table1);
            UiUtil.indent(UI,updateUi);
        }
    }

    private void first(ActionEvent e) {
        // TODO add your code here
        Page pageInfo=service.getPage(info,1);
        page(pageInfo,1,info);
    }

    private void previous(ActionEvent e) {
        // TODO add your code here
        Page pageInfo=service.getPage(info,1);
        String c=page.getText();//获取当前页码
        if (!c.equals("1")){
            pageInfo.setPage(Long.parseLong(c)-1);
            long p = pageInfo.getPage();
            page(pageInfo,(int)p,info);
        }
    }

    private void next(ActionEvent e) {
        // TODO add your code here
        Page pageInfo=service.getPage(info,1);
        //获取最后一页
        Long lastPage = pageInfo.getPages();
        String c=page.getText();//获取当前页码
        Long currentPage=Long.parseLong(c);
        if (currentPage<lastPage){
            pageInfo.setPage(currentPage+1);
            long p = pageInfo.getPage();
            page(pageInfo,(int)p,info);
        }
    }

    private void last(ActionEvent e) {
        // TODO add your code here
        Page pageInfo=service.getPage(info,1);
        pageInfo.setPage(pageInfo.getPages());
        long p = pageInfo.getPage();
        page(pageInfo,(int)p,info);
    }

    private void init(){
        column=new String[]{"id","品名","单价","数量","类别id","类别名称"};
        service=new GoodsInfoService();
        info=new GoodsInfoQuery();
        info.setName("");
        info.setType("请选择");
        info.setLowPrice(0);
        info.setHighPrice(0);
        data=service.selectExample(info,1);
        model = new CustomModel(data, column);
        table1.setModel(model);
        TableStyle.setStyle(table1);
        Page pageInfo=service.getPage(info,1);
        total.setText(pageInfo.getTotal().toString());
        page.setText(pageInfo.getPage().toString());
        pages.setText(pageInfo.getPages().toString());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        queryBtn = new JButton();
        label4 = new JLabel();
        nameVal = new JTextField();
        label5 = new JLabel();
        lowPriceVal = new JTextField();
        GoodsTypeService typeService=new GoodsTypeService();
        java.util.List<GoodsType> list = typeService.getList();
        List<String> types = list.stream().map(GoodsType::getTypeName).collect(Collectors.toList());
        types.add(0,"请选择");
        Object[] objects =types.toArray();
        typeVal = new JComboBox(objects);
        hiPriceVal = new JTextField();
        label3 = new JLabel();
        clearBtn = new JButton();
        updateBtn = new JButton();
        deleteBtn = new JButton();
        addBtn = new JButton();
        firstBtn = new JButton();
        previousBtn = new JButton();
        nextBtn = new JButton();
        lastBtn = new JButton();
        backBtn = new JButton();
        label10 = new JLabel();
        pages = new JLabel();
        page = new JLabel();
        label8 = new JLabel();
        total = new JLabel();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label7 = new JLabel();

        init();
        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/img/\u9152\u5e97.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u5546\u54c1\u7ba1\u7406");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(0, 0, 800, 55);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u5546\u54c1\u7c7b\u578b");
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(10, 22), label2.getPreferredSize()));

            //---- queryBtn ----
            queryBtn.setText("\u67e5\u8be2");
            queryBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u67e5\u8be2.png")));
            queryBtn.addActionListener(e -> query(e));
            panel1.add(queryBtn);
            queryBtn.setBounds(515, 10, 95, 40);

            //---- label4 ----
            label4.setText("\u5546\u54c1\u540d\u79f0");
            panel1.add(label4);
            label4.setBounds(170, 22, 48, 17);
            panel1.add(nameVal);
            nameVal.setBounds(225, 15, 100, 30);

            //---- label5 ----
            label5.setText("\u5546\u54c1\u4ef7\u683c");
            panel1.add(label5);
            label5.setBounds(325, 22, 48, 17);
            panel1.add(lowPriceVal);
            lowPriceVal.setBounds(375, 15, 49, 30);
            panel1.add(typeVal);
            typeVal.setBounds(65, 15, 100, typeVal.getPreferredSize().height);
            panel1.add(hiPriceVal);
            hiPriceVal.setBounds(455, 15, 49, 30);

            //---- label3 ----
            label3.setText("\u2014\u2014");
            panel1.add(label3);
            label3.setBounds(425, 22, 35, label3.getPreferredSize().height);

            //---- clearBtn ----
            clearBtn.setText("\u6e05\u7a7a");
            clearBtn.addActionListener(e -> clear(e));
            panel1.add(clearBtn);
            clearBtn.setBounds(615, 10, 95, 40);
        }
        contentPane.add(panel1);
        panel1.setBounds(40, 65, 720, 60);

        //---- updateBtn ----
        updateBtn.setText("\u4fee\u6539");
        updateBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u4fee\u6539.png")));
        updateBtn.addActionListener(e -> update(e));
        contentPane.add(updateBtn);
        updateBtn.setBounds(230, 135, 95, 40);

        //---- deleteBtn ----
        deleteBtn.setText("\u5220\u9664");
        deleteBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u5220\u9664.png")));
        deleteBtn.addActionListener(e -> delete(e));
        contentPane.add(deleteBtn);
        deleteBtn.setBounds(135, 135, 95, 40);

        //---- addBtn ----
        addBtn.setText("\u65b0\u589e");
        addBtn.setIcon(new ImageIcon(getClass().getResource("/img/\u589e\u52a0.png")));
        addBtn.addActionListener(e -> save(e));
        contentPane.add(addBtn);
        addBtn.setBounds(40, 135, 95, 40);

        //---- firstBtn ----
        firstBtn.setText("\u9996\u9875");
        firstBtn.addActionListener(e -> first(e));
        contentPane.add(firstBtn);
        firstBtn.setBounds(365, 465, 78, 30);

        //---- previousBtn ----
        previousBtn.setText("\u4e0a\u4e00\u9875");
        previousBtn.addActionListener(e -> previous(e));
        contentPane.add(previousBtn);
        previousBtn.setBounds(445, 465, 78, 30);

        //---- nextBtn ----
        nextBtn.setText("\u4e0b\u4e00\u9875");
        nextBtn.addActionListener(e -> next(e));
        contentPane.add(nextBtn);
        nextBtn.setBounds(525, 465, 78, 30);

        //---- lastBtn ----
        lastBtn.setText("\u5c3e\u9875");
        lastBtn.addActionListener(e -> last(e));
        contentPane.add(lastBtn);
        lastBtn.setBounds(605, 465, 78, 30);

        //---- backBtn ----
        backBtn.setText("\u8fd4\u56de");
        backBtn.addActionListener(e -> {
			goBack(e);
			goBack(e);
		});
        contentPane.add(backBtn);
        backBtn.setBounds(685, 465, 78, 30);

        //---- label10 ----
        label10.setText("\u603b\u9875\u6570\uff1a");
        label10.setForeground(Color.white);
        label10.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(label10);
        label10.setBounds(225, 470, 48, 17);

        //---- pages ----
        //pages.setText("20");
        pages.setForeground(Color.white);
        pages.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(pages);
        pages.setBounds(285, 470, 29, 17);

        //---- page ----
        //page.setText("1");
        page.setForeground(Color.white);
        page.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(page);
        page.setBounds(205, 470, 22, 17);

        //---- label8 ----
        label8.setText("\u5f53\u524d\u9875\uff1a");
        label8.setForeground(Color.white);
        label8.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(label8);
        label8.setBounds(150, 470, 48, 17);

        //---- total ----
        //total.setText("100");
        total.setForeground(Color.white);
        total.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(total);
        total.setBounds(110, 470, 40, 17);

        //---- label6 ----
        label6.setText("\u603b\u8bb0\u5f55\uff1a");
        label6.setForeground(Color.white);
        label6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        contentPane.add(label6);
        label6.setBounds(60, 470, 55, 17);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 180, 720, 278);

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
    private JButton queryBtn;
    private JLabel label4;
    private JTextField nameVal;
    private JLabel label5;
    private JTextField lowPriceVal;
    private JComboBox typeVal;
    private JTextField hiPriceVal;
    private JLabel label3;
    private JButton clearBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton addBtn;
    private JButton firstBtn;
    private JButton previousBtn;
    private JButton nextBtn;
    private JButton lastBtn;
    private JButton backBtn;
    private JLabel label10;
    private JLabel pages;
    private JLabel page;
    private JLabel label8;
    private JLabel total;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
