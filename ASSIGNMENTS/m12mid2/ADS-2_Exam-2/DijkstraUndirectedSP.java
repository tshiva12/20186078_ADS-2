/**
 * Class for dijkstra undirected sp.
 */
public class DijkstraUndirectedSP {
    /**
     * double array.
     */
    private double[] distTo;
    /**
     * Edge array.
     */
    private Edge[] edgeTo;
    /**
     * IndexMinPQ.
     */
    private IndexMinPQ<Double> pq;
    /**
     * Computes a shortest-paths tree from the source vertex to every
     * other vertex in the edge-weighted graph.
     *
     * @param      g     the edge-weighted digraph
     * @param      s     the source vertex
     */
    public DijkstraUndirectedSP(final EdgeWeightedGraph g, final int s) {
        distTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        validateVertex(s);
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq = new IndexMinPQ<Double>(g.vertices());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : g.adj(v)) {
                relax(e, v);
            }
        }
    }
    /**
     * relax.
     *
     * @param      e     Edge object.
     * @param      v     Integer variable.
     */
    private void relax(final Edge e, final int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
    /**
     * distTo.
     *
     * @param      v     Integer variable.
     *
     * @return     distance.
     */
    public double distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path between the source vertex and
     * vertex.
     *
     * @param      v     the
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * Iterable.
     *
     * @param      v     Integer variable.
     *
     * @return     path.
     */
    public Iterable<Edge> pathTo(final int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
    /**
     * check.
     *
     * @param      g1     EdgeWeightedGraph object.
     * @param      s     integer variable.
     *
     * @return      boolean value.
     */
    private boolean check(final EdgeWeightedGraph g1, final int s) {
        for (Edge e : g1.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < g1.vertices(); v++) {
            if (v == s) {
                continue;
            }
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }
        for (int v = 0; v < g1.vertices(); v++) {
            for (Edge e : g1.adj(v)) {
                int w = e.other(v);
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }
        for (int w = 0; w < g1.vertices(); w++) {
            if (edgeTo[w] == null) {
                continue;
            }
            Edge e = edgeTo[w];
            if (w != e.either() && w != e.other(e.either())) {
                return false;
            }
            int v = e.other(w);
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }
    /**
     * validate the vertex.
     *
     * @param      v     Integer variable.
     */
    private void validateVertex(final int v) {
        int vertices = distTo.length;
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }
}




