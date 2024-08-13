/*
 * Created by JFormDesigner on Sun Mar 05 13:59:41 CST 2023
 */

package com.coder.hotel.ui.memberInfo;

import com.coder.hotel.entity.MemberInfo;
import com.coder.hotel.entity.MemberInfoQuery;
import com.coder.hotel.entity.MemberLevel;
import com.coder.hotel.service.MemberInfoService;
import com.coder.hotel.service.MemberLevelService;
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
public class MemberInfoUi extends JFrame {
    private MemberInfoUi() {
        initComponents();
    }
    private static final MemberInfoUi UI=new MemberInfoUi();
    public static MemberInfoUi getInstance(){
        return UI;
    }
    private void query(ActionEvent e) {
        // TODO add your code here
        info=new MemberInfoQuery();
        info.setName(nameVal.getText());
        info.setGender(genderVal.getSelectedItem().toString());
        String lowAgeText = lowAge.getText();
        info.setLowAge(Integer.valueOf(StringUtil.isEmpty(lowAgeText)?"0":lowAgeText));
        String highAgeText = highAge.getText();
        info.setHighAge(Integer.valueOf(StringUtil.isEmpty(highAgeText)?"0":highAgeText));
        info.setLevel(levelVal.getSelectedItem().toString());
        Page pageInfo=service.getPage(info,1);
        page(pageInfo,1,info);
        total.setText(pageInfo.getTotal().toString());
        pages.setText(pageInfo.getPages().toString());
    }

    private void clear(ActionEvent e) {
        // TODO add your code here
        init();
        table1.updateUI();
        nameVal.setText("");
        genderVal.setSelectedIndex(0);
        lowAge.setText("");
        highAge.setText("");
        levelVal.setSelectedIndex(0);
    }

    private void save(ActionEvent e) {
        // TODO add your code here
        MemberInfoAddUi infoAddUi = MemberInfoAddUi.getInstance();
        infoAddUi.setTable(table1);
        infoAddUi.setTotal(total);
        infoAddUi.setPages(pages);
        UiUtil.indent(UI,infoAddUi);
    }

