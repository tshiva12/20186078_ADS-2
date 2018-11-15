public class BoggleSolver {
	private TrieST<Integer> tries;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		tries = new TrieST<Integer>();
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
		return new Bag<String>();
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (tries.contains(word)) {
			return tries.get(word);
		} else {
			return 0;
		}
	}
}