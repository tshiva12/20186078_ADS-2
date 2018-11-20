import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for maximum pq.
 *
 * @param      <Key>  The key
 */
public class MaxPQ<Key> implements Iterable<Key> {
    /**
     * Iterable array.
     */
    private Key[] pq;                    // store items at indices 1 to n
    /**
     * Integer Variable.
     */
    private int n;                      // number of items on priority queue
    /**
     * Comperator.
     */
    private Comparator<Key> comparator;  // optional comparator
    /**
     * Initializes an empty priority queue with the given initial capacity.
     * Time complexity 1.
     *
     * @param  initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     * Time complexity 1.
     *
     * @param  initCapacity the initial capacity of this priority queue
     * @param  comparator the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param  comparator the order in which to compare the keys
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Time complexity logN.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param  keys the array of keys
     */
    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i+1] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }
      


    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a largest key on this priority queue.
     * Time complexity 1.
     * 
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }
    /**
     * resize method.
     * Time complexity 1.
     *
     * @param      capacity  The capacity
     */
    private void resize(int capacity) {
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    /**
     * Adds a new key to this priority queue.
     * Time complexity logN.
     *
     * @param  x the new key to add to this priority queue
     */
    public void insert(Key x) {

        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
    }

    /**
     * Removes and returns a largest key on this priority queue.
     * Time complexity logN.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;     // to avoid loiteing and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        return max;
    }
    /**
     * swim.
     * Time complexity logN.
     *
     * @param      k     return k.
     */
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }
    /**
     * sink.
     * Time complexity logN.
     *
     * @param      k     return k variable.
     */
    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    /**
     * less.
     * Time complexity 1.
     *
     * @param      i     integer variable.
     * @param      j     integer variable.
     *
     * @return     return boolen value.
     */
    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }
    /**
     * exchange method is used to swap the elements.
     * Time complexity 1.
     *
     * @param      i     integer variable.
     * @param      j     intger variable.
     */
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    /**
     * Determines if maximum heap.
     * Time complexity logN.
     * 
     *
     * @return     True if maximum heap, False otherwise.
     */
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }
    /**
     * Determines if maximum heap.
     * Time complexity logN.
     *
     * @param      k     integer variable.
     *
     * @return     True if maximum heap, False otherwise.
     */
    private boolean isMaxHeap(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && less(k, left))  return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }
    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in ascending order.
     * <p>
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }
    /**
     * Class for heap iterator.
     */
    private class HeapIterator implements Iterator<Key> {
        /**
         * variable.
         */
        private MaxPQ<Key> copy;
        /**
         * Constructs the object.
         * Time complexity N.
         */
        public HeapIterator() {
            if (comparator == null) copy = new MaxPQ<Key>(size());
            else                    copy = new MaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }
        /**
         * Determines if it has next.
         * Time complexity 1.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return !copy.isEmpty();
        }
        /**
         * remove.
         */
        public void remove()      {
            throw new UnsupportedOperationException();
        }
        /**
         * next.
         * Time complexity 1.
         *
         * @return     return next element.
         */
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }
}