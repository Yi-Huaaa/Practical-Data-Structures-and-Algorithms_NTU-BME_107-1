import edu.princeton.cs.algs4.*;
import java.util.*;

public class FindNeighbors {
    public FindNeighbors()
    {
        pq = new PriorityQueue<>(999999, new MyOrder());
    }
//    int total_size;
    //
    public class node
    {
        int axis; //(splitting axis)
        double value;// (splitting value)
        node left;// (left subtree)
        node right;// (right subtree)
        Point2D point;// (holds a point if left and right children are null)
        public node()
        {
            left = right = null;
            point = null;
        }
    }
    //
    node head;
    //
    public node build(Point2D[] p, int ori)
    {
        node tmp = new node();
        int size = p.length;
        if(size == 1)
        {
            tmp.point = p[0];
            //System.out.println("point "+p[0].x() +" "+p[0].y());
        }
        else
        {
            if(ori == 0)
                Arrays.sort(p, Point2D.X_ORDER);
            else
                Arrays.sort(p, Point2D.Y_ORDER);
            /*
            Point2D p1[] = p.clone();
            Arrays.sort(p, Point2D.X_ORDER);
            Arrays.sort(p1, Point2D.Y_ORDER);

            //System.out.println("size = "+ size);
            //System.out.println(p1);
            if(Math.abs(p[0].x() - p[size - 1].x()) > Math.abs(p1[0].y() - p1[size - 1].y()))
            {
                tmp.axis = 0;// 0 = 切x; 1 = 切y
                tmp.value = (p[size / 2].x() + p[size / 2 - 1].x()) / 2;
                tmp.left = build(Arrays.copyOfRange(p, 0, size / 2));
                tmp.right = build(Arrays.copyOfRange(p, size/2, size));
            }
            else
            {
                tmp.axis = 1;// 0 = 切x; 1 = 切y
                tmp.value = (p1[size / 2].y() + p1[size / 2 - 1].y()) / 2;
                tmp.left = build(Arrays.copyOfRange(p1, 0, size / 2));
                tmp.right = build(Arrays.copyOfRange(p1, size/2, size));
            }
            */
            if(ori == 0)
            {
                tmp.axis = 0;// 0 = 切x; 1 = 切y
                tmp.value = (p[size / 2].x() + p[size / 2 - 1].x()) / 2;
                tmp.left = build(Arrays.copyOfRange(p, 0, size / 2), 1);
                tmp.right = build(Arrays.copyOfRange(p, size/2, size), 1);
            }
            else
            {
                tmp.axis = 1;// 0 = 切x; 1 = 切y
                tmp.value = (p[size / 2].y() + p[size / 2 - 1].y()) / 2;
                tmp.left = build(Arrays.copyOfRange(p, 0, size / 2), 0);
                tmp.right = build(Arrays.copyOfRange(p, size/2, size), 0);
            }
        }
        return tmp;
    }
    //
    public void init(Point2D[] points)
    {
        //System.out.println(points.length);
//        for (int i = 0; i< points.length; i++){
//            System.out.println("test"+points[i].x()+"  "+points[i].y());
//        }
//        pq = new PriorityQueue<>(points.length, new MyOrder());
        head = build(points, 0);
        return;

    }
    //
    public static Point2D hua_quary;
    int hua_k;
    //
    public PriorityQueue<Point2D> pq;
    //
    public void travel(node now)
    {
        if(now.point == null)
        {
            if(now.axis == 0)
            {
                if(hua_quary.x() < now.value)
                {
                    travel(now.left);
                    if(pq.size() < hua_k || hua_quary.x() + hua_quary.distanceTo(pq.peek()) > now.value)
                        travel(now.right);
                }
                else
                {
                    travel(now.right);
                    if(pq.size() < hua_k || hua_quary.x() - hua_quary.distanceTo(pq.peek()) <= now.value)
                        travel(now.left);
                }
            }
            else
            {
                if(hua_quary.y() < now.value)
                {
                    travel(now.left);
                    if(pq.size() < hua_k || hua_quary.y() + hua_quary.distanceTo(pq.peek()) > now.value)
                        travel(now.right);
                }

                else
                {
                    travel(now.right);
                    if(pq.size() < hua_k || hua_quary.y() - hua_quary.distanceTo(pq.peek()) <= now.value)
                        travel(now.left);
                }
            }
        }
        else
        {
            pq.add(now.point);
            if(pq.size() > hua_k){
                //System.out.println(pq.peek().x() + " " + pq.peek().y());
                pq.poll();
            }
            //add to priority queue
        }
    }
    //
    public Point2D[] query(Point2D point, int k)
    {
        pq.clear();
        hua_quary = point;
        hua_k = k;
        Point2D[] result = new Point2D[k];
        travel(head);
        for(int i = 0; i < hua_k; i++)
            result[k-i-1] = pq.poll();
        return result;
        // the points should be sorted accordingly to their distances to the query, from small to large
    }
    //
    public static void main(String[] args){
        In in = new In(args[0]);
        int n = in.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            points[i] = new Point2D(x, y);
        }
        FindNeighbors test = new FindNeighbors();
        test.init(points);
        Point2D []ts = test.query(new Point2D(0.88, 0.89), 3);
        for(int i = 0; i < ts.length; i++)
        {
            System.out.println("ts:"+i+"  "+ts[i].x()+"  " + ts[i].y() + " " + ts[i].distanceTo(hua_quary));
        }
    }


}
class MyOrder implements Comparator<Point2D> {
    public int compare(Point2D p, Point2D q) {
        Point2D tmp = FindNeighbors.hua_quary;
        double xx, yy;
        xx = p.x() - tmp.x();
        yy = p.y() - tmp.y();
        double d1 = xx*xx + yy*yy;
        xx = q.x() - tmp.x();
        yy = q.y() - tmp.y();
        double d2 = xx*xx + yy*yy;
        if (d1 - d2 < 0) return 1;
        else return -1;
    }
}