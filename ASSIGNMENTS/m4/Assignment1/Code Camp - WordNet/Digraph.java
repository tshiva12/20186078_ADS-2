/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * String variable.
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
     * Bag array.
     */
    private Bag<Integer>[] adj;
    /**
     * Integer array.
     */
    private int[] indegree;
    /**
     * Initializes an empty digraph with vertices.
     *
     * @param      v1     the number of vertices
     * @throws     IllegalArgumentException
     */
    public Digraph(final int v1) {
        if (v1 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.vertices = v1;
        this.edges = 0;
        indegree = new int[v1];
        adj = (Bag<Integer>[]) new Bag[v1];
        for (int i = 0; i < v1; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  g the digraph to copy
     */
    public Digraph(final Digraph g) {
        this(g.vertices());
        this.edges = g.edges();
        for (int i = 0; i < vertices; i++) {
            this.indegree[i] = g.indegree(i);
        }
        for (int j = 0; j < g.vertices(); j++) {
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj[j]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[j].add(w);
            }
        }
    }
    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int edges() {
        return edges;
    }
    /**
     * validateVetex.
     *
     * @param      v     Integer variable.
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param      v     the tail vertex
     * @param      w     the head vertex
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edges++;
    }

    /**
     * Returns the vertices adjacent from vertex in this digraph.
     *
     * @param      v     the vertex
     *
     * @return     the vertices adjacent from vertex in this digraph,
     *             as an iterable
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex.
     * This is known as the outdegree of vertex.
     *
     * @param  v the vertex
     * @return the outdegree of vertex.
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex.
     * This is known as the indegree of vertex.
     *
     * @param  v the vertex
     * @return the indegree of vertex
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(vertices);
        for (int v = 0; v < vertices; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>,
     *  followed by the number of edges,
     *  followed by the adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
