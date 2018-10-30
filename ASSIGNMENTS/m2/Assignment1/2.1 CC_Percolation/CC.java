/**
 * Class for cc.
 */
public class CC {
    /**
     * Boolean array.
     */
    private boolean[] marked;
    /**
     * Integer array.
     */
    private int[] id;
    /**
     * Integer array.
     */
    private int[] size;
    /**
     * Integer variable..
     */
    private int count;

    /**
     * Computes the connected components of the undirected graph.
     *
     * @param g the undirected graph
     */
    public CC(final Graph g) {
        marked = new boolean[g.v()];
        id = new int[g.v()];
        size = new int[g.v()];
        for (int i = 0; i < g.v(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }
    /**
     * DFS.
     *
     * @param      g     { parameter_description }
     * @param      v     Integer variable.
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
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
    public int id(final int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component
     *  containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected component
     *  containing vertex {@code v}
     */
    public int size(final int v) {
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
    public boolean connected(final int v, final int w) {
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
    public boolean areConnected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }
    /**
     * validate vertex.
     *
     * @param      v     Integer variable.
     */
    private void validateVertex(final int v) {
        int v1 = marked.length;
        if (v < 0 || v >= v1) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (v1 - 1));
        }
    }
}