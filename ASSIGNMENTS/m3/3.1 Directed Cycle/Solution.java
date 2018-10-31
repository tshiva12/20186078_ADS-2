import java.util.Scanner;
public final class Solution {
	private Solution() {
		// default constructor is not used.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		Digraph digraph = new Digraph(vertices);
		while (edges > 0) {
			String[] tokens = scan.nextLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			digraph.addEdge(a, b);
			edges--;
		}
		DirectedCycle dcycle = new DirectedCycle(digraph);
		if (dcycle.hasCycle()) {
			System.out.println("Cycle exists.");
		} else {
			System.out.println("Cycle doesn't exists.");
		}
	}
}