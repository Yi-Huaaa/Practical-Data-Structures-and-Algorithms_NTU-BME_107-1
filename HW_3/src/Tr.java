import java.util.Iterator;

public class Tr <Item> implements Iterable {
    private int currentSize;
    private Node first = null;
    private Node last = null;

    private class Node {
        Item item;
        Node next;
    }

    public Tr() {
        currentSize = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return currentSize;
    }

    private void checkState() {
        System.out.println("current size: " + currentSize);
        System.out.println("first.item: " + first.item);
        System.out.println("first.next: " + first.next);
        System.out.println("last.item: " + last.item);
        System.out.println("last.next: " + last.next);
        System.out.println("- - - - - - - - - - - - - - - - - - - -");
    }

    // Add item to the end of the list
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        last.next = first;
        currentSize++;
    }

    // Remove item from the beginning of the list
    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        last.next = first;
        currentSize--;
        return item;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 3;
        Tr<Integer> queue = new Tr<>();
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        int counter = 0;
        while (queue.size() != 0) {
            if (queue.currentSize > 0 && counter % m == 0) {
                queue.enqueue(queue.removeFirst());
            } else {
                System.out.print(queue.removeFirst() + " ");
            }
            counter++;
        }
    }
}