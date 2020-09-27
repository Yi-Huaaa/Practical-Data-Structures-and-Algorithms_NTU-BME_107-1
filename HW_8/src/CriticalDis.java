
import edu.princeton.cs.algs4.*;
import java.util.Arrays;
public class CriticalDis {

    public static Point2D[] points;
    public static Point2D [][] Distance;
    public static int Dis_sz[];
    public static int Max_order = 0, min_order = 0;
    public static double ans_d = 2.0;//since the distance won;t be bigger than this number so assume it

    public static void DFS(int now, double d) {
        if (now == Max_order) {
            ans_d = d;
            return;
        }
        double tmp;
        for (int i = 0; i < Dis_sz[now]; i++)
        {
            tmp = Distance[now][i].y();
            if(tmp < ans_d)
            {
                if(tmp > d)
                    DFS((int)Distance[now][i].x(), tmp);
                else
                    DFS((int)Distance[now][i].x(), d);
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            points[i] = new Point2D(x, y);
        }
        double max = 0.0, min = 2.0;
        for (int i = 0 ; i < n ; i++){
            double tmp = points[i].x() + points[i].y();
            if( min > tmp ){
                min = tmp;
                min_order = i;
            }
            if( tmp > max ){
                max = tmp;
                Max_order = i;
            }
        }
        double [][] distance = new double[n][n];
        boolean outside;
        for (int i = 0 ; i < n ; i++){
            outside = false;
            if( points[i].y() > points[Max_order].y() ||  points[i].y() < points[min_order].y() ||
                    points[i].x() > points[Max_order].x()  || points[i].x() < points[min_order].x() ){
                outside = true;
            }
            for (int j = 0; j < n ; j ++)
            {
                if(outside || i == j)
                    distance[i][j] = -1;
                else if(points[i].x() < points[j].x() && points[i].y() < points[j].y())
                {
                    distance[i][j] = points[i].distanceTo(points[j]);
                }
                else
                    distance[i][j] = -1;
            }
        }
        Distance = new Point2D[n][n];
        Dis_sz = new int [n];

        for (int i = 0 ; i < n ; i ++){
            Dis_sz[i] = 0;
            for (int j = 0 ; j < n ; j ++){
                if(distance[i][j] != -1){
                    Distance[i][Dis_sz[i]++] = new Point2D(j, distance[i][j]);
                }
            }
            Arrays.sort(Distance[i], 0, Dis_sz[i], Point2D.Y_ORDER);
        }
        DFS(min_order, 0);

//        for(int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++)
//                System.out.print(distance[i][j] + " ");
//            System.out.println();
//        }
        //System.out.println();
        System.out.printf("%5.5f\n", ans_d);
    }
}