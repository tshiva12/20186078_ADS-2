import java.util.LinkedList;
/**
 * Class for move to front.
 */
public class MoveToFront {
	/**
	 * Integer variable.
	 * Radix variable.
	 */
    private static final int radix = 256;
    /**
     * encode.
     */
    public static void encode() {
        String str1 = BinaryStdIn.readString();
        char[] char1 = str1.toCharArray();
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < radix; i++) {
            list.add(i);
        }
        for (int j = 0; j < char1.length; j++) {
            int x = list.indexOf((int) char1[j]);
            BinaryStdOut.write((char) x, 8);
            int y = list.remove(x);
            list.add(0, y);
        }
        BinaryStdOut.close();
    }
    /**
     * decode.
     */
    public static void decode() {
        String str2 = BinaryStdIn.readString();
        char[] char2 = str2.toCharArray();
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        for (int i = 0; i < radix; i++) {
            list1.add(i);
        }
        for (int j = 0; j < char2.length; j++) {
            int y = list1.remove((int) char2[j]);
            list1.add(0, y);
            BinaryStdOut.write((char) y, 8);
        }
        BinaryStdOut.close();
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        if (args.length == 0) {
            return;
        }
        if (args[0].equals("-")) {
            encode();
        }
        if (args[0].equals("+")) {
            decode();
        }
    }
}