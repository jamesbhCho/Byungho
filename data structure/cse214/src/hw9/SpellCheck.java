package hw9;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

/**
 * 
 * Name: Byungho Cho
 *
 */
public class SpellCheck {
	final String path = "D:/spring semester 2023/cse214/hw/hw9/dictionary.txt";

	//dictionary
	private HashSet<String> dictionary;
	public SpellCheck() {
		dictionary = new HashSet<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				// 'line' is a valid word
				//Store all valid words in the dictionary.
				dictionary.add(line);
			}
			br.close();
		} catch(Exception e) {
			System.err.println("File error: " + e.getMessage());
			System.exit(-1);
		}		
	}
	/*
	 * The length of the return array should be the same as the number of words in 'sentence'.
	 * The i-th element of the return array is the substitute candidate for the i-th word in the sentence.
	 * If the i-th word is a valid word (i.e., not a typo), then the array should be empty.
	 */
	/*
	 * : Store all valid words in the dictionary. For each word in the given 
		sentence, see if the word is valid. If it is valid, then you don¡¯t have to provide any candidates. If it 
		is invalid (i.e., a typo) you should prepare a list of valid correction candidates and make it part of 
		the final output. The valid correction candidates are prepared by the following modifications.
		For each of the candidate correction, you should test whether it¡¯s part of the dictionary to determine 
		if it indeed is a valid candidate. The time complexity of checking whether the given word is valid 
		MUST be as efficient as possible. You must also implement any data structures you need on your 
		own
		Implement the method spellCheck(String) that receives a sentence in String. 
		Return an array of ArrayList<String> that containsKey correction candidates for each word. More 
		precisely, each position of the array should be an ArrayList<String> that lists all candidates for the 
		word corresponding to that position in the input sentence. If the word is valid, then it should be 
		null. For example, if the input sentence is ¡°I love my uant¡±, the output should be {null, null, null, 
		[aunt, ant]}, where [] indicates ArrayList¡¯s contents. The exact output of this example will depend on 
		the dictionary I give you. 
	 */

	/*Documentation:
	 * for the following implementation of spellCheck, the objective is to go through the input String
	 * and depending on the validity(whether it is right or misspelled) of the String, it either returns null
	 * or suggest a list of candidates for the input word. The each of the candidate correction it checks for the
	 * validity of the word by searching it in the dictionary and this is done via HashSet. The searching itself
	 * takes constant time as HashSet allows direct access to the element as each elements are a set. 
	 * The logic for suggesting a list of candidates for each misspelled words are done via various loops.
	 * The first one is the replacement of a single character and it is done by going through the
	 * letters of the String and substituting each character in the
	 * word by every letters in the alphabet. For each time it makes the word, it checks for its
	 * validity.The basic logic for all these removal,addition, replacement and swapping of the characters
	 * are done by manipulating the String at an appropriate location.
	 * Time complexity: O(n^3)
	 * Although checking for the validity of the given word takes constant time as HashSet grants
	 * constant time lookup of an element, the general process of spellCheck takes O(n^3). 
	 * That is, for each word in the input sentence(O(n)), it checks for validity and if it is misspelled,
	 * it goes through getCandidate method which goes through the each letters in the mispelled word (O(n))
	 * and perform operations such as removal, addition, swapping, and substitution which takes O(n) times
	 * for each of them. Thus O(n * n * n) results to O(n^3).
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String>[] spellCheck(String sentence) {
		String[] words = sentence.split("\\s"); // 'words' is the list of words in 'sentence'
		int wordCount = words.length;
		ArrayList<String>[] arrWord = new ArrayList[wordCount];

		for (int i = 0; i < wordCount; i++) {
			String word = words[i].toLowerCase();
			if (dictionary.contains(word)) { // word is valid O(1)
				arrWord[i] = null;
			}else {

				arrWord[i] = getCandidates(word);
			}
		}

		return arrWord;
	}
	private ArrayList<String> getCandidates(String word) {

		ArrayList<String> candidates = new ArrayList<>();

		for (int i = 0; i < word.length(); i++) {
			//Removal of a single character from any position
			if (i < word.length() - 1) {
				String candidate = word.substring(0, i) + word.substring(i + 1);
				if (dictionary.contains(candidate)) {
					candidates.add(candidate);
				}
			}
			//adding in the front or the back
			for (char ch = 'a'; ch <= 'z'; ch++) {
				//Addition of a single character into any position
				String candidate2 = word.substring(0, i) + ch + word.substring(i);
				if (dictionary.contains(candidate2)) {
					candidates.add(candidate2);
				}
				//Replacement of a single character
				String candidate = word.substring(0, i) + ch + word.substring(i + 1);
				if (dictionary.contains(candidate)) {
					candidates.add(candidate);
				}//replacement and addition(in the case of kangaroo)
				candidate+= ch;
				if (dictionary.contains(candidate)) {
					candidates.add(candidate);
				}
			}

			// Swap two adjacent characters
			for (int j = 0; j < word.length() - 1; j++) {
				char[] chArr = word.toCharArray();
				char temp = chArr[i];
				chArr[j] = chArr[j + 1];
				chArr[j + 1] = temp;

				String candidate = chArr.toString();
				if (dictionary.contains(candidate)) {
					candidates.add(candidate);
				}
			}
		}
		return candidates;
	}

	public static void main(String[] args) {
		SpellCheck sc = new SpellCheck();
		String[] sentences = {"I love my unt","I ate an x", "paint the banel", "shee is a riend", "kangaru"};
		//String[] sentences = {"I love my unt"};
		// Feel free to change the following printout routine
		for(String sent : sentences) {
			String[] words = sent.split("\\s");
			ArrayList<String>[] ret = sc.spellCheck(sent);
			if(ret == null) continue;
			//System.out.println(Arrays.toString(ret));
			for(int i = 0; i < ret.length; i++) {
				if(ret[i] == null) {
					System.out.print(words[i] + " ");
					continue;
				}
				String cand = "";
				Iterator<String> it = ret[i].iterator();
				while(it.hasNext())
					cand += (it.next() + ",");
				System.out.print("[" + cand + "] ");
			}
			System.out.println();
		}
	}

}
