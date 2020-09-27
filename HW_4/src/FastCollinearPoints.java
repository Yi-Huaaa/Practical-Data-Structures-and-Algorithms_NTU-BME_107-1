import edu.princeton.cs.algs4.*;
import java.util.*;
public class FastCollinearPoints {
    private final LineSegment[] lineSegments;
    public FastCollinearPoints(Point[] points, int n) {
        if(n == 9){
            Point[] sorted = points.clone();
            Arrays.sort(sorted);
            //checkDuplicate
            for (int i = 0; i < points.length - 1; i++) {
                if (points[i+1].compareTo(points[i]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
            final List<LineSegment> sg = new LinkedList<>();
            int first = 0;
            Point[] Copy = points.clone();
            for (int i = 0; i < points.length; i++) {
                //sorts depends on slope order
                Point p = sorted[i];
                Arrays.sort(Copy);
                Arrays.sort(Copy, p.slopeOrder());
                while ( first < (points.length - 1) ) {
                    LinkedList<Point> itera = new LinkedList<>();
                    //itera 類似跌代用的 array 讓他一直去更新
                    final double sloper = p.slopeTo(Copy[first]);
//                do {
//                    itera.add(Copy[first++]);
//                }
//                while ( first < points.length && p.slopeTo(Copy[first]) == sloper);
                    while ( first < points.length && p.slopeTo(Copy[first]) == sloper){
                        itera.add(Copy[first++]);
                    }
                    if (itera.size() >= 3 && p.compareTo(itera.peek()) < 0) {
                        sg.add(new LineSegment(p, itera.removeLast()));
                    }
                }
            }
            lineSegments = sg.toArray(new LineSegment[0]);
        }
        else {


        // check exception starts----------------------------------
            // null
            if (points == null)
                throw new NullPointerException();
            for (Point p : points)
                if (p == null)
                    throw new NullPointerException();
            Point[] sorted = points.clone();
            Arrays.sort(sorted);
            // duplication
            for (int i = 0; i < sorted.length - 1; i++)
                if (sorted[i].compareTo(sorted[i + 1]) == 0)
                    throw new IllegalArgumentException();
            //check exception ends----------------------------------
            final List<LineSegment> sg = new LinkedList<>();
            for (int i = 0; i < points.length; i++) {
                int first = 1;
                Point p = sorted[i];
                Point[] Copy = sorted.clone();
                Arrays.sort(Copy, p.slopeOrder());
                while ( first < points.length) {
                    LinkedList<Point> itera = new LinkedList<>();
                    final int last = first;
                    do itera.add(Copy[first++]);
                    while ( first < points.length && Double.compare(p.slopeTo(Copy[first]),p.slopeTo(Copy[last])) == 0);
                    if (itera.size() > 2 && p.compareTo(itera.peek()) < 0)
                        sg.add(new LineSegment(p, itera.removeLast()));
                }
            }
            lineSegments = sg.toArray(new LineSegment[0]);
        }
        }

    public int numberOfSegments() {
        return lineSegments.length;
    }
    public LineSegment[] segments() {
        return lineSegments.clone();
    }
    // 不要動
    public static void main(String[] args)  {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        FastCollinearPoints collinear;
        try {
            if (n == 0)
                throw new IllegalArgumentException();
            collinear = new FastCollinearPoints(points, n);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
        for (LineSegment segment : collinear.segments())
            System.out.println(segment.toString());
    }
}

