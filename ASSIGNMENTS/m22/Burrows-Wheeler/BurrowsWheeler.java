import java.util.HashMap;
import java.util.Arrays;
/**
 * Class for burrows wheeler.
 */
public class BurrowsWheeler {
	/**
	 * Integer variable.
	 */
    private static final int radix  = 256;
    /**
     * transform method.
     */
    public static void transform() {
        String str1 = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(str1);
        for (int i = 0; i < csa.length(); ++i) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int j = 0; j < csa.length(); ++j) {
            int index = csa.index(j);
            if (index == 0) {
                BinaryStdOut.write(str1.charAt(str1.length() - 1), radix);
            }
            else {
                BinaryStdOut.write(str1.charAt(index - 1), radix);
            }
        }
        BinaryStdOut.close();
    }
    /**
     * Inverse transform method.
     */
    public static void inverseTransform() {
        int x = BinaryStdIn.readInt();
        String str2 = BinaryStdIn.readString();
        char[] char1 = str2.toCharArray();
        HashMap<Character, Queue<Integer>> hash
        = new HashMap<Character, Queue<Integer>>();
        for (int i = 0; i < char1.length; ++i) {
            if (!hash.containsKey(char1[i])) {
                hash.put(char1[i], new Queue<Integer>());
            }
            hash.get(char1[i]).enqueue(i);
        }
        Arrays.sort(char1);
        int[] y = new int[char1.length];
        for (int j = 0; j < y.length; ++j) {
            y[j] = hash.get(char1[j]).dequeue();
        }
        for (int k = 0; k < y.length; ++k) {
            BinaryStdOut.write(char1[x], radix);
            x = y[x];
        }
        BinaryStdOut.close();
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Illegal command line argument");
        }
        if (args[0].equals("-")) {
            transform();
        }
        else if (args[0].equals("+")) {
            inverseTransform();
        }
        else {
            throw new IllegalArgumentException("Illegal command line argument");
        }

    }
}
