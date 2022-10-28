package com.roboskeletron.task8;

public class Rectangle implements Comparable<Rectangle> {
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

    @Override
    public int compareTo(Rectangle o) {
        return -Integer.compare(this.area, o.area);
    }
}
