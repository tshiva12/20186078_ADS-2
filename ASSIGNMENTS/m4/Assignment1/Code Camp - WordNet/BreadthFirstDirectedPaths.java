/**
 * Class for breadth first directed paths.
 */
public class BreadthFirstDirectedPaths {
    /**
     * Integer variable.
     */
    private static final int INFINITY = Integer.MAX_VALUE;
    /**
     * boolean array.
     * marked[v] = is there an s->v path?
     */
    private boolean[] marked;
    /**
     * Integer array.
     * edgeTo[v] = last edge on shortest s->v path
     */
    private int[] edgeTo;
    /**
     * Integer array.
     * distTo[v] = length of shortest s->v path
     */
    private int[] distTo;

    /**
     * Computes the shortest path from {@code s} and every other vertex in graph {@code G}.
     * @param g the digraph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    BreadthFirstDirectedPaths(final Digraph g, final int s) {
        marked = new boolean[g.vertices()];
        distTo = new int[g.vertices()];
        edgeTo = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(s);
        bfs(g, s);
    }

    /**
     * Computes the shortest path from any one of the source vertices in {@code sources}
     * to every other vertex in graph {@code G}.
     * @param g the digraph
     * @param sources the source vertices
     * @throws IllegalArgumentException unless each vertex {@code v} in
     *         {@code sources} satisfies {@code 0 <= v < V}
     */
    public BreadthFirstDirectedPaths(final Digraph g, final Iterable<Integer> sources) {
        marked = new boolean[g.vertices()];
        distTo = new int[g.vertices()];
        edgeTo = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertices(sources);
        bfs(g, sources);
    }

    /**
     * bfs.
     *
     * @param      g     Digraph object.
     * @param      s     integer variable.
     */
    private void bfs(final Digraph G, final int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }
    /**
     * bfs.
     *
     * @param      g        Digragh
     * @param      sources  The sources
     */
    private void bfs(final Digraph G, final Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Is there a directed path from the source {@code s} (or sources) to vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a directed path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path from the source {@code s}
     * (or sources) to vertex {@code v}?
     *
     * @param      v     the vertex
     *
     * @return     the number of edges in a shortest path
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public int distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns a shortest path from or if no such path.
     *
     * @param      v     the vertex
     *
     * @return     the sequence of vertices on a shortest path, as an Iterable
     * @throws     IllegalArgumentException
     */
    public Iterable<Integer> pathTo(final int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

    /**
     * validate the vaertex.
     *
     * @param      v     vertex
     */
    private void validateVertex(int v) {
        int v2 = marked.length;
        if (v < 0 || v >= 2)
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (v2 - 1));
    }
    /**
     * Validate the varitices.
     *
     * @param      vertices  The vertices
     */
    private void validateVertices(final Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int v1 = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= v1) {
                throw new IllegalArgumentException(
                    "vertex " + v + " is not between 0 and " + (v1 - 1));
            }
        }
    }
}