import java.util.Scanner;
/**
 * List of graphs.
 */
class GraphList {
    /**
     * Integer variable.
     */
    private int v;
    /**
     * Integer variable.
     */
    private int e;
    /**
     * Bag array.
     */
    private Bag<Integer>[] adj;
    /**
     * Bag array.
     */
    private String[] tokens;
    /**
     * Constructs the object.
     */
    GraphList() {
        //default constructor is not used.
    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    GraphList(final Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        if (v < 0) {
            throw new
             IllegalArgumentException(
                "number of vertices in a Graph must be nonnegative");
        }
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
        int e1 = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        if (e1 < 0) {
            throw new
             IllegalArgumentException(
                "number of edges in a Graph must be nonnegative");
        }
        for (int i = 0; i < e1; i++) {
            String[] tokens1 = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(tokens1[0]),
             Integer.parseInt(tokens1[1]));
        }
    }
    /**
     * vertex.
     *
     * @return     vertex.
     */
    public int v() {
        return v;
    }
    /**
     * edge.
     *
     * @return     edges.
     */
    public int e() {
        return e;
    }
    /**
     * Adds an edge.
     *
     * @param      v1    The v 1
     * @param      w1    The w 1
     */
    public void addEdge(final int v1, final int w1) {
        if (v1 == w1) {
            return;
        }
        if (!hasEdge(v1, w1)) {
            e++;
        }
        adj[v1].add(w1);
        adj[w1].add(v1);
    }
    /**
     * Iterable.
     *
     * @param      v2    The v 2
     *
     * @return     vertex.
     */
    public Iterable<Integer> adj(final int v2) {
        return adj[v2];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v3    The v 3
     * @param      w3    The w 3
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v3, final int w3) {
        for (int a:adj(v3)) {
            if (a == w3) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(v + " vertices, " + e + " edges" + "\n");
        if (e > 0) {
            for (int i = 0; i < v; i++) {
                sb.append(tokens[i] + ": ");
                for (int j : adj[i]) {
                    sb.append(tokens[j] + " ");
                }
                sb.append("\n");
            }
            return sb.toString();
        } else {
            sb.append("No edges");
            return sb.toString();
        }
    }
}
/**
 * Class for graph matrix.
 */
class GraphMatrix {
    /**
     * String array.
     */
    private String[] tokens;
    /**
     * 2-d Matrix.
     */
    private int[][] matrix;
    /**
     * Integer variable.
     */
    private int v;
    /**
     * Ibtrger variabe.
     */
    private int e;
    /**
     * Constructs the object.
     */
    GraphMatrix() {
        e = 0;
    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    GraphMatrix(final Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        matrix = new int[v][v];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] tokens1 = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(tokens1[0]), Integer.parseInt(tokens1[1]));
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v1    The v 1
     * @param      w1    The w 1
     */
    public void addEdge(final int v1, final int w1) {
        if (v1 != w1) {
            if (!hasEdge(v1, w1)) {
                matrix[v1][w1] = 1;
                matrix[w1][v1] = 1;
                e++;
            }
        }
    }
    /**
     * Determines if it has edge.
     *
     * @param      v2    The v 2
     * @param      w2    The w 2
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v2, final int w2) {
        if (matrix[v2][w2] == 1) {
            return true;
        }
        return false;
    }
    /**
     * print.
     */
    public void print() {
        String str1 = "";
        str1 += v + " vertices, " + e + " edges" + "\n";
        if (e > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    str1 += matrix[i][j] + " ";
                }
                str1 += "\n";
            }
            System.out.println(str1);
        } else {
            str1 += "No edges";
            System.out.println(str1);
        }
    }
}
/**
 * Class Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //default constructor is not used.
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String str1 = scan.nextLine();
        switch (str1) {
            case "List" :
            GraphList graphlist = new GraphList(scan);
            System.out.println(graphlist);
            break;
            case "Matrix" :
            GraphMatrix graphmatrix = new GraphMatrix(scan);
            graphmatrix.print();
            break;
            default:
            break;
        }
    }
}




