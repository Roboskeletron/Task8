package com.roboskeletron.task8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class SearchRectangle {
    public static Rectangle searchRectangle(ArrayIO io){
        var array = io.getArray();
        int height = array.size();
        int width = array.get(0).size();

        addZeroPadding(array);

        var rectangles = getRectangles(height, width, array);

        var comparator = Comparator.comparing((Rectangle rect)->rect.area, Comparator.reverseOrder())
                .thenComparing((Rectangle rect)->rect.topLeft.heightIndex())
                .thenComparing((Rectangle rect)->rect.topLeft.widthIndex());

        rectangles.sort(comparator);

        for (Rectangle rectangle : rectangles){
            if (hasCorrectBoarder(rectangle, array))
                return rectangle;
        }

        return new Rectangle(new Point(0, 0), new Point(0, 0));
    }

    public static boolean hasCorrectBoarder(Rectangle rectangle, Vector<Vector> array){
        int widthIndex = rectangle.topLeft.widthIndex() - 1;
        int heightIndex = rectangle.topLeft.heightIndex() - 1;
        int sum = 0;

        for (int h = heightIndex; h <= rectangle.bottomRight.heightIndex() + 1; h++){
            for (int w = widthIndex; w <= rectangle.bottomRight.widthIndex() + 1; w++){
                sum += (Integer) array.get(h).get(w);
            }
        }

        return sum == rectangle.area;
    }

    public static void addZeroPadding(Vector<Vector> array){
        int width = array.get(0).size();

        for (var val : array) {
            val.add(0);
            val.add(0, 0);
        }
        width+=2;
        Vector<Integer> padding = new Vector<>();

        for (int i = 0; i < width; i++)
            padding.add(0);

        array.add(padding);
        array.add(0, padding);
    }

    public static List<Rectangle> getRectangles(int height, int width, Vector<Vector> array){
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

    public static Rectangle getRectangle(Point start, Vector<Vector> array){
        int width = 0;
        int lineWidth = array.get(start.heightIndex()).size();

        for (int i = start.widthIndex(); i < lineWidth; i++){
            if ((Integer) array.get(start.heightIndex()).get(i) == 0)
                break;
            width++;
        }

        for (int h = start.heightIndex() + 1; h < array.size(); h++){
            for (int w = start.widthIndex(); w < start.widthIndex() + width - 1; w++){
                if ((Integer) array.get(h).get(w) == 0){
                    Point end = new Point(h - 1, start.widthIndex() + width - 1);
                    return new Rectangle(start, end);
                }
            }
        }

        return new Rectangle(new Point(0, 0), new Point(0, 0));
    }
}
