import java.util.Scanner;
class LSD {
    private static final int BITS_PER_BYTE = 8;

    // do not instantiate
    protected LSD() { }

   /**  
     * Rearranges the array of W-character strings in ascending order.
     *
     * @param a the array to be sorted
     * @param w the number of characters per string
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }
}
public final class Solution {
	private Solution() {
		// default constructor is not used.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		String[] str1 = new String[n];
		for (int i = 0; i < str1.length; i++) {
			str1[i] = scan.nextLine();
		}
		LSD lsd = new LSD();
		lsd.sort(str1, str1[0].length());
	}
}