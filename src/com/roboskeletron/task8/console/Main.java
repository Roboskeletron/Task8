package com.roboskeletron.task8.console;

import com.roboskeletron.task8.ArrayIO;
import com.roboskeletron.task8.Point;
import com.roboskeletron.task8.Rectangle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        InputArgs ioInfo = new InputArgs(args);
        ArrayIO io = new ArrayIO(ioInfo);
        var rectangle = solution(io);
        int height = rectangle.bottomRight.heightIndex() - rectangle.topLeft.heightIndex() + 1;
        int width = rectangle.bottomRight.widthIndex() - rectangle.topLeft.widthIndex() + 1;

        System.out.printf("(%d, %d, %d, %d)", rectangle.topLeft.heightIndex() - 1, rectangle.topLeft.widthIndex() - 1,
                height, width);
    }

    public static Rectangle solution(ArrayIO io){
        var array = io.getArray();
        int height = array.size();
        int width = array.get(0).size();

        addZeroPadding(array);

        var rectangles = getRectangles(height, width, array);

        rectangles.sort(Rectangle::compareTo);

        for (Rectangle rectangle : rectangles){
            if (hasCorrectBoarder(rectangle, array))
                return rectangle;
        }

        return new Rectangle(new Point(0, 0), new Point(0, 0));
    }

    public static boolean hasCorrectBoarder(Rectangle rectangle, List<List<Integer>> array){
        int widthIndex = rectangle.topLeft.widthIndex() - 1;
        int heightIndex = rectangle.topLeft.heightIndex() - 1;
        int sum = 0;

        for (int h = heightIndex; h <= rectangle.bottomRight.heightIndex() + 1; h++){
            for (int w = widthIndex; w <= rectangle.bottomRight.widthIndex() + 1; w++){
                sum += array.get(h).get(w);
            }
        }

        return sum == rectangle.area;
    }

    public static void addZeroPadding(List<List<Integer>> array){
        int width = array.get(0).size();

        for (List<Integer> val : array) {
            val.add(0);
            val.add(0, 0);
        }
        width+=2;
        List<Integer> padding = new ArrayList<>();

        for (int i = 0; i < width; i++)
            padding.add(0);

        array.add(padding);
        array.add(0, padding);
    }

    public static List<Rectangle> getRectangles(int height, int width, List<List<Integer>> array){
        List<Rectangle> result = new ArrayList<>();

        for (int h = 1; h <= height; h++){
            for (int w = 1; w <= width; w++){
                if (array.get(h).get(w).equals(1)){
                    var rectangle = getRectangle(new Point(h, w), array);
                    result.add(rectangle);
                }
            }
        }

        return result;
    }

    public static Rectangle getRectangle(Point start, List<List<Integer>> array){
        int width = 0;
        int lineWidth = array.get(start.heightIndex()).size();

        for (int i = start.widthIndex(); i < lineWidth; i++){
            if (array.get(start.heightIndex()).get(i) == 0)
                break;
            width++;
        }

        for (int h = start.heightIndex() + 1; h < array.size(); h++){
            for (int w = start.widthIndex(); w < start.widthIndex() + width - 1; w++){
                if (array.get(h).get(w) == 0){
                    Point end = new Point(h - 1, start.widthIndex() + width - 1);
                    return new Rectangle(start, end);
                }
            }
        }

        return new Rectangle(new Point(0, 0), new Point(0, 0));
    }
}
