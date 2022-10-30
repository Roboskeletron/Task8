package com.roboskeletron.task8;

public class Rectangle {
    public final Point topLeft;
    public final Point bottomRight;
    public final int area;

    public Rectangle(Point topLeft, Point bottomRight){
        this.bottomRight = bottomRight;
        this.topLeft = topLeft;

        int height = bottomRight.heightIndex() - topLeft.heightIndex() + 1;
        int width = bottomRight.widthIndex() - topLeft.widthIndex() + 1;
        area = height * width;
    }

    public String toString(){
        int height = bottomRight.heightIndex() - topLeft.heightIndex() + 1;
        int width = bottomRight.widthIndex() - topLeft.widthIndex() + 1;

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
