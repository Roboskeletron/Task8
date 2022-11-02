package com.roboskeletron.task8.jtable;

import com.roboskeletron.task8.ArrayIO;
import com.roboskeletron.task8.SearchRectangle;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class MainWindow {

    private final JFrame frame = new JFrame();
    private final JTable table = new JTable(new JTableModel());

    public MainWindow(){
        Initialize();
    }

    private void Initialize(){
        frame.setSize(480, 480);

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel tableControls = new JPanel();
        JPanel rowControls = new JPanel();
        JPanel columnControls = new JPanel();
        JPanel searchControls = new JPanel();

        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");
        JMenuItem closeButton = new JMenuItem("Close");

        JMenu menu = new JMenu("File");
        menu.add(openButton);
        menu.add(saveButton);
        menu.add(closeButton);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        JButton addLine = new JButton("Add row");
        JButton deleteLine = new JButton("Delete row");
        JButton addColumn = new JButton("Add column");
        JButton deleteColumn = new JButton("Delete column");
        JButton getRectangle = new JButton("Search rectangle");

        JLabel resultLabel = new JLabel();

        rowControls.add(addLine);
        rowControls.add(deleteLine);

        columnControls.add(addColumn);
        columnControls.add(deleteColumn);

        searchControls.add(getRectangle);
        searchControls.add(resultLabel);

        tableControls.setLayout(new BoxLayout(tableControls, BoxLayout.PAGE_AXIS));
        tableControls.add(searchControls);
        tableControls.add(rowControls);
        tableControls.add(columnControls);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH, tableControls);

        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                String path = f.getAbsolutePath();
                return path.endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "Text document";
            }
        };

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\wwwrt\\source\\repos\\Task8");
        fileChooser.setFileFilter(filter);

        openButton.addActionListener(e -> {
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                var file = fileChooser.getSelectedFile();

                try {
                    openFile(file);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        saveButton.addActionListener(e -> {
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
                var file = fileChooser.getSelectedFile();

                try {
                    saveFile(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addColumn.addActionListener(e -> {
            var model = getTableModel();
            int name  = model.getColumnCount();
            var rowData = getIntegerVector(model.getRowCount());

            model.addColumn(name, rowData);
        });

        addLine.addActionListener(e -> {
            var model = getTableModel();

            var columns = getIntegerVector(model.getColumnCount());

            model.addRow(columns);
        });

        deleteColumn.addActionListener(e -> {
            var model = getTableModel();
            int columnCount = model.getColumnCount() - 1;

            model.setColumnCount(columnCount);

            model.fireTableDataChanged();
        });

        deleteLine.addActionListener(e -> {
            var model = getTableModel();

            model.removeRow(model.getRowCount() - 1);
        });

        getRectangle.addActionListener(e -> {
            var model = getTableModel();

            Vector<Vector> array = (Vector<Vector>) model.getDataVector().clone();
            var rectangle = SearchRectangle.searchRectangle(array);

            resultLabel.setText(rectangle.toString());
        });

        closeButton.addActionListener(e -> {
            var model = getTableModel();

            model.getDataVector().clear();
            model.setColumnIdentifiers(new Vector<>());

            model.fireTableDataChanged();
        });
    }

    private Vector<Integer> getIntegerVector(int count) {
        Vector<Integer> columns = new Vector<>();

        for (int i = 0; i < count; i++)
            columns.add(0);
        return columns;
    }

    private void saveFile(File file) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        var model = getTableModel();

        ArrayIO.saveArray(model.getDataVector(), stream);
    }

    private void openFile(File file) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(file);
        var array = ArrayIO.getArray(stream);

        loadArrayInTable(array);
    }

    private void loadArrayInTable(Vector<Vector> array) {
        var model = getTableModel();
        Vector<String> headers = new Vector<>();

        for (int w = 0; w < array.get(0).size(); w++){
            headers.add(String.valueOf(w));
        }

        model.setDataVector(array, headers);

        model.fireTableDataChanged();
    }

    private JTableModel getTableModel() {
        return (JTableModel) table.getModel();
    }

    public void run(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
