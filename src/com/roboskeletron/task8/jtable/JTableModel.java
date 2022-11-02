package com.roboskeletron.task8.jtable;

import javax.swing.table.DefaultTableModel;

public class JTableModel extends DefaultTableModel {
    @Override
    public Class getColumnClass(int index){
        return Integer.class;
    }
}
