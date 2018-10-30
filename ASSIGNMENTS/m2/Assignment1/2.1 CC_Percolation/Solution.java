import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     * boolean array.
     */
    private boolean[] list;
    /**
     * boolean WeightedQuickUnionUF.
     */
    private Graph graph;
    /**
     * int variable.
     */
    private int n;
    /**
     * int variable.
     */
    private int listsize;
    /**
     * int variable.
     */
    private int first;
    /**
     * int variable.
     */
    private int last;
    /**
     * int variable.
     */
    private int count;
    /**
     * Constructs the object.
     *
     * @param      n1    The n 1
     */
    Percolation(final int n1) {
        this.n = n1;
        this.count = 0;
        this.listsize = n1 * n1;
        this.first = listsize;
        this.last = listsize + 1;
        graph = new Graph((listsize + 2));
        list = new boolean[listsize];
        for (int i = 0; i < n; i++) {
            graph.addEdge(first, i);
            graph.addEdge(last, listsize - i - 1);
        }
    }
    /**
     * Links open sites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void linkOpenSites(final int row, final int col) {
        if (list[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    /**
     * Open.
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        int index = oneDimarray(row, col);
        list[index] = true;
        count++;
        if (n == 1) {
            graph.addEdge(first, index);
            graph.addEdge(last, index);
        }
        int bottom = index + n;
        int top = index - n;
        if (bottom < listsize) {
            linkOpenSites(index, bottom);
        }
        if (top >= 0) {
            linkOpenSites(index, top);
        }
        if (col == 1) {
            if (col != n) {
                linkOpenSites(index, index + 1);
            }
            return;
        }
        if (col == n) {
            linkOpenSites(index, index - 1);
            return;
        }
        linkOpenSites(index, index + 1);
        linkOpenSites(index, index - 1);
    }
    /**
     * Determines if open.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return list[oneDimarray(row, col)];
    }
    /**
     * number of open sites.
     *
     * @return     return count of number of open sites.
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * percolates.
     *
     * @return     return boolean value.
     */
    public boolean percolates() {
        CC cc1 = new CC(graph);
        return cc1.connected(first, last);
    }
    /**
     * 1D array.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     return 1D array.
     */
    public int oneDimarray(final int row, final int col) {
        return n * (row - 1) + col - 1;
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //default constructor.
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Percolation per = new Percolation(n);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            per.open(Integer.parseInt(tokens[0]),
             Integer.parseInt(tokens[1]));
        }
        System.out.println(per.percolates()
         && per.numberOfOpenSites() != 0);
    }
}