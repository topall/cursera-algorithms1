package Algorithms_part1.week4;

import Algorithms_part1.week3.LineSegment;
import Algorithms_part1.week3.Point;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final Point[] points = new Point[4];
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException("Null points given.");
        for (int i = 0; i < points.length && i < 4; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("Null point given.");
            for (int j = i +1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Repeated points given.");
            this.points[i] = points[i];
        }
        Arrays.sort(this.points, points[0].slopeOrder());
    }

    // the number of line segments
    public int numberOfSegments()
    {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments()
    {
        return segments;
    }
}
