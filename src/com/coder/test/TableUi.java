/*
 * Created by JFormDesigner on Mon Feb 13 13:38:49 CST 2023
 */

package com.coder.test;

import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author Administrator
 */
public class TableUi extends JFrame {
    public TableUi() {
        initComponents();
    }

    public static void main(String[] args) {
        UiUtil.indent(null,new TableUi());
    }


    class CustomModel extends DefaultTableModel{
        public CustomModel(Object[][] data,Object[] columnNames){
            super(data,columnNames);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(50, 25, 525, 245);

        contentPane.setPreferredSize(new Dimension(650, 385));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
