import java.util.*;
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
        String synsets = StdIn.readLine();
        String hypernyms = StdIn.readLine();
        String strtype1 = StdIn.readLine();
        try {
            switch (strtype1) {
                case "Graph" :
                    WordNet wordnet = new WordNet(synsets, hypernyms);
                break;
                case "Queries" :
                    while (StdIn.hasNextLine()) {
                        // WordNet wordnet1 = new WordNet(synsets, hypernyms);
                        String[] str1 = StdIn.readLine().split(" ");
                        if (str1[0].equals("null")) {
                            throw new IllegalArgumentException("IllegalArgumentException");
                        }
                    }
                break;
                default :
                break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


