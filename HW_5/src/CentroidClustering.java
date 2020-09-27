import edu.princeton.cs.algs4.*;
import java.util.*;

class Points {
    public double x,y;
    public Points(double xx, double yy)
    {
        x= xx;
        y= yy;
    }
}
public class CentroidClustering{
    public static Points point[] ;
    public static void main(String[] args) {
         WeightedQuickUnionUF con;
        // Create a Priority Queue
        In in = new In(args[0]);
        int N = in.readInt();
        PriorityQueue<Points> numbers = new PriorityQueue<>(N*N, new PointC());
        point = new Points[2*N];
        int weight[] = new int[2 * N];
        con = new WeightedQuickUnionUF(2 * N);
        for (int i = 0; i < N; i++) {
            point[i] = new Points(in.readDouble(), in.readDouble());
            weight[i] = 1;
            for(int j = 0; j < i; j++)
                numbers.add(new Points(i, j));
        }
        int cluster_num = N, A, B, C = N, total_weight;
        double x,y;
        Points pp;
        while(cluster_num > 3)
        {
            pp = numbers.poll();
            A = (int)pp.x;
            B = (int)pp.y;
            if(weight[A] == 0 || weight[B] == 0)
                continue;
            con.union(A,B);
            total_weight = weight[A] + weight[B];
            x = ( weight[A] * point[A].x + weight[B] * point[B].x ) / total_weight;
            y = ( weight[A] * point[A].y + weight[B] * point[B].y ) / total_weight;
            point[C] = new Points(x, y);
            weight[C] = total_weight;
            weight[A] = weight[B] = 0;
            for(int i = 0; i < C; i++)
            {
                if(weight[i] > 0)
                    numbers.add(new Points(i, C));
            }
            C++;
            cluster_num--;
        }


        int order[] = new int[weight.length];
        int b = 0;
        for ( int i = 0; i < weight.length ; i++){
            if(weight[i] > 0){
                order[b] = i;
                b++;
            }
        }
        //3 max
        if( weight[order[2]] > weight[order[1]] && weight[order[2]] > weight[order[0]] ) {
            //321
            if (  weight[order[1]] > weight[order[0]] ){
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
            }
            //312
            else{
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
            }
        }
        //2 max
        else if( weight[order[1]] > weight[order[0]] && weight[order[1]] > weight[order[2]] ) {
            //213
            if (  weight[order[0]] > weight[order[2]] ){
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
            }
            //231
            else{
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
            }
        }
        //1 max
        else if( weight[order[0]] > weight[order[1]] && weight[order[0]] > weight[order[2]]) {
            //123
            if (  weight[order[1]] > weight[order[2]] ){
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
            }
            //132
            else{
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
            }
        }
        else
        {
            System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
            System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
            System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));

        }


    }
}

class PointC implements Comparator<Points>{
    public double computeDistance(Points p1, Points p2) {
        double val = Math.pow((p1.x - p2.x),2) +
                Math.pow((p1.y - p2.y),2);
        return Math.sqrt(val);
    }
    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(Points p1, Points p2) {
        if(computeDistance(CentroidClustering.point[(int)p1.x], CentroidClustering.point[(int)p1.y]) <= computeDistance(CentroidClustering.point[(int)p2.x], CentroidClustering.point[(int)p2.y]))
            return -1;
        else
            return 1;

    }
}
