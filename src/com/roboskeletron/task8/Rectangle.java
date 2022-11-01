package com.roboskeletron.task8;

public class Rectangle {
    public final Point topLeft;
    public final Point bottomRight;
    public final int area;
    public final int height;
    public final int width;

    public Rectangle(Point topLeft, Point bottomRight){
        this.bottomRight = bottomRight;
        this.topLeft = topLeft;

        height = bottomRight.heightIndex() - topLeft.heightIndex() + 1;
        width = bottomRight.widthIndex() - topLeft.widthIndex() + 1;
        area = height * width;
    }

    public Rectangle(){
        topLeft = new Point(0, 0);
        bottomRight = new Point(0, 0);
        area = 0;
        width = -1;
        height = -1;
    }

    public String toString(){
        return "(" +
                (topLeft.heightIndex() - 1) +
                " " +
                (topLeft.widthIndex() - 1) +
                " " +
                height +
                " " +
                width +
                ")";
    }
}
