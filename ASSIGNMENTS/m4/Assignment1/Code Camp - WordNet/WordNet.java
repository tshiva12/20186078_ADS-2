import java.util.List;
import java.util.ArrayList;
public class WordNet {
    private int vertices;
    private LinearProbingHashST<String, ArrayList<Integer>> st;
    private ArrayList<String> synlist;
    private Digraph dig;
    private SAP sap;
    // constructor takes the name of the two input files
    WordNet(final String synsets, final String hypernyms) {
        st = new LinearProbingHashST<String, ArrayList<Integer>>();
        synlist = new ArrayList<String>();
        vertices = readSynsetFile(synsets);
        dig = new Digraph(vertices);
        readHypernymFile(hypernyms);
        sap = new SAP(dig);
    }
    public int readSynsetFile(final String filename) {
        In in = new In(".\\Files\\" + filename);
        while (!in.isEmpty()) {
            vertices++;
            // ArrayList<Integer> al = new ArrayList<Integer>(); 
            String[] tokens = in.readLine().split(",");
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
        }
        return vertices;
    }
        
    public void readHypernymFile(String hypernyms) {
        In in1 = new In(".\\Files\\" + hypernyms);
        while (!in1.isEmpty()) {
            String[] tokens2 = in1.readString().split(",");
            for (int i = 1; i < tokens2.length; i++) {
                dig.addEdge(Integer.parseInt(tokens2[0]), Integer.parseInt(tokens2[i]));
            }
        }   
    }
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
    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return st.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word.equals("null")) {
            throw new IllegalArgumentException();
        }
        return st.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        ArrayList<Integer> nounA1 = st.get(nounA);
        ArrayList<Integer> nounB1 = st.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return sap.length(nounA1, nounB1);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
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
