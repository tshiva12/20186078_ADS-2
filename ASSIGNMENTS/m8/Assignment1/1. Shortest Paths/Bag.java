import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * Number of elements in bag.
     */
    private int n;
    /**
     * Node variable.
     */
    private Node first;
    /**
     * Class for node.
     */
    private class Node {
        /**
         * variable to store the item value.
         */
        private Item item;
        /**
         * Node variable.
         */
        private Node next;
    }
    /**
    * Create an empty stack.
    */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Is the BAG empty?
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * Return the number of items in the bag.
     *
     * @return     size of bag.
     */
    public int size() {
        return n;
    }
    /**
     * Add the item to the bag.
     * Time complexity is O(1).
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
     * Return an iterator that iterates over the items in the bag.
     *
     * @return     iterator.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * Node variable.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * remove operation.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * next method returns the item.
         *
         * @return     item in the bag.
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}




