import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * n.
     */
    private int n;         // number of elements in bag
    /**
     * first.
     */
    private Node first;    // beginning of bag
    // helper linked list class
    /**
     * Node.
     */
    private class Node {
        /**
         * { var_description }.
         */
        private Item item;
        /**
         * { var_description }.
         */
        private Node next;
    }

   /**
     * Create an empty stack.
     */
    /**
     * Constructs the object.
     */
    public Bag() {
        first = null;
        n = 0;
    }

   /**
     * Is the BAG empty?
     */
    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the bag.
     */
    /**
     * size.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }

   /**
     * Add the item to the bag.
     */
    /**
     * add.
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
     */
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional.
    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * { var_description }.
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
         * remove.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * next.
         *
         * @return     { description_of_the_return_value }
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







