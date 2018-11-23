import java.util.Arrays;
/**
 * Class for circular suffix array.
 */
public class CircularSuffixArray {
    /**
     * Class for node.
     */
    private class Node implements Comparable<Node> {
        /**
         * String variable.
         */
        private String str1;
        /**
         * Integer variable.
         */
        private int value1;
        /**
         * Constructs the object.
         *
         * @param      str2    The string 2
         * @param      value2  The value 2
         */
        Node(final String str2, final int value2) {
            this.str1 = str2;
            this.value1 = value2;
        }
        /**
         * compareTo.
         *
         * @param      that  The that
         *
         * @return     1 if greater, -1 if lesser and 0 if equal.
         */
        public int compareTo(final Node that) {
            int strlen = str1.length();
            for (int i = 0; i < strlen; i++) {
                char c1 = str1.charAt((i + this.value1) % strlen);
                char c2 = str1.charAt((i + that.value1) % strlen);
                if (c1 > c2) {
                    return 1;
                } else if (c1 < c2) {
                    return -1;
                } else {
                    continue;
                }
            }
            return 0;
        }
        /**
         * Gets the value.
         *
         * @return     The value.
         */
        public int getValue() {
            return this.value1;
        }
    }
    /**
     * Integer array.
     */
    private int[] indexarray;
    /**
     * Integer variable.
     */
    private int length;
    /**
     * Constructs the object.
     *
     * @param      s     String variable.
     */
    public CircularSuffixArray(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        this.length = s.length();
        this.indexarray = new int[length];
        Node[] substrings = new Node[length];
        for (int i = 0; i < length; i++) {
            substrings[i] = new Node(s, i);
        }
        Arrays.sort(substrings);
        for (int j = 0; j < substrings.length; j++) {
            indexarray[j] = substrings[j].getValue();
        }
    }
    /**
     * length method.
     *
     * @return     length.
     */
    public int length() {
        return this.length;
    }
    /**
     * returns index of ith sorted suffix.
     *
     * @param      i     the index of the ith sorted suffix
     *
     * @return     returns index of ith sorted suffix
     */
    public int index(final int i) {
        return indexarray[i];
    }
}
