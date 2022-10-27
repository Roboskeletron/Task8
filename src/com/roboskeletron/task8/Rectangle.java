package com.roboskeletron.task8;

public class Rectangle {
    public final Point topLeft;
    public final Point bottomRight;
    public final int area;

    public Rectangle(Point topLeft, Point bottomRight){
        this.bottomRight = bottomRight;
        this.topLeft = topLeft;

        int height = bottomRight.heightIndex() - topLeft.heightIndex();
        int width = bottomRight.widthIndex() - topLeft.widthIndex();
        area = height * width;
    }
}
