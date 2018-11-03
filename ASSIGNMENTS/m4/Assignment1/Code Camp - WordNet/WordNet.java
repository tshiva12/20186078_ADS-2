import java.util.List;
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * Integer variable.
     */
    private int vertices;
    /**
     * Hashing.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> st;
    /**
     * Array list.
     */
    private ArrayList<String> synlist;
    /**
     * Digrapg object.
     */
    private Digraph dig;
    /**
     * SAP object.
     */
    private SAP sap;
    /**
     * Constructs the object.
     * constructor takes the name of the two input files.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    WordNet(final String synsets, final String hypernyms) {
        synlist = new ArrayList<String>();
        st = new LinearProbingHashST<String, ArrayList<Integer>>();
        vertices = readSynsetFile(synsets);
        // for (String s: st.keys()) {
        //     // System.out.println(s+"16");
        // }
        dig = new Digraph(vertices);
        readHypernymFile(hypernyms);
        sap = new SAP(dig);
    }
    /**
     * Reads a synset file.
     *
     * @param      filename  The filename
     *
     * @return     temp value.
     */
    public int readSynsetFile(final String filename) {
        int temp = 0;
        In in = new In("./Files/" + filename);
        // String line = in.readLine();
        while (!in.isEmpty()) {
        // while (!line.equals(null)) {
            temp++;
            // ArrayList<Integer> al = new ArrayList<Integer>(); 
            String[] tokens = in.readLine().split(",");
            // String[] tokens = line.split(",");
            // System.out.println(tokens[0] + ": " + tokens[1]);
            int id = Integer.parseInt(tokens[0]);
            synlist.add(id, tokens[1]);
            String[] nouns = tokens[1].split(" ");
            for (int i = 0; i < nouns.length; i++) {
                ArrayList<Integer> al;
                if (st.contains(nouns[i])) {
                    al = st.get(nouns[i]);
                    al.add(id);
                } else {
                    al = new ArrayList<Integer>();
                    al.add(id);
                }
                st.put(nouns[i], al);
            }
            // line = in.readLine();
                // break;
        }
        return temp;
    }
    /**
     * Reads a hypernym file.
     *
     * @param      hypernyms  The hypernyms
     */
    public void readHypernymFile(String hypernyms) {
        In in1 = new In("./Files/" + hypernyms);
        while (!in1.isEmpty()) {
            String[] tokens2 = in1.readLine().split(",");
            for (int i = 1; i < tokens2.length; i++) {
                // System.out.println(tokens2[i]);
                dig.addEdge(Integer.parseInt(tokens2[0]), Integer.parseInt(tokens2[i]));
            }
        }   
    }
    /**
     * show method display the output of digraph object.
     */
    public void show() {
        DirectedCycle dc = new DirectedCycle(dig);
        if (dc.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else {
            int count = 0;
            for (int i = 0; i < dig.vertices(); i++) {
                if (dig.outdegree(i) == 0) {
                    count++;
                }               
            }
            if (count > 1) {
                throw new IllegalArgumentException("Multiple roots");
            }
            System.out.println(dig);
        }
    }
    /**
     * returns all WordNet nouns.
     *
     * @return     keys.
     */
    public Iterable<String> nouns() {
        return st.keys();
    }
    /**
     * Determines if noun.
     * is the word a WordNet noun ?
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(String word) {
        if (word.equals("null")) {
            throw new IllegalArgumentException();
        }
        return st.contains(word);
    }
    /**
     * distance between nounA and nounB.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     distance.
     */
    public int distance(String nounA, String nounB) {
        ArrayList<Integer> nounA1 = st.get(nounA);
        ArrayList<Integer> nounB1 = st.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return sap.length(nounA1, nounB1);
    }
    /**
     * a synset that is the common ancestor of nounA and nounB
     * in a shortest ancestral path
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     shortest ancestor path.
     */
    public String sap(String nounA, String nounB) {
        ArrayList<Integer> nounA1 = st.get(nounA);
        ArrayList<Integer> nounB1 = st.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        int id = sap.ancestor(nounA1, nounB1);
        return synlist.get(id);
    }
}

