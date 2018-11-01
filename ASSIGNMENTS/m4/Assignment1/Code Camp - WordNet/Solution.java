/**
 * class Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // default constructor is not used.
    }

    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String synsets = StdIn.readString();
        String hypernyms = StdIn.readString();
        String strtype1 = StdIn.readString();
        if (strtype1.equals("Graph")) {
            WordNet wordnet = new WordNet(synsets, hypernyms);
        }
        if (strtype1.equals("Queries")) {
            String[] str1 = StdIn.readString().split(" ");
            if (str1[0].equals("null")) {
                System.out.println("IllegalArgumentException");
            }
        }

    }
}


