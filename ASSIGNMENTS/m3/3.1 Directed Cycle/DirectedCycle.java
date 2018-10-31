/**
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**
     * boolean array.
     */
    private boolean[] marked;
    /**
     * Integer array.
     */
    private int[] edgeTo;
    /**
     * boolean array.
     */
    private boolean[] onStack;
    /**
     * Stack.
     */
    private Stack<Integer> cycle;

    /**
     * Constructs the object.
     *
     * @param      g     Digraph.
     */
    public DirectedCycle(final Digraph g) {
        marked  = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
        edgeTo  = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g, v);
            }
        }
    }

    /**
     * dfs find the path of a directed graph.
     *
     * @param      g1     Digraph.
     * @param      v     Integer vertex.
     */
    private void dfs(final Digraph g1, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g1.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g1, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    /**
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a
     *  directed cycle, and otherwise.
     *
     * @return     a directed cycle if the digraph has a
     *             directed cycle, and otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    /**
     * certify that digraph has a directed cycle if it reports one.
     *
     * @return     Boolean value.
     */
    private boolean check() {
        if (hasCycle()) {
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.out.println(
                    "cycle begins with %d and ends with %d\n" + first + last);
                return false;
            }
        }
        return true;
    }
}



