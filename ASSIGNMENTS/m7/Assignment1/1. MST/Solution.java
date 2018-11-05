import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //comstructor.
    }
    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph edgeweight = new EdgeWeightedGraph(vertices);
        while (scan.hasNext()) {
            String[] token = scan.nextLine().split(" ");
            Edge edge = new Edge(Integer.parseInt(token[0]),
                Integer.parseInt(token[1]),
                Float.parseFloat(token[2]));
            edgeweight.addEdge(edge);
        }
        LazyPrimMST prim = new LazyPrimMST(edgeweight);
        System.out.printf("%.5f", prim.weight());
    }
}




