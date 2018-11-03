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
        // System.out.println(strtype1);
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
                    //System.out.println("lkl");
                    while (StdIn.hasNextLine()) {
                        String str = StdIn.readLine();
                        String[] str1 = str.split(" ");
                        //System.out.println(Arrays.toString(str1));
                        if (str1[0].equals("null")) {
                            throw new IllegalArgumentException("IllegalArgumentException");
                        } else {
                            System.out.println(
                                "distance = " + wordnet1.distance(str1[0], str1[1])
                                 + ", " + "ancestor = " + wordnet1.sap(str1[0], str1[1]));
                        }
                    }
                } catch (Exception e) {
                    // e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                break;
            default :
                break;
        }
    }
}


