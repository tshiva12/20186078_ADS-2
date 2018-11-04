import java.util.Scanner;
/**
 * class Solution.
 */
class PageRank {
	/**
	 * Digraph object.
	 */
	private Digraph digraph;
	/**
	 * Digraph object.
	 */
	private Digraph revdigraph;
	/**
	 * double array.
	 */
	private double[] pageranks;
	/**
	 * double variable.
	 */
	private double vertices;
	/**
	 * Constructs the object.
	 *
	 * @param      dig   The dig
	 */
	PageRank(final Digraph dig) {
		this.digraph = dig;
		pageranks = new double[dig.vertices()];
	}
	/**
	 * Gets the pr.
	 *
	 * @param      v     Integer variable.
	 *
	 * @return     The pr.
	 */
	public double getPR(int v) {
		// the reverse digraph is used to find the incoming nodes in the graph.
		revdigraph = digraph.reverse();
		vertices = revdigraph.vertices();
		// initially
		for (int i = 0; i < pageranks.length; i++) {
			pageranks[i] = 1.0 / digraph.vertices();
		}
		// iterate through 1000 times.
		for (int i = 1; i < 1000; i++) {
			// iterate through for every node
			for (int j = 0; j < digraph.vertices(); j++) {
				double d = 0.0;
				// adjacent vertices
				for (int k : revdigraph.adj(j)) {
					d += (pageranks[k]) / (double) (digraph.outdegree(k));
				}
				pageranks[j] = d;
			}
		}
		return pageranks[v];
	}
	/**
	 * Returns a string representation of the object.
	 *
	 * @return     String representation of the object.
	 */
	public String toString() {
		double d2 = getPR(0);
		String str = "";
		for (int i = 0; i < pageranks.length; i++) {
			str += i + " - " + pageranks[i] + "\n";
		}
		return str;
	}

}
// class WebSearch {
// 	private String filename;
// 	WebSearch(PageRank pr1, String filename1) {
// 		this.filename = filename;
// 	}
// }
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
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		Digraph digraph = new Digraph(vertices);
		for (int i = 0; i < vertices; i++) {
			String[] strarray = scan.nextLine().split(" ");
			for (int j = 1; j < strarray.length; j++) {
				digraph.addEdge(Integer.parseInt(strarray[0]),
				 Integer.parseInt(strarray[j]));
			}
		}
		System.out.println(digraph.toString());
		PageRank pr = new PageRank(digraph);
		System.out.println(pr);
		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		
		
		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
