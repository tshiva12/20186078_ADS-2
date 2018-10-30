import java.util.NoSuchElementException;
/**
 * Class for graph.
 */
public class Graph {
    /**
     * String Variable.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * Integer variable.
     */
    private final int v;
    /**
     * Integer variable.
     */
    private int e;
    /**
     * Bag variable.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      v1     Integer variable.
     */
    public Graph(int v1) {
        if (v1 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.v = v1;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v1; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int v() {
        return v;
    }
    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int e() {
        return e;
    }
    /**
     * Validate vertex.
     *
     * @param      v1    Integer variable.
     */
    private void validateVertex(final int v1) {
        if (v1 < 0 || v1 >= v) {
            throw new IllegalArgumentException(
                "vertex " + v1 + " is not between 0 and " + (v1 - 1));
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     Integer variable.
     * @param      w     Integer variable.
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        e++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int each : adj[w]) {
            if (each == v) {
                return true;
            }
        }
        return false;
    }
    /**
     * Iterable.
     *
     * @param      v     Integer variable.
     *
     * @return     adjacent of vertices.
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }
    /**
     * Degree.
     *
     * @param      v     Integer variable.
     *
     * @return     degree of vertex.
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges " + NEWLINE);
        for (int i = 0; i < v; i++) {
            s.append(i + ": ");
            for (int w : adj[i]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
