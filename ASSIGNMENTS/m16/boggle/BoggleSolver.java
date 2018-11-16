import java.util.Set;
import java.util.TreeSet;
public class BoggleSolver {
	private TrieST<Integer> tries;
	private Set<String> validwords;
	private boolean[][] check;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		tries = new TrieST<Integer>();
		validwords = new TreeSet<String>();
		int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};
		for (String eachword : dictionary) {
			if (eachword.length() >= 8) {
				tries.put(eachword, 11);
			} else {
				tries.put(eachword, points[eachword.length()]);
			}
		}


	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		if (board == null) {
			throw new IllegalArgumentException("board is null");
		}
		check = new boolean[board.rows()][board.cols()];
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				String str = addChar("", board.getLetter(i, j));
				dfs(board, check, i, j, str);				
			}
		}
		return validwords;
	}
	private String addChar(String str, char c) {
		if (c == 'Q') {
			return str + "QU";
		} else {
			return str + c;
		}
	}
	private boolean isValid(String word) {
		if (word.length() < 3) {
			return false;
		}
		return tries.contains(word);
	}
	public void dfs(BoggleBoard board, boolean[][] check, int rows, int cols, String word) {
		if (isValid(word)) {
			validwords.add(word);
		}
		check[rows][cols] = true;
		for (int i = rows - 1; i <= rows + 1; i++) {
			for (int j = cols - 1; j <= cols + 1; j++) {
				if (isValidRowColumn(i, j, board) && !check[i][j]) {
					String str1 = addChar(word, board.getLetter(i, j));
					dfs(board, check, i, j, str1);
				}
			}
		}
		check[rows][cols] = false;
	}
	private boolean isValidRowColumn(int row, int col, BoggleBoard board) {
		return (row >= 0 && col >= 0 && row < board.rows() && col < board.cols());
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(final String word) {
		if (word == null) {
			return 0;
		}
		if (tries.contains(word)) {
			return tries.get(word);
		} else {
			return 0;
		}
	}
}