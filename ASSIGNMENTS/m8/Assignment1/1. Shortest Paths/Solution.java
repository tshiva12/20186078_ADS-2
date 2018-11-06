import java.util.Scanner;
import java.util.HashMap;
public final class Solution {
	private Solution() {
		// default constructor is not used.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] tokens = scan.nextLine().split(" ");
		int n = Integer.parseInt(tokens[0]);
		int m = Integer.parseInt(tokens[1]);
		String[] str1 = scan.nextLine().split(" ");
		EdgeWeightedGraph edgeweight = new EdgeWeightedGraph(n);
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		for (int i = 0; i < str1.length; i++) {
			hash.put(str1[i], i);
		}
		while (m > 0) {
			String[] str2 = scan.nextLine().split(" ");
			edgeweight.addEdge(new Edge(
				hash.get(str2[0]),
				 hash.get(str2[1]),
				  Double.parseDouble(str2[2])));
		m--;
		}
		int i = Integer.parseInt(scan.nextLine());
		while (i > 0) {
			String[] str3 = scan.nextLine().split(" ");
			int j = hash.get(str3[0]);
			DijkstraUndirectedSP dij = new DijkstraUndirectedSP(edgeweight, j);
			if (dij.hasPathTo(hash.get(str3[1]))) {
				System.out.println((int) dij.distTo(hash.get(str3[1])));
			}
		i--;
		}
	}
}