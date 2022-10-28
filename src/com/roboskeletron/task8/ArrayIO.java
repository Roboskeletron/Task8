package com.roboskeletron.task8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Vector;

public class ArrayIO {
    private final FileInputStream inputStream;
    private final FileOutputStream outputStream;

    public  ArrayIO(InputArgs ioInfo) throws FileNotFoundException {
        inputStream = new FileInputStream(ioInfo.inputFile);
        outputStream = new FileOutputStream(ioInfo.outputFile);
    }

    public Vector<Vector> getArray(){
        return getArray(inputStream);
    }

    public void saveArray(Vector<Vector> array) throws IOException {
        saveArray(array, outputStream);
    }

    public static Vector<Vector> getArray(FileInputStream inputStream){
        Scanner file = new Scanner(inputStream, StandardCharsets.UTF_8);
        int width = 0;

        Vector<Vector> lines = new Vector<>();
        
        while (file.hasNextLine()){
            Vector<Integer> line = new Vector<>();
            Scanner line_reader = new Scanner(file.nextLine());
            
            while (line_reader.hasNextInt())
                line.add(line_reader.nextInt());
            lines.add(line);
            
            if (width > 0 && width != line.size())
                throw new IllegalArgumentException("Array is not a rectangle");
            width = line.size();
        }
        
        return lines;
    }
    
    public static void saveArray(Vector<Vector> array, FileOutputStream outputStream) throws IOException {
        if (!outputStream.getChannel().isOpen())
            return;

        outputStream.getChannel().position(0);

        StringBuilder stringBuilder = new StringBuilder();

        for (Vector line : array) {
            for (Object o : line) {
                stringBuilder.append(o);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }

        byte[] data = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        outputStream.write(data);

        outputStream.flush();
    }
}