    private void delete(ActionEvent e) {
        // TODO add your code here
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
                    service.deleteId(id);
                }
                for (int i = selectedRows.length; i > 0; i--) {
                    int x = table1.getSelectedRow();
                    model.removeRow(x);
                }
            }
        }
        //table1.updateUI();//刷新界面
        Page pageInfo=service.getPage(info,1);
        total.setText(pageInfo.getTotal().toString());
        pages.setText(pageInfo.getPages().toString());
        first(e);
    }

    private void update(ActionEvent e) {
        // TODO add your code here
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
            MemberInfoService service=new MemberInfoService();
            MemberInfo memberInfo = service.selectId(id);
            MemberInfoUpdateUi updateUi=MemberInfoUpdateUi.getInstance();
            updateUi.setMemberInfo(memberInfo);
            updateUi.setTable(table1);
            UiUtil.indent(UI,updateUi);
        }
    }
    private void page(Page pageInfo, int page, MemberInfoQuery info){
        data=service.selectExample(info,page);
        model.setDataVector(data,column);
        currentPage.setText(pageInfo.getPage().toString());
        table1.updateUI();
    }

    private void first(ActionEvent e) {
        Page pageInfo=service.getPage(info,1);
        page(pageInfo,1,info);
    }

    private void privous(ActionEvent e) {
        // TODO add your code here
        Page pageInfo=service.getPage(info,1);
        String c=currentPage.getText();//获取当前页码
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
        String c=currentPage.getText();//获取当前页码
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

    private void goBack(ActionEvent e) {
        // TODO add your code here
        first(e);
        UiUtil.indent(UI, MainUi.getFrame());
    }
    private void init(){
        column=new String[]{"id","姓名","身份证号","出生日期","年龄","性别","电话","等级id","等级"};
        service=new MemberInfoService();
        info=new MemberInfoQuery();
        info.setName("");
        info.setGender("请选择");
        info.setLowAge(0);
        info.setHighAge(0);
        info.setLevel("请选择");
        data=service.selectExample(info,1);
        model = new CustomModel(data, column);
        table1.setModel(model);
        TableStyle.setStyle(table1);
        Page pageInfo=service.getPage(info,1);
        total.setText(pageInfo.getTotal().toString());
        //当前页
        currentPage.setText(pageInfo.getPage().toString());
        pages.setText(pageInfo.getPages().toString());
        //table1.updateUI();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        nameVal = new JTextField();
        genderVal = new JComboBox(new String[]{"请选择","男","女"});

        lowAge = new JFormattedTextField();
        highAge = new JFormattedTextField();
        label6 = new JLabel();
        //查询会员等级数据
        MemberLevelService levelService=new MemberLevelService();
        List<MemberLevel> list = levelService.getList();
        List<String> memberLevels = list.stream().map(MemberLevel::getLevel).collect(Collectors.toList());
        memberLevels.add(0,"请选择");
        Object[] objects =memberLevels.toArray();
        levelVal = new JComboBox(objects);
        queryBtn = new JButton();
        clearBtn = new JButton();
        toolBar1 = new JToolBar();
        saveBtn = new JButton();
        deleteBtn = new JButton();
        updateBtn = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        toolBar2 = new JToolBar();
        firstBtn = new JButton();
        privousBtn = new JButton();
        nextBtn = new JButton();
        lastBtn = new JButton();
        backBtn = new JButton();
        label7 = new JLabel();
        total = new JLabel();
        label9 = new JLabel();
        currentPage = new JLabel();
        label11 = new JLabel();
        pages = new JLabel();
        label10 = new JLabel();

        init();


        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/img/\u9152\u5e97.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u4f1a\u5458\u4fe1\u606f");
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
            label2.setText("\u4f1a\u5458\u59d3\u540d");
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(15, 25), label2.getPreferredSize()));

            //---- label3 ----
            label3.setText("\u4f1a\u5458\u6027\u522b");
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(150, 25), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("\u4f1a\u5458\u5e74\u9f84");
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(285, 25), label4.getPreferredSize()));

            //---- label5 ----
            label5.setText("\u4f1a\u5458\u7b49\u7ea7");
            panel1.add(label5);
            label5.setBounds(new Rectangle(new Point(460, 25), label5.getPreferredSize()));
            panel1.add(nameVal);
            nameVal.setBounds(65, 18, 85, 30);
            panel1.add(genderVal);
            genderVal.setBounds(200, 18, 85, 30);
            panel1.add(lowAge);
            lowAge.setBounds(335, 18, 55, 30);
            panel1.add(highAge);
            highAge.setBounds(400, 18, 55, 30);

            //---- label6 ----
            label6.setText("\u2014");
            panel1.add(label6);
            label6.setBounds(new Rectangle(new Point(385, 25), label6.getPreferredSize()));
            panel1.add(levelVal);
            levelVal.setBounds(515, 18, 85, 30);

            //---- queryBtn ----
            queryBtn.setText("\u67e5\u8be2");
            queryBtn.addActionListener(e -> query(e));
            panel1.add(queryBtn);
            queryBtn.setBounds(new Rectangle(new Point(605, 18), queryBtn.getPreferredSize()));

            //---- clearBtn ----
            clearBtn.setText("\u6e05\u7a7a");
            clearBtn.addActionListener(e -> clear(e));
            panel1.add(clearBtn);
            clearBtn.setBounds(new Rectangle(new Point(680, 18), clearBtn.getPreferredSize()));
        }
        contentPane.add(panel1);
        panel1.setBounds(15, 70, 765, 65);

        //======== toolBar1 ========
        {
            toolBar1.setFloatable(false);

            //---- saveBtn ----
            saveBtn.setText("\u65b0\u589e");
            saveBtn.addActionListener(e -> save(e));
            toolBar1.add(saveBtn);

            //---- deleteBtn ----
            deleteBtn.setText("\u5220\u9664");
            deleteBtn.addActionListener(e -> delete(e));
            toolBar1.add(deleteBtn);

            //---- updateBtn ----
            updateBtn.setText("\u4fee\u6539");
            updateBtn.addActionListener(e -> update(e));
            toolBar1.add(updateBtn);
        }
        contentPane.add(toolBar1);
        toolBar1.setBounds(new Rectangle(new Point(20, 150), toolBar1.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 200, 765, 240);

        //======== toolBar2 ========
        {
            toolBar2.setFloatable(false);

            //---- firstBtn ----
            firstBtn.setText("\u9996\u9875");
            firstBtn.addActionListener(e -> first(e));
            toolBar2.add(firstBtn);

            //---- privousBtn ----
            privousBtn.setText("\u4e0a\u4e00\u9875");
            privousBtn.addActionListener(e -> privous(e));
            toolBar2.add(privousBtn);

            //---- nextBtn ----
            nextBtn.setText("\u4e0b\u4e00\u9875");
            nextBtn.addActionListener(e -> next(e));
            toolBar2.add(nextBtn);

            //---- lastBtn ----
            lastBtn.setText("\u5c3e\u9875");
            lastBtn.addActionListener(e -> last(e));
            toolBar2.add(lastBtn);

            //---- backBtn ----
            backBtn.setText("\u8fd4\u56de");
            backBtn.addActionListener(e -> goBack(e));
            toolBar2.add(backBtn);
        }
        contentPane.add(toolBar2);
        toolBar2.setBounds(new Rectangle(new Point(395, 450), toolBar2.getPreferredSize()));

        //---- label7 ----
        label7.setText("\u603b\u8bb0\u5f55\u6570");
        label7.setBackground(Color.white);
        label7.setForeground(Color.white);
        contentPane.add(label7);
        label7.setBounds(new Rectangle(new Point(30, 460), label7.getPreferredSize()));

        //---- total ----
        total.setBackground(Color.white);
        total.setForeground(Color.white);
        contentPane.add(total);
        total.setBounds(90, 460, 25, total.getPreferredSize().height);

        //---- label9 ----
        label9.setText("\u5f53\u524d\u9875");
        label9.setBackground(Color.white);
        label9.setForeground(Color.white);
        contentPane.add(label9);
        label9.setBounds(new Rectangle(new Point(120, 460), label9.getPreferredSize()));

        //---- currentPage ----
        currentPage.setBackground(Color.white);
        currentPage.setForeground(Color.white);
        contentPane.add(currentPage);
        currentPage.setBounds(170, 460, 20, currentPage.getPreferredSize().height);

        //---- label11 ----
        label11.setText("\u603b\u9875\u6570");
        label11.setBackground(Color.white);
        label11.setForeground(Color.white);
        contentPane.add(label11);
        label11.setBounds(new Rectangle(new Point(195, 460), label11.getPreferredSize()));

        //---- pages ----
        pages.setBackground(Color.white);
        pages.setForeground(Color.white);
        contentPane.add(pages);
        pages.setBounds(240, 460, 35, pages.getPreferredSize().height);

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
    private JTextField nameVal;
    private JComboBox genderVal;
    private JFormattedTextField lowAge;
    private JFormattedTextField highAge;
    private JLabel label6;
    private JComboBox levelVal;
    private JButton queryBtn;
    private JButton clearBtn;
    private JToolBar toolBar1;
    private JButton saveBtn;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JToolBar toolBar2;
    private JButton firstBtn;
    private JButton privousBtn;
    private JButton nextBtn;
    private JButton lastBtn;
    private JButton backBtn;
    private JLabel label7;
    private JLabel total;
    private JLabel label9;
    private JLabel currentPage;
    private JLabel label11;
    private JLabel pages;
    private JLabel label10;
    private String[] column;
    private MemberInfoService service;
    private Object[][] data;
    private MemberInfoQuery info;
    private CustomModel model;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
