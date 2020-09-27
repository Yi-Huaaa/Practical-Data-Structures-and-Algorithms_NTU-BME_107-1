import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    // todo 1 okok
    public double slopeTo ( Point that ) {

        if ( this.x - that.x == 0){
            if ( this.y - that.y == 0 ) {
                //同個點
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        else if ( this.y - that.y == 0  ) {
            //return positive 0
            return +0;
        }
        return ( this.y - that.y) / (double) ( this.x - that.x);
    }
    // todo 2 okok
    public int compareTo(Point that) {
        if ( this.y - that.y == 0)
            return this.x - that.x;
        return this.y - that.y;
    }
    // todo 3 okok
    public Comparator<Point> slopeOrder() {

        return new Comparator<Point>() {
            @Override
            public int compare ( Point a, Point b ) {
                // code here
                //google the for from slack
                //https://stackoverflow.com/questions/39677697/return-a-comparator-from-another-function
                double delta = slopeTo(a) - slopeTo(b);
                if ( delta > 0)
                    return 1;
                else if ( delta < 0 )
                    return -1;
                else
                    return 0;
            }
        };
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public static void main(String[] args) {
    }
}
