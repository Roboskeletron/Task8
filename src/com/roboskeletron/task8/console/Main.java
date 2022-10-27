package com.roboskeletron.task8.console;

import com.roboskeletron.task8.ArrayIO;
import com.roboskeletron.task8.Rectangle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static InputArgs ioInfo;

    public static void main(String[] args) throws FileNotFoundException {
        ioInfo = new InputArgs(args);
        ArrayIO io = new ArrayIO(ioInfo);
        solution(io);
    }

    public static void solution(ArrayIO io){
        var array = io.getArray();
        int height = array.size();
        int width = array.get(0).size();

        array = addZeroPadding(array);

        for (int h = 1; h <= height; h++){
            for (int w = 1; w <= width; w++){
                if (array.get(h).get(w).equals(1))
            }
        }
    }

    public static List<List<Integer>> addZeroPadding(List<List<Integer>> array){
        int width = array.get(0).size();
        int height = array.size();

        for (int i = 0; i < height; i++){
            array.get(i).add(0);
            array.get(i).add(0, 0);
        }
        width+=2;
        List<Integer> padding = new ArrayList<>(width);
        array.add(padding);
        array.add(0, padding);

        return array;
    }

    public static List<Rectangle> getRectangles(List<List<Integer>> array)
}
