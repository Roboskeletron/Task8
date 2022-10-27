package com.roboskeletron.task8;

import com.roboskeletron.task8.console.InputArgs;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayIO {
    private final FileInputStream inputStream;
    private final FileOutputStream outputStream;

    public  ArrayIO(InputArgs ioInfo) throws FileNotFoundException {
        inputStream = new FileInputStream(ioInfo.inputFile);
        outputStream = new FileOutputStream(ioInfo.outputFile);
    }

    public List<List<Integer>> getArray(){
        Scanner file = new Scanner(inputStream, StandardCharsets.UTF_8);
        int width = 0;

        List<List<Integer>> lines = new ArrayList<>();
        
        while (file.hasNextLine()){
            List<Integer> line = new ArrayList<>();
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
    
    public void saveArray(List<List<Integer>> array) throws IOException {
        if (!outputStream.getChannel().isOpen())
            return;

        outputStream.getChannel().position(0);

        for (int h = 0; h < array.size(); h++){
            var line = array.get(h);
            
            for (int w = 0; w < line.size(); w++){
                byte[] data = line.get(w).toString().getBytes(StandardCharsets.UTF_8);
                outputStream.write(data);
            }
        }

        outputStream.flush();
    }
}
