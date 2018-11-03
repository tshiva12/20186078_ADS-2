/**
 * Class for sap.
 */
public class SAP {
    /**
     * Digraph object.
     */
    private Digraph digraph;
    /**
     * Integer variable.
     */
    private int distance;
    /**
     * Constructs the object.
     * constructor takes a digrah.
     *
     * @param      g     digraph variable.
     */
    public SAP(final Digraph g) {
        this.digraph = g;
        this.distance = 0;
    }
    /**
     * length of shortest ancestral path between any.
     *  vertex in v and any vertex in w.
     *   -1 if no such path.
     *
     * @param      v     Iterable variable.
     * @param      w     Iterable variable.
     *
     * @return     distance.
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        ancestor(v, w);
        return distance;
    }
    /**
     * a common ancestor that participates in shortest ancestral path.
     *  -1 if no such path.
     *
     * @param      v     Iterable variable.
     * @param      w     Iterable variable.
     *
     * @return     ancestor.
     */
    public int ancestor(final Iterable<Integer> v, final Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsv
         = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw
         = new BreadthFirstDirectedPaths(digraph, w);
        distance = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < digraph.vertices(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int distance1 = bfsv.distTo(i) + bfsw.distTo(i);
                if (distance1 < distance) {
                    distance = distance1;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }
}
