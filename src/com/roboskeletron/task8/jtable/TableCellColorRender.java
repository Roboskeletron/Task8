package com.roboskeletron.task8.jtable;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellColorRender extends JLabel implements TableCellRenderer {

    public TableCellColorRender(){
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Integer val = (Integer) value;

        Color color;

        if (val == 1)
            color = Color.GRAY;
        else
            color = Color.WHITE;

        setBackground(color);

        return this;
    }
}
