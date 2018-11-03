public class SAP {
    private Digraph digraph;
    private int distance;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph g) {
        this.digraph = g;
        this.distance = 0;
    }
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        ancestor(v, w);
        return distance;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
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
