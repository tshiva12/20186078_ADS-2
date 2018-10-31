/**
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**
     * Boolean array.
     */
    private boolean[] marked;
    /**
     * Integer array.
     */
    private int[] edgeTo;
    /**
     * Boolean array.
     */
    private boolean[] onStack;
    /**
     * Stack.
     */
    private Stack<Integer> cycle;
    /**
     * Constructs the object.
     *
     * @param      G     Digraph
     */
    public DirectedCycle(Digraph G) {
        marked  = new boolean[G.v()];
        onStack = new boolean[G.v()];
        edgeTo  = new int[G.v()];
        for (int i = 0; i < G.v(); i++)
            if (!marked[i] && cycle == null) dfs(G, i);
    }
    /**
     * dfs.
     *
     * @param      G     Digraph
     * @param      v     Integer variable.
     */
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            else if (onStack[w]) {
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
     * Iterable.
     *
     * @return     cycle
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
    /**
     * check.
     *
     * @return     boolean value.
     */
    private boolean check() {
        if (hasCycle()) {
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
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
