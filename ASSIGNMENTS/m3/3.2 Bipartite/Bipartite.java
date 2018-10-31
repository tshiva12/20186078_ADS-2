/**
 * Class for bipartite.
 */
public class Bipartite {
    /**
     * boolean variable.
     */
    private boolean isBipartite;
    /**
     * boolean array.
     */
    private boolean[] color;
    /**
     * boolean array.
     */
    private boolean[] marked;
    /**
     * Integer array.
     */
    private int[] edgeTo;
    /**
     * Stack.
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether an undirected graph is bipartite and finds either a
     * bipartition or an odd-length cycle.
     *
     * @param  g the graph
     */
    Bipartite(final Graph g) {
        isBipartite = true;
        color  = new boolean[g.vertices()];
        marked = new boolean[g.vertices()];
        edgeTo = new int[g.vertices()];

        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    /**
     * Depth First Search.
     * Time complexity of this method is O(V + E).
     * @param      g1     Graph
     * @param      v      Interger variable.
     */
    private void dfs(final Graph g1, final int v) {
        marked[v] = true;
        for (int w : g1.adj(v)) {
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(g1, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    /**
     * Returns true if the graph is bipartite.
     *
     * @return boolean value.
     */
    public boolean isBipartite() {
        return isBipartite;
    }

    /**
     * Returns the side of the bipartite that vertex is on.
     *
     * @param      v     Integer variable,
     *
     * @return     Boolean
     */
    public boolean color(final int v) {
        if (!isBipartite) {
            throw new UnsupportedOperationException("graph is not bipartite");
        }
        return color[v];
    }

    /**
     * Iterable.
     *
     * @return     cycle.
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }
}



