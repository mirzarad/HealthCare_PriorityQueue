import java.util.NoSuchElementException;

/**
 * @author Mirza Radoncic and Ritwik Banerjee
 */

public interface Heap<T> {
    
    /** @return <code>true</code> if and only if the priority queue has no elements. */
    boolean isEmpty();
    
    /** @return the number of elements in the priority queue. */

    int size();

    /**
     * @return the best element from the priority queue, consistent with how elements of the generic parameter type
     * are compared.
     */
    T findBest();
    
    /** Inserts a new element into the priority queue. Duplicates are allowed. */
    void insert(T t);

    /**
     * Removes and returns the best element from the priority queue.
     * @return the best element (also see {@link Heap#findBest}).
     * @throws NoSuchElementException if the priority queue is empty.
     */

    T deleteBest();

    /**
     * Resets the priority queue to an empty priority queue.
     */
    void clear();
}
