import java.util.*;
import edu.princeton.cs.algs4.*;

public class KdTree {
    private class Node implements Comparable<Node> {
        private Point2D point;
        private Node left;
        private Node right;
        private boolean flag;
        private RectHV rect;
        public Node(Point2D point, boolean flag,RectHV rect){
            this.point = point;
            this.flag = flag;
            this.rect = rect;
        }
        public int compareTo(Node that){
            if(flag){
                return Double.compare(this.point.x(), that.point.x());
            }else{
                return Double.compare(this.point.y(), that.point.y());
            }
        }
    }
    private Node root;
    private int size = 0;
    public KdTree(){
        // construct an empty set of points
        root = null;
        size = 0;
    }
    /**************************************
     *isEmpty
     *************************************/
    public boolean isEmpty(){
        // is the set empty?
        return size == 0;
    }
    /**************************************
     *size
     *************************************/
    public int size(){
        // number of points in the set
        return size;
    }
    /**************************************
     *insert
     *************************************/
    public void insert(Point2D p){
        // add the point to the set (if it is not already in the set)
        if(this.size==0) {
            this.root = new Node(p,true,new RectHV(0,0,1,1));
            this.size ++;
        }else if(put (root, p)) this.size ++;
    }
    /**************************************
     *put
     *************************************/
    private boolean put(Node root, Point2D p){
        Node tmp = new Node(p,!root.flag,null);
        if(root.point.x() == p.x() && root.point.y() == p.y()) return false;
        int cmp = root.compareTo(tmp);
        RectHV newrect = null;
        if(cmp > 0){
            if(root.left == null){
                if(root.flag){
                    newrect = new RectHV(root.rect.xmin(), root.rect.ymin(), root.point.x(), root.rect.ymax());
                }else{
                    newrect = new RectHV(root.rect.xmin(), root.rect.ymin(), root.rect.xmax(), root.point.y());
                }
                tmp.rect = newrect;
                root.left = tmp;
                return true;
            }
            return put(root.left, p);
        }else{
            if(root.right == null){
                if(root.flag){
                    newrect = new RectHV(root.point.x(), root.rect.ymin(), root.rect.xmax(), root.rect.ymax());
                }else{
                    newrect = new RectHV(root.rect.xmin(),  root.point.y(), root.rect.xmax(), root.rect.ymax());
                }
                tmp.rect = newrect;
                root.right = tmp;
                return true;
            }
            return put(root.right, p);
        }
    }
    /**************************************
     *contains
     *************************************/
    public boolean contains(Point2D p){
        // does the set contain point p?
        if(isEmpty()) return false;
        Node tmp = new Node(p,!root.flag,null);
        Node x = root;
        while(x !=null){
            int cmp = x.compareTo(tmp);
            if(cmp > 0) x = x.left;
            else if((x.point.x() == p.x() && x.point.y() == p.y())) return true;
            else x = x.right;
        }
        return false;
    }
    /**************************************
     *draw
     *************************************/
    public void draw(){
        // draw all points to standard draw
        if (isEmpty()) return;
        drawRec(root, 0, 1, 0, 1);

    }
    /**************************************
     *range
     *************************************/
    public Iterable<Point2D> range(RectHV rect){
        // all points that are inside the rectangle
        Set<Point2D> pointList = new TreeSet<Point2D>();
        rangeRec(root, rect, pointList);
        return pointList;

    }
    private void rangeRec(Node root, RectHV rect, Set<Point2D> set) {
        if (root == null) return;
        if (root.rect.intersects(rect)) {
            if (rect.contains(root.point)) set.add(root.point);
            rangeRec(root.left, rect, set);
            rangeRec(root.right, rect, set);
        }
    }
    /**************************************
     *nearest
     *************************************/
    public Point2D nearest(Point2D p){
        // a nearest neighbor in the set to point p; null if the set is empty
        if(isEmpty()) return null;
        return nearest(root, root.point, p);
    }
    private Point2D nearest(Node root, Point2D champion, Point2D aim){
        //Point2D closest = champion;
        if(root == null || root.rect.distanceSquaredTo(aim) > champion.distanceSquaredTo(aim)) return champion;
        double dist = root.point.distanceSquaredTo(aim);
        if (dist < champion.distanceSquaredTo(aim)){
            champion = root.point;
        }

        Node near, far;
        if((root.flag && (aim.x() < root.point.x())) || (!root.flag && (aim.y() < root.point.y()))){
            near = root.left;
            far = root.right;
        }
        else {
            near = root.right;
            far = root.left;
        }
        champion = nearest(near, champion,aim);
        champion = nearest(far, champion,aim);
        return champion;
    }
    /**************************************
     *drawRec
     *************************************/
    private void drawRec(Node root, double minX, double maxX, double minY, double maxY) {
        if (root == null) return;

        StdDraw.setPenRadius(.001);
        if (root.flag) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(root.point.x(), minY, root.point.x(), maxY);
            drawRec(root.left, minX, root.point.x(), minY, maxY);
            drawRec(root.right, root.point.x(), maxX, minY, maxY);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(minX, root.point.y(), maxX, root.point.y());
            drawRec(root.left, minX, maxX, minY, root.point.y());
            drawRec(root.right, minX, maxX, root.point.y(), maxY);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        root.point.draw();
    }
    /**************************************
     *main
     *************************************/
    public static void main(String[] args){
        // unit testing of the methods (optional)
    }

}