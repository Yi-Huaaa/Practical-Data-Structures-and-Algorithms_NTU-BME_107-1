import java.util.*;

public class CircularQueue<Item> implements Iterable  {
    private ArrayList<Item> circle;
    public CircularQueue() {
        circle = new ArrayList<>();
    }
    public boolean isEmpty() {
        return circle.size() == 0;
    }
    public int size() {
        return circle.size();
    }
    public void enqueue ( Item item ) {// Add item to the end of the list
        circle.add(item);
    }
    public Item removeFirst () {
        return circle.remove(0);
    }
    public Iterator<Item> iterator() {
        return new MyIterator<Item>();
    }
    public Iterator<Item> iterator(int gap) {
        return new MyIterator<Item>(gap);
    }
    private class  MyIterator<Item> implements Iterator<Item>{
        private int index = 0, gap = 0;
        public MyIterator() {}
        public MyIterator(int gap) {
            this.gap = gap;
            index = gap - 1;
        }
        public boolean hasNext() {
            return circle.size() > 0;
        }
        public Item next() {
            if (gap != 0) {
                int i = index;
                if (circle.size() > 0)
                    index = (index + gap - 1) % circle.size();
                return (Item) circle.remove(i);
            }
            else {
                int i = index;
                index = (index + 1) % circle.size();
                return (Item) circle.get(i) ;
            }

        }
    }


    //不動 對了
    //todo 8_the jos question okok
    public static void main ( String[] args)
    {
        int M,N;
        M = Integer.parseInt(args[0]);
        N = Integer.parseInt(args[1]);
        int counter;
        ArrayList<Integer> list = new ArrayList<>();
        for (int count = 1; count <= M; count++)
        {
            list.add(new Integer(count));
        }
        counter = N - 1;
        // System.out.println("% java Josephus "+M+" "+N);
        while (!(list.isEmpty()))
        {
            int i = list.remove(counter);
            System.out.print( ( i-1 )+" ");
            M = M - 1;
            if ( M > 0 )
                counter = (counter + N - 1) % M;
        }

    }
}
