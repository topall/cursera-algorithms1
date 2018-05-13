package Algorithms_part1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int head = 0, tail = 0;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        queue = (Item[]) new Object[1];
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = head; i < tail; i++)
            copy[i-head] = queue[i];
        queue = copy;
        tail -= head;
        head = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return tail == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return tail - head;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("The client calls enqueue() with a null argument.");
        if (tail == queue.length)
            resize(2 * size());
        queue[tail++] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("The randomized queue is empty.");
        int key = StdRandom.uniform(head, tail);
        Item item = queue[key];
        queue[key] = queue[head];
        queue[head++] = null;
        if (head == tail) {
            head = 0;
            tail = 0;
        }
        if (size() > 0 && size() == queue.length/4)
            resize(queue.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty())
            throw new NoSuchElementException("The randomized queue is empty.");
        return queue[StdRandom.uniform(head, tail)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator<>(this);
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item>
    {
        private final RandomizedQueue<Item> queue;
        private int n;

        public RandomizedQueueIterator(RandomizedQueue<Item> q)
        {
            n = q.size();
            queue = q;
        }

        public Item next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException("There are no more items to return.");
            }
            n--;
            Item item = queue.sample();
            return item;
        }

        public boolean hasNext()
        {
            return n > 0;
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
        //                RandomizedQueue<Integer> r = new RandomizedQueue<>();
        //
        ////                for (int i = 0; i < 10; i++) {
        ////                    r.enqueue(i);
        ////                    StdOut.print(r.size());
        ////                }
        //                StdOut.print("|");
        //                    r.enqueue(22);
        //                    StdOut.print(r.size());
        //                StdOut.print("|".concat(r.dequeue().toString().concat("|")));
        //                StdOut.print(r.size());
        //                StdOut.print("|");
        //        r.enqueue(36);
        //        StdOut.print(r.size());
    }
}
