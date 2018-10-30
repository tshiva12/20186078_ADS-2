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
     * @param      v0     Integer variable.
     */
    public Graph(final int v0) {
        if (v0 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.v = v0;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v0; i++) {
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
     * @param      v1     Integer variable.
     * @param      w     Integer variable.
     */
    public void addEdge(final int v1, final int w) {
        validateVertex(v1);
        validateVertex(w);
        e++;
        adj[v1].add(w);
        adj[w].add(v1);
    }

    /**
     * Determines if it has edge.
     *
     * @param      v2     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v2, final int w) {
        for (int each : adj[w]) {
            if (each == v2) {
                return true;
            }
        }
        return false;
    }
    /**
     * Iterable.
     *
     * @param      v3    Integer variable.
     *
     * @return     adjacent of vertices.
     */
    public Iterable<Integer> adj(final int v3) {
        validateVertex(v3);
        return adj[v3];
    }
    /**
     * Degree.
     *
     * @param      v4     Integer variable.
     *
     * @return     degree of vertex.
     */
    public int degree(final int v4) {
        validateVertex(v4);
        return adj[v4].size();
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
