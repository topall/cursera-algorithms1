package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first = null, last = null;

    private class Node
    {
        Item item;
        Node next = null;
        Node previous = null;
    }

    public Deque()
    {
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("Null value for addFirst");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (oldFirst != null) {
            oldFirst.previous = first;
        }
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("Null value for addLast");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (oldLast != null) {
            oldLast.next = last;
        }
        last.previous = oldLast;
        if (isEmpty()) {
            first = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        emptyCheck();
        size--;
        Item item = first.item;
        if (isEmpty()) {
            first = null;
            last = null;
            return item;
        }
        first = first.next;
        first.previous = null;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast()
    {
        emptyCheck();
        size--;
        Item item = last.item;
        if (isEmpty()) {
            first = null;
            last = null;
            return item;
        }
        last = last.previous;
        last.next = null;
        return item;
    }

    private void emptyCheck()
    {
        if (size == 0) {
            throw new NoSuchElementException("Deque is empty.");
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()
    {
        return new DequeIterator(this);
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current;

        public DequeIterator(Deque<Item> d)
        {
            current = d.first;
        }

        public Item next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException("There are no more items to return.");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext()
        {
            return current != null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Unsupported Operation 'remove'.");
        }
    }

    // unit testing (optional)
    public static void main(String[] args)
    {
        // UncommentedEmptyMethodBody
    }
}
