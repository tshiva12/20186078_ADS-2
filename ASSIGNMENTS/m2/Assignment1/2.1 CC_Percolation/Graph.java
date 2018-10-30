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
    private final int V;
    /**
     * Integer variable.
     */
    private int E;
    /**
     * Bag variable.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      V     Integer variable.
     */
    public Graph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }
    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }
    /**
     * Validate vertex.
     *
     * @param      v     Integer variable.
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (V - 1));
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     Integer variable.
     * @param      w     Integer variable.
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
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
    public boolean hasEdge(int v, int w) {
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
    public Iterable<Integer> adj(int v) {
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
    public int degree(int v) {
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
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
