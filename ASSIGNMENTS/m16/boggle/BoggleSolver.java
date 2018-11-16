import java.util.Set;
import java.util.TreeSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
	/**
	 * TrieST object.
	 */
	private TrieST<Integer> tries;
	/**
	 * Set object.
	 */
	private Set<String> validwords;
	/**
	 * boolean array.
	 */
	private boolean[][] check;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	/**
	 * Constructs the object.
	 *
	 * @param      dictionary  The dictionary
	 */
	public BoggleSolver(final String[] dictionary) {
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
	/**
	 * Gets all valid words.
	 * Returns the set of all valid words in the given Boggle board, as an Iterable.
	 *
	 * @param      board  The board
	 *
	 * @return     All valid words.
	 */
	public Iterable<String> getAllValidWords(final BoggleBoard board) {
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
	/**
	 * Adds a character.
	 *
	 * @param      str   The string
	 * @param      c     character.
	 *
	 * @return     string.
	 */
	private String addChar(final String str, final char c) {
		String str1 = str;
		if (c == 'Q') {
			str1 += "QU";
			return str1;
		} else {
			str1 += c;
			return str1;
		}
	}
	/**
	 * Determines if valid.
	 *
	 * @param      word  The word
	 *
	 * @return     True if valid, False otherwise.
	 */
	private boolean isValid(final String word) {
		if (word.length() < 3) {
			return false;
		}
		return tries.contains(word);
	}
	/**
	 * dfs.
	 *
	 * @param      board  The board
	 * @param      check  The check
	 * @param      rows   The rows
	 * @param      cols   The cols
	 * @param      word   The word
	 */
	public void dfs(final BoggleBoard board, final boolean[][] check,
	 int rows, int cols, String word) {
		if (!tries.hasPrefix(word)) {
			return;
		}
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
	/**
	 * Determines if valid row column.
	 *
	 * @param      row    The row
	 * @param      col    The col
	 * @param      board  The board
	 *
	 * @return     True if valid row column, False otherwise.
	 */
	private boolean isValidRowColumn(final int row, final int col, final BoggleBoard board) {
		return (row >= 0 && col >= 0 && row < board.rows() && col < board.cols());
	}
	/**
	 * Returns the score of the given word if it is in the dictionary, zero otherwise.
	 *
	 * @param      word  The word
	 *
	 * @return     score.
	 */
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