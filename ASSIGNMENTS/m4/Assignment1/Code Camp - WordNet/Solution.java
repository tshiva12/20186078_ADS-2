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
        switch (strtype1) {
            case "Graph" :
                try {
                    WordNet wordnet = new WordNet(synsets, hypernyms);
                    wordnet.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "Queries" :
                try {
                    WordNet wordnet1 = new WordNet(synsets, hypernyms);
                    while (StdIn.hasNextLine()) {
                        String[] str1 = StdIn.readLine().split(" ");


                        if (str1[0].equals("null")) {
                            throw new IllegalArgumentException("IllegalArgumentException");
                        }
                        System.out.println("distance = " + wordnet1.distance(str1[0], str1[1]) + ", " + "ancestor = " + wordnet1.sap(str1[0], str1[1]));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default :
                break;
        }
    }
}


