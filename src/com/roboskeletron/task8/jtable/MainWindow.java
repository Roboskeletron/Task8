package com.roboskeletron.task8.jtable;

import com.roboskeletron.task8.ArrayIO;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class MainWindow {

    private final JFrame frame = new JFrame();
    private final JTable table = new JTable();

    public MainWindow(){
        Initialize();
    }

    private void Initialize(){
        frame.setSize(480, 480);

        JScrollPane scrollPane = new JScrollPane(table);

        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");

        JMenu menu = new JMenu("File");
        menu.add(openButton);
        menu.add(saveButton);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

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
    }

    private void saveFile(File file) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        var model = (DefaultTableModel) table.getModel();

        ArrayIO.saveArray(model.getDataVector(), stream);
    }

    private void openFile(File file) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(file);
        var array = ArrayIO.getArray(stream);

        loadArrayInTable(array);
    }

    private void loadArrayInTable(Vector<Vector> array) {
        var model = (DefaultTableModel) table.getModel();
        Vector<String> headers = new Vector<>();

        for (int w = 0; w < array.get(0).size(); w++){
            headers.add(String.valueOf(w));
        }

        model.setDataVector(array, headers);

        model.fireTableDataChanged();
    }

    public void run(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
