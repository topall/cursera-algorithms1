package week3;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    // x-coordinate of this point
    private final int x;
    // y-coordinate of this point
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public   void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        int x1 = that.x;
        int y1 = that.y;
        if (y < y1)
            return -1;
        if (y > y1)
            return 1;
        if (x < x1)
            return -1;
        if (x > x1)
            return 1;
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        int x1 = that.x;
        int y1 = that.y;
        double divided = (double) y1 - y;
        int dividing = x1 - x;
        if (dividing == 0) {
            return y > y1 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        return  divided / dividing;
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new BySlope(this);
    }

    private static class BySlope implements Comparator<Point>
    {
        private final Point p0;

        public BySlope(Point p) {
            p0 = p;
        }

        public int compare(Point p1, Point p2) {
            Double s1 = p0.slopeTo(p1);
            Double s2 = p0.slopeTo(p2);
            return s1.equals(s2) ? 0 : (s1 < s2 ? -1 : 1);
        }
    }
}
