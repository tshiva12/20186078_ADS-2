public class CC {
    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    /**
     * Computes the connected components of the undirected graph.
     *
     * @param G the undirected graph
     */
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }
    /**
     * DFS
     *
     * @param      G     Graph variable.
     * @param      v     Integer variable.
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
    /**
     * Returns the component id of the connected component containing vertex.
     *
     * @param      v     the vertex
     *
     * @return     the component id of the connected component containing vertex
     */
    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected component containing vertex {@code v}
     */
    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph.
     *
     * @return the number of connected components in the graph.
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param      v     one vertex
     * @param      w     the other vertex
     * @return     true else false
     */
    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }
    /**
     * areConnected.
     *
     * @param      v     Integer variable.
     * @param      w     Integer variable
     *
     * @return     boolean value.
     */
    public boolean areConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }
    /**
     * validate vertex
     *
     * @param      v     Integer variable.
     */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (V-1));
        }
    }
}