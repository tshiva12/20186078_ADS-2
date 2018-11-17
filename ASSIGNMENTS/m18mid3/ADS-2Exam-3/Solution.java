import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		// default constructor is not used.
	}
	// Don't modify this method.
	/**
	 * main function.
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();
		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;
		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;
		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;
		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}
			break;
		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;
		default:
			break;
		}
	}
	// Don't modify this method.
	/**
	 * Read file.
	 *
	 * @param      file  The file
	 *
	 * @return     string.
	 */
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}
	/**
	 * Loads a dictionary.
	 *
	 * @param      file  The file
	 *
	 * @return     search of a string.
	 */
	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		for (String word : toReadFile(file)) {
			word = word.toLowerCase();
			if (st.contains(word)) {
				st.put(word, st.get(word) + 1);
			} else {
				st.put(word, 1);
			}
		}
		return st;
	}
}
class T9 {
	TST tst = new TST();
	/**
	 * Constructs the object.
	 *
	 * @param      st    BinarySearch object.
	 */
	public T9(BinarySearchST<String, Integer> st) {
		for (String each : st.keys()) {
			tst.put(each, st.get(each));
		}
		// your code goes here
	}
	// get all the prefixes that match with given prefix.
	/**
	 * Gets all words.
	 *
	 * @param      prefix  The prefix
	 *
	 * @return     All words.
	 */
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
			return tst.keysWithPrefix(prefix);
	}
	/**
	 * potential words.
	 *
	 * @param      t9Signature  The t 9 signature.
	 *
	 * @return     string.
	 */
	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		// int length1 = t9Signature.length();
  // 		String str = " ";
  // 		for(int i = 0; i < length1; i++) {
  //  			char ch = t9Signature.charAt(i);
  //  			if(ch=='a'||ch=='b'||ch=='c') {
  //   			str +=  2;
  //  			}
  //   		if(ch=='d'||ch=='e'||ch=='f') {
  //     			str += 3;
  //   		}
  //   		if(ch=='g'||ch=='h'||ch=='i') {
  //     			str += 4;
  //   		}
  //   		if(ch=='j'||ch=='k'||ch=='l') {
  //     			str += 5;
  //   		}
  //   		if(ch=='m'||ch=='n'||ch=='o') {
  //     			str += 6;
  //   		}
  //   		if(ch=='p'||ch=='q'||ch=='r'|| ch=='s') {
  //     			str += 7;
  //   		}
  //   		if(ch=='t'||ch=='u'||ch=='v') {
  //     			str += 8;
  //   		}
  //   		if(ch=='w'||ch=='x'||ch=='y'||ch=='z') {
  //     			str += 9;
  //   		}
  // 		}
  // 		return str;
		return null;
	}
	// return all possibilities(words), find top k with highest frequency.
	/**
	 * Gets the suggestions.
	 *
	 * @param      words  The words
	 * @param      k      Integer variable.
	 *
	 * @return     The suggestions.
	 */
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		return null;
	}
	// final output
	// Don't modify this method.
	/**
	 * Final output method t9.
	 *
	 * @param      t9Signature  The t 9 signature
	 * @param      k            Integer variable.
	 *
	 * @return     output.
	 */
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
