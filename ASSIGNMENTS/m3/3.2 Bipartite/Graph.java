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
    private final int vertices;
    /**
     * Integer variable.
     */
    private int edges;
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
        this.vertices = v0;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < v0; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int vertices() {
        return vertices;
    }
    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int edges() {
        return edges;
    }

    /**
     * Adds an edge.
     *
     * @param      v1     Integer variable.
     * @param      w     Integer variable.
     */
    public void addEdge(final int v1, final int w) {
        edges++;
        adj[v1].add(w);
        adj[w].add(v1);
    }
    /**
     * Iterable.
     *
     * @param      v3    Integer variable.
     *
     * @return     adjacent of vertices.
     */
    public Iterable<Integer> adj(final int v3) {
        return adj[v3];
    }
}
