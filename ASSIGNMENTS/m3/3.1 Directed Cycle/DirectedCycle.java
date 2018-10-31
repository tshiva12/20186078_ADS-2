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
     * @param      G     Digraph.
     */
    public DirectedCycle(final Digraph G) {
        marked  = new boolean[G.vertices()];
        onStack = new boolean[G.vertices()];
        edgeTo  = new int[G.vertices()];
        for (int v = 0; v < G.vertices(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    /**
     * dfs find the path of a directed graph.
     *
     * @param      G     Digraph.
     * @param      v     Integer vertex.
     */
    private void dfs(final Digraph G, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
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
     * Returns a directed cycle if the digraph has a directed cycle, and otherwise.
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
                System.out.println("cycle begins with %d and ends with %d\n" + first + last);
                return false;
            }
        }
        return true;
    }
}