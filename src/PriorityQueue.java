import java.util.*;
import java.util.Comparator;

/**
 * @author Mirza Radoncic and Ritwik Banerjee
 */

public class PriorityQueue<T> implements Heap<T> {
    
    private transient int           size;
    private transient ArrayList<T>  queue;
    private transient Comparator<T> comparator;

    // Return type: Generic Priority Queue / Takes in: (a collection, and the comparator)
    // This method is static due to its useful properties: Organize a collection based on the given comparator.
    // ? extends E means that it is a collection or anything that extends Collection of generic type E.

    public static <E> PriorityQueue<E> fromCollection(Collection<? extends E> c, Comparator<E> comparator) {

        PriorityQueue pq = new PriorityQueue(comparator);
        //  Casting: Superclass object = new Subclass();
        ArrayList<E> aL = (ArrayList)c;
        for(int i = 0; i < c.size(); i++)
        pq.insert(aL.get(i));
        return pq;
    }

    // Constructors

    public PriorityQueue(){
        queue = new ArrayList<T>();
    }

    public PriorityQueue(Comparator<T> c) {
        queue = new ArrayList<T>();
        comparator = c;
    }

    public PriorityQueue(ArrayList<T> queue,Comparator<T> comparator){
        this.queue = queue;
        this.comparator = comparator;
    }

    // --------------------------
    //          toString()
    // --------------------------
    public String toString(){
        String str = "";
        for (int i = 0; i < queue.size(); i++) {
            str += queue.get(i) + "\n";
        }
        return str;
    }

    // -----------------------------------
    // Implement Heap Interface Methods:
    // -----------------------------------
    public boolean isEmpty(){
        if(size == 0) return true;
        return false;
    }

    public int size(){
        return size;
    }

    // Think of findBest() like peak()!
    public T findBest(){
        if(isEmpty()) return null;  // Return null if the PQ is empty.
        return queue.get(0);        // Best is at element 0.
    }

    // Swap method for inserting into the heap.
    public void swapper(int x, int y){
        T helper = queue.get(x);
        queue.set(x,queue.get(y));
        queue.set(y,helper);
    }

    public void insert(T t){        // isFull() not necessary, arraylist can keep growing in size. No exception required.
        int childPos;
        size++;                     // Insert -> size + 1
        queue.add(t);               // Add the element at the end of the arraylist.
        childPos = size - 1;
        //Reheapify
        while(childPos > 0 && comparator.compare(queue.get(childPos), queue.get((childPos - 1)/2)) > 0){
            swapper(childPos, (childPos - 1)/2);
            childPos = (childPos - 1)/2;
        }
    }

    public T deleteBest() {
        // Edge Case 0:
        if (isEmpty()) throw new NoSuchElementException("The Priority Queue is empty");

        // Edge Case 1:
        T returnT = findBest();
        if (size == 1) {
            size--;
            queue.remove(0);
            return returnT;
        }

        int position = 0;
        int childPos = 2 * position + 1;

        swapper(size - 1, 0);
        queue.remove(size - 1);
        size--;

        while (childPos < size && comparator.compare(queue.get(childPos), queue.get(position)) > 0) {
            if (childPos + 1 < size && comparator.compare(queue.get(childPos + 1), queue.get(childPos)) > 0) {
                childPos++;
            }
            swapper(position, childPos);
            position = childPos;
            childPos = position * 2 + 1;

        }
        return returnT;
    }

    public void clear(){
        queue = new ArrayList<T>(); // Resets the queue. O(1). Java Garbage Collector will remove the old PQ.
        size = 0;                   // Since new queue is empty, set size to 0.
    }

    // DO NOT TOUCH:
    @Override
    public int hashCode() {
        return queue.hashCode();
    }
}
