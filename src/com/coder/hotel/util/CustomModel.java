package com.coder.hotel.util;

import javax.swing.table.DefaultTableModel;

/**
 * @author teacher_shi
 */
public class CustomModel extends DefaultTableModel {
    public CustomModel(Object[][] data, Object[] column) {
        super(data, column);
    }
    //禁止jtable可编辑
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}