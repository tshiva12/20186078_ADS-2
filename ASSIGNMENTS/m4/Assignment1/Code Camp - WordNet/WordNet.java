import java.util.Arrays;
public class WordNet {

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        readSynsetFile(synsets, hypernyms);
    }

    public void readSynsetFile(String filename, String hypernyms) {
        int id = 0;
        int nov = 0;
        try{
            In in = new In(".\\Files\\" + filename);
            String[] str1 = null;
            while (!in.isEmpty()) {
                nov++;
                String[] tokens = in.readString().split(",");
                id = Integer.parseInt(tokens[0]);
                str1 = tokens[1].split(" ");
            }
            Digraph dig = new Digraph(nov);
            readHypernymFile(hypernyms, dig);
        } catch (Exception e) {
            System.out.println("not found");
        }
    }
        
    public void readHypernymFile(String hypernyms, Digraph dig1) {
        try {
            In in1 = new In(".\\Files\\" + hypernyms);
            while (!in1.isEmpty()) {
                String[] tokens1 = in1.readString().split(",");
                for (int i = 1; i < tokens1.length; i++) {
                    dig1.addEdge(Integer.parseInt(tokens1[0]), Integer.parseInt(tokens1[i]));
                }
            }
            DirectedCycle dc = new DirectedCycle(dig1);
            if (dc.hasCycle()) {
                System.out.println("Cycle detected");
            } else {
                System.out.println(dig1);
            }    
        } catch (Exception e) {
            System.out.println("not found");
        }
    }
    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    // // do unit testing of this class
    // public static void main(String[] args) {

    // }
}
