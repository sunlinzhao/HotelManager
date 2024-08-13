/*
 * Created by JFormDesigner on Sat Mar 04 23:30:00 CST 2023
 */

package com.coder.hotel.ui.memberlevel;

import com.coder.hotel.entity.MemberLevel;
import com.coder.hotel.service.MemberLevelService;
import com.coder.hotel.ui.MainUi;
import com.coder.hotel.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 */
public class MemberLevelUi extends JFrame {
    private  MemberLevelUi() {
        initComponents();
    }
    private static MemberLevelUi UI=new MemberLevelUi();
    public static MemberLevelUi getInstance(){
        return UI;
    }

    private void query(ActionEvent e) {
        String level=levelVal.getText();
        String low= StringUtil.isEmpty(lowVal.getText())?"0":lowVal.getText();
        String high=StringUtil.isEmpty(highVal.getText())?"0":highVal.getText();
        MemberLevel memberLevel=new MemberLevel();
        memberLevel.setLevel(level);
        memberLevel.setLow(Integer.valueOf(low));
        memberLevel.setHigh(Integer.valueOf(high));
        objects=service.selectExample(memberLevel);
        model.setDataVector(objects,column);
        table1.updateUI();

        // TODO add your code here
    }

    private void clear(ActionEvent e) {
        objects = service.selectList();
        model.setDataVector(objects,column);
        table1.updateUI();
        levelVal.setText("");
        highVal.setText("");
        lowVal.setText("");
        // TODO add your code here
    }

    private void save(ActionEvent e) {
        // TODO add your code here
        MemberLevelAddUi addUi=MemberLevelAddUi.getInstance();
        addUi.setTable(table1);
        UiUtil.indent(UI,addUi);
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
            MemberLevelService service=new MemberLevelService();
            MemberLevel memberLevel=service.selectId(id);
            MemberLevelUpdateUi updateUi=MemberLevelUpdateUi.getInstance();
            updateUi.setMemberLevel(memberLevel);
            updateUi.setTable(table1);
            UiUtil.indent(UI,updateUi);

        }
    }

    private void delete(ActionEvent e) {
        // TODO add your code here
        TableUtil.delete(table1,model,service);
    }

    private void goBack(ActionEvent e) {
        UiUtil.indent(UI, MainUi.getFrame());
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        levelVal = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        queryBtn = new JButton();
        clearBtn = new JButton();
        highVal = new JFormattedTextField();
        lowVal = new JFormattedTextField();
        addBtn = new JButton();
        updateBtn = new JButton();
        deleteBtn = new JButton();
        scrollPane1 = new JScrollPane();
        column = new String[]{"id", "会员等级", "最低金额", "最高金额",  "折扣"};
        service = new MemberLevelService();
        objects = service.selectList();
        model = new CustomModel(objects, column);

        table1 = new JTable(model);
        TableStyle.setStyle(table1);
        button6 = new JButton();
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
        label1.setBounds(0, 0, 800, 55);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u7b49\u7ea7\u540d\u79f0");
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(25, 20), label2.getPreferredSize()));
            panel1.add(levelVal);
            levelVal.setBounds(75, 13, 130, 30);

            //---- label3 ----
            label3.setText("\u4f4e\u503c");
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(215, 20), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("\u9ad8\u503c");
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(380, 20), label4.getPreferredSize()));

            //---- queryBtn ----
            queryBtn.setText("\u67e5\u8be2");
            queryBtn.addActionListener(e -> query(e));
            panel1.add(queryBtn);
            queryBtn.setBounds(new Rectangle(new Point(545, 15), queryBtn.getPreferredSize()));

            //---- clearBtn ----
            clearBtn.setText("\u6e05\u7a7a");
            clearBtn.addActionListener(e -> clear(e));
            panel1.add(clearBtn);
            clearBtn.setBounds(new Rectangle(new Point(625, 15), clearBtn.getPreferredSize()));
            panel1.add(highVal);
            highVal.setBounds(405, 13, 130, 30);
            panel1.add(lowVal);
            lowVal.setBounds(240, 13, 130, 30);
        }
        contentPane.add(panel1);
        panel1.setBounds(35, 65, 715, 55);

        //---- addBtn ----
        addBtn.setText("\u65b0\u589e");
        addBtn.addActionListener(e -> save(e));
        contentPane.add(addBtn);
        addBtn.setBounds(new Rectangle(new Point(35, 135), addBtn.getPreferredSize()));

        //---- updateBtn ----
        updateBtn.setText("\u4fee\u6539");
        updateBtn.addActionListener(e -> update(e));
        contentPane.add(updateBtn);
        updateBtn.setBounds(new Rectangle(new Point(115, 135), updateBtn.getPreferredSize()));

        //---- deleteBtn ----
        deleteBtn.setText("\u5220\u9664");
        deleteBtn.addActionListener(e -> delete(e));
        contentPane.add(deleteBtn);
        deleteBtn.setBounds(new Rectangle(new Point(195, 135), deleteBtn.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(35, 175, 715, 250);

        //---- button6 ----
        button6.setText("\u8fd4\u56de");
        button6.addActionListener(e -> goBack(e));
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(675, 440), button6.getPreferredSize()));

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
    private JTextField levelVal;
    private JLabel label3;
    private JLabel label4;
    private JButton queryBtn;
    private JButton clearBtn;
    private JFormattedTextField highVal;
    private JFormattedTextField lowVal;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button6;
    private JLabel label10;
    private String[] column;
    private MemberLevelService service;
    private Object[][] objects;
    private CustomModel model;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
