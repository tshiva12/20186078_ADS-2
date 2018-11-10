/**
 * Class for index minimum pq.
 *
 * @param      <Key>  The key
 */
public class IndexMinPQ<Key extends Comparable<Key>> {
    /**.
     * Integer variable.
     */
    private int maxn;
    /**.
     * Integer variable.
     */
    private int n;
    /**.
     * Integer variable.
     */
    private int[] pq;
    /**.
     * Integer variable.
     */
    private int[] qp;
    /**.
     * key variable.
     */
    private Key[] keys;
    /**
     * Constructs the object.
     *
     * @param      max  The maximum n
     */
    public IndexMinPQ(final int max) {
        this.maxn = max;
        n = 0;
        keys = (Key[]) new Comparable[maxn + 1];
        pq   = new int[maxn + 1];
        qp   = new int[maxn + 1];
        for (int i = 0; i <= maxn; i++) {
            qp[i] = -1;
        }
    }
    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return n == 0;
    }
    /**.
     * { contains }
     *
     * @param      i     { i }
     *
     * @return     { true if it is, else false }
     */
    public boolean contains(final int i) {
        return qp[i] != -1;
    }
    /**.
     * { size }
     *
     * @return     { size }
     */
    public int size() {
        return n;
    }
    /**.
     * { insert }
     *
     * @param      i     { i }
     * @param      key   The key
     */
    public void insert(final int i, final Key key) {
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }
    /**.
     * { del min }
     *
     * @return     { min item }
     */
    public int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n + 1];
        qp[min] = -1;
        keys[min] = null;
        pq[n + 1] = -1;
        return min;
    }
    /**.
     * { decrease key }
     *
     * @param      i     { i }
     * @param      key   The key
     */
    public void decreaseKey(final int i, final Key key) {
        keys[i] = key;
        swim(qp[i]);
    }
    /**.
     * { grater }
     *
     * @param      i     { i }
     * @param      j     { j }
     *
     * @return     { true if ,else false }
     */
    private boolean greater(final int i, final int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }
    /**.
     * { exchanges }
     *
     * @param      i     { i }
     * @param      j     { j }
     */
    private void exch(final int i, final int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    /**.
     * { swim }
     *
     * @param      temp     { k }
     */
    private void swim(final int temp) {
        int k = temp;
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }
    /**.
     * { sink }
     *
     * @param      temp     { k }
     */
    private void sink(final int temp) {
        int k = temp;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}
