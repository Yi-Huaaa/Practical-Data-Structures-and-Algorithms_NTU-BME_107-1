
import edu.princeton.cs.algs4.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TAs {
    static BreadthFirstDirectedPaths check;
    static Pair min;
    static Point2D[] points;
    static class Pair implements Comparable<Pair>{

        double distance = 0.0;
        Point2D pointa;
        Point2D pointb;
        int a;
        int b;
        public Pair(int a,int b){
            this.distance = points[a].distance(points[b]);
            this.pointa = points[a];
            this.pointb = points[b];
            this.a = a;
            this.b = b;
        }
        public int compareTo(Pair that) {
            if (this.distance>that.distance)return +1;
            else if (this.distance<that.distance)return -1;
            else return 0;
        }
    }
    public static void main(String[] args) {
        In in = new In(args[0]); // open the input file 
        int N = in.readInt();
        points = new Point2D[N];
        int source = 0;
        int target = 0;
        for (int i = 0; i < N; i++) {
            points[i] = new Point2D.Double(0, 0);
            points[i].setLocation(in.readDouble(), in.readDouble());
            if ((points[i].getX() + points[i].getY()) < (points[source].getX() + points[source].getY())) {
                source = i;
            }
            if ((points[i].getX() + points[i].getY()) > (points[target].getX() + points[target].getY())) {
                target = i;
            }
        }

        Digraph g = new Digraph(N);
        MinPQ<Pair> mpq = new MinPQ<Pair>(N * N / 2);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (points[j].getX() > points[i].getX() && points[j].getY() > points[i].getY()) {
                    mpq.insert(new Pair(i,j));
                } else if (points[i].getX() > points[j].getX() && points[i].getY() > points[j].getY()) {
                    mpq.insert(new Pair(j,i));
                }
            }
        }

        Digraph g2 = new Digraph(N);
        do {

            min = mpq.delMin();
            g2.addEdge(min.a, min.b);
//            StdDraw.line(points[min.a].getX(), points[min.a].getY(), points[min.b].getX(), points[min.b].getY());

            check = new BreadthFirstDirectedPaths(g2, source);
        } while (!check.hasPathTo(target));

        DepthFirstDirectedPaths bfs = new DepthFirstDirectedPaths(g2, source);
        double dis = 0;
        ArrayList<Integer> al = new ArrayList<Integer>();

        for(int point:bfs.pathTo(target)){
            al.add(point);
//            StdOut.print(point+" ");

        }
        for(int i = 0;i<al.size()-1;i++){
            int a = al.get(i);
            dis=dis+points[al.get(i)].distance(points[al.get(i+1)]);
        }
        System.out.printf("%.5f\n", dis);
    }
}