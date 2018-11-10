import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // default constructor is not used.
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph edgeweight
         = new EdgeWeightedGraph(vertices);
        while (edges > 0) {
            String[] token = scan.nextLine().split(" ");
            Edge edge = new Edge(Integer.parseInt(token[0]),
                Integer.parseInt(token[1]),
                Float.parseFloat(token[2]));
            edgeweight.addEdge(edge);
            edges--;
        }
        String str1 = scan.nextLine();
        switch (str1) {
        case "Graph":
            //Print the Graph Object.
            try {
                System.out.println(edgeweight);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths,
            //  where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] token1 = scan.nextLine().split(" ");
            int src = Integer.parseInt(token1[0]);
            int dest = Integer.parseInt(token1[1]);
            DijkstraUndirectedSP dij
             = new DijkstraUndirectedSP(edgeweight, src);
            if (dij.hasPathTo(dest)) {
                System.out.println(dij.distTo(dest));
            } else {
                System.out.println("No Path Found.");
            }
            break;
        case "ViaPaths":
            // Handle the case of ViaPaths,
            //  where three integers are given.
            // First is the source and second is the via is the
            //  one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] token2 = scan.nextLine().split(" ");
            int src1 = Integer.parseInt(token2[0]);
            int via = Integer.parseInt(token2[1]);
            int dest1 = Integer.parseInt(token2[2]);
            DijkstraUndirectedSP dij1
             = new DijkstraUndirectedSP(edgeweight, src1);
            if (dij1.hasPathTo(dest1)) {
                System.out.println(dij1.distTo(dest1));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}



