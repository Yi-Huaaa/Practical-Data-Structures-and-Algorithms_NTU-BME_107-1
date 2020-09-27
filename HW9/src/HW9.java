/*
1. Read in a file containing N points (2-dim) using args[0] as the file name

(assuming that the N points are randomly distributed in the square of 1*1)

2. Find minimum-spanning tree
3. Print out the weight of your MST
System.out.printf("%5.5f\n", weight);

Example output:

3.14467
 */
import edu.princeton.cs.algs4.*;
import java.util.*;


public class HW9 {
    public static Point2D[] points;
    public static void main(String[] args) {
        double weight = 0.0;

        In in = new In(args[0]);
        int n = in.readInt();
        points = new Point2D[n];
        int [] exist = new int [n];
        //initialize
        for (int i = 0; i < n ; i++){
            exist[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            points[i] = new Point2D(x, y);
        }
        PriorityQueue<Point2D> pq = new PriorityQueue<>(n*n, new Order());
        //從 0 開始抓
        int choose_point = 0 ;
        exist[choose_point] = 1;
        for ( int i = 1; i < n ; i++ ){
            pq.add(new Point2D(0,i));
        }
        //loop
        Point2D tmp ;
        boolean get_in = true;
        while (get_in)
        {
            tmp = pq.poll();
            choose_point = (int)tmp.y();
            if( exist [choose_point] == 0 ){
                exist[choose_point] = 1;
                weight += points[(int)tmp.x()].distanceTo(points[choose_point]);
                get_in = false;
                for ( int i = 1; i < n ; i++ ){
                    if( exist[i] == 0 )
                    {
                        get_in = true;
                        pq.add(new Point2D(choose_point,i));
                    }
                }
            }
        }
        System.out.printf("%5.5f\n", weight);
    }


}

class Order implements Comparator<Point2D> {
    public int compare(Point2D p, Point2D q) {
        Point2D p1 = HW9.points[(int)p.x()];
        Point2D p2 = HW9.points[(int)p.y()];
        Point2D q1 = HW9.points[(int)q.x()];
        Point2D q2 = HW9.points[(int)q.y()];
        double d1 = p1.distanceSquaredTo(p2);
        double d2 = q1.distanceSquaredTo(q2);
        if (d1 - d2 < 0) return -1;
        else return 1;
    }
}
