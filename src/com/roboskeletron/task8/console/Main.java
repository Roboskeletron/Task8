package com.roboskeletron.task8.console;

import com.roboskeletron.task8.ArrayIO;
import com.roboskeletron.task8.InputArgs;

import java.io.FileNotFoundException;

import static com.roboskeletron.task8.SearchRectangle.searchRectangle;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        InputArgs ioInfo = new InputArgs(args);
        ArrayIO io = new ArrayIO(ioInfo);
        var rectangle = searchRectangle(io);
        int height = rectangle.bottomRight.heightIndex() - rectangle.topLeft.heightIndex() + 1;
        int width = rectangle.bottomRight.widthIndex() - rectangle.topLeft.widthIndex() + 1;

        System.out.printf("(%d, %d, %d, %d)", rectangle.topLeft.heightIndex() - 1, rectangle.topLeft.widthIndex() - 1,
                height, width);
    }
}
