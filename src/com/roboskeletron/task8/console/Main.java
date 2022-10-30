package com.roboskeletron.task8.console;

import com.roboskeletron.task8.ArrayIO;
import com.roboskeletron.task8.InputArgs;

import java.io.FileNotFoundException;

import static com.roboskeletron.task8.SearchRectangle.searchRectangle;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        InputArgs ioInfo = new InputArgs(args);
        ArrayIO io = new ArrayIO(ioInfo);
        var array = io.getArray();
        var rectangle = searchRectangle(array);

        System.out.print(rectangle.toString());
    }
}
