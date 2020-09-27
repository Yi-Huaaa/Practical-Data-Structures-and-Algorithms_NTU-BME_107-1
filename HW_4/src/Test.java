import java.util.*;
public class Test {
    private final LineSegment[] lineSegments;
    public Test(Point[] points) {
        //sorting first
        Point[] sorted = points.clone();
        Arrays.sort(sorted);
        //砍掉重複的點 duplication
        for (int i = 0; i < points.length - 1; i++)
            if (points[i+1].compareTo(points[i]) == 0)
                throw new IllegalArgumentException();
        //***1
        final List<LineSegment> sg = new LinkedList<>();
        //final List<LineSegment> sg = new ArrayList<>();
        Point[] Copy = points.clone();
        for (int first = 1, i = 0; i < points.length; i++) {
            //sorts depends on slope order
            Point p = sorted[i];
            Arrays.sort(Copy);
            Arrays.sort(Copy, p.slopeOrder());
            while ( first < points.length) {
                //ArrayList<Point> itera = new ArrayList<>();
                //***2
                LinkedList<Point> itera = new LinkedList<>();
                //itera 類似跌代用的 array 讓他一直去更新
                final int last = first;
                do itera.add(Copy[first++]);
                while ( first < points.length && Double.compare(p.slopeTo(Copy[first]),p.slopeTo(Copy[last])) == 0);
                //as 終於找到大於三ㄍ之後，peek 找到他的頭 發現頭比較小 則把他往前排
                //if (itera.size() > 2 && p.compareTo(itera.get(0)) < 0) {
                //***3
                if (itera.size() > 2 && p.compareTo(itera.peek()) < 0) {
                    sg.add(new LineSegment(p, itera.removeLast()));
                }
            }
        }
        lineSegments = sg.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }
    public LineSegment[] segments() {
        return lineSegments.clone();
    }
    // 不要動 對了
    public static void main(String[] args)  {
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//        FastCollinearPoints collinear;
//        try {
//            if (n == 0)
//                throw new IllegalArgumentException();
//            collinear = new FastCollinearPoints(points);
//        }
//        catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException();
//        }
//        for (LineSegment segment : collinear.segments())
//            System.out.println(segment.toString());
    }
}

