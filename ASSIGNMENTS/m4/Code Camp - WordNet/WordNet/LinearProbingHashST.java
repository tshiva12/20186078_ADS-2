/**
 * Class for linear probing hash st.
 *
 * @param      <Key>    The key
 * @param      <Value>  The value
 */
class LinearProbingHashST<Key, Value> {
    /**
     * Integer variable.
     */
    private static final int INIT_CAPACITY = 1;
    /**
     * Integer variable.
     */
    private int n;
    /**
     * Integer variable.
     */
    private int m;
    /**
     * key array.
     */
    private Key[] keys;
    /**
     * value array.
     */
    private Value[] vals;
    /**
     * Constructs the object.
     */
    LinearProbingHashST() {
        this(INIT_CAPACITY);
    }
    /**
     * Constructs the object.
     *
     * @param      capacity  The capacity
     */
    LinearProbingHashST(final int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }
    /**
     * size.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }
    /**
     * Determines if empty.
     * Time complexity is 1.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
     * Determines the key is there are not.
     * Time complexity is logN.
     *
     * @param      key   The key
     *
     * @return     return key value.
     */
    public boolean contains(final Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }
    /**
     * hash.
     * Time complexity is logN.
     *
     * @param      key   The key
     *
     * @return     return hash vale.
     */
    private int hash(final Key key) {
        String s = (String)key;
        return ((int)s.charAt(0) * 11) % m;
    }
    /**
     * Increses the size.
     * Time complexity is logN.
     * 
     * @param      capacity  The capacity
     */
    private void resize(final int capacity) {
        LinearProbingHashST<Key, Value> temp
         = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }
    /**
     * Put.
     * Time complexity is logN.
     *
     * @param      key   The key
     * @param      val   The value
     */
    public void put(final Key key, final Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        if (n >= m/2) {
            resize(2*m);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }
    /**
     * Get.
     * Time complexity is logN.
     *
     * @param      key   The key
     *
     * @return     return value.
     */
    public Value get(final Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key)) {
                return vals[i];
            }
        return null;
    }
    /**
     * Delete.
     * Time complexity is logN.
     *
     * @param      key   The key
     */
    public void delete(final Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m/8) {
            resize(m/2);
        }
    }
    /**
     * Iterable.
     * Time complexity is logN.
     *
     * @return     queue.
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }
    /**
     * check.
     * Time complexity is N.
     *
     * @return     return boolean value.
     */
    private boolean check() {
        if (m < 2*n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) { 
                continue;
            }
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }
}