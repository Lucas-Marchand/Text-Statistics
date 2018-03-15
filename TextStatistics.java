import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * TextStatistics.java
 * 
 * An instantiable class that reads a given text file, parses it, and stores the
 * gernerated statistics
 * 
 * @author Lucas Marchand
 */
public class TextStatistics implements TextStatisticsInterface {
	int charCount;
	int wordCount;
	int lineCount;
	int letterCount;
	int wordLengthCount;
	int AvgWordLengthCount;
	private static final String DELIMITERS = "[\\W\\d_]+";
	
	char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	int[] wordLength = new int[24];
	int freq[] = new int[26];
	ArrayList<String> words = new ArrayList<String>();

	/**
	 * constructs a TextStatistics object from a file to
	 * be analyzed for different aspects.
	 * 
	 * @param text file to be processes
	 */
	public TextStatistics(File file) {
		charCount = 0;
		wordCount = 0;
		lineCount = 0;
		letterCount = 0;
		wordLengthCount = 0;
		AvgWordLengthCount = 0;
		HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
		
		try {

			Scanner lineScanner = new Scanner(file);
			lineScanner.useDelimiter(DELIMITERS);

			while (lineScanner.hasNextLine()) {

				String line = lineScanner.nextLine();
				charCount += line.length();
				
				Scanner stringScanner = new Scanner(line);
				stringScanner.useDelimiter(DELIMITERS);
				
				while (stringScanner.hasNext()) {
					String word = stringScanner.next();
					// get a line from the text file
					AvgWordLengthCount += word.length();
					wordCount++;
					
					// record the words length
					int length = word.length();
					int swap = wordLength[length];
					wordLength[length] = swap+1;
					
					//we don't want to be case sensitive
					String lowerCaseWord = word.toLowerCase();
					
					//for-each loop through each word and increment the value in the hash map
					for (char c : lowerCaseWord.toCharArray()) {
						hash.merge(c, 1, Integer::sum);
					}
				}
				
				stringScanner.close();

				// get a line from the text file
				lineCount++;
			}
			
			//get some resources back
			lineScanner.close();

			// add in new line characters
			charCount += lineCount;
			
			//loop to populate integer array with frequencies from hash map
			for (int i = 0; i < 26; i++) {
				char s = alphabet[i];
				if(hash.containsKey(s)) {
					int x = hash.get(s);
					freq[i] = x;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Invalid file path: " + file);
		}

	}

	/**
	 * Gives the total amount of characters in the text file
	 * 
	 * @return The amount of characters in the file
	 */
	@Override
	public int getCharCount() {

		return charCount;
	}

	/**
	 * Gives the amount of words of the text file as an int.
	 * 
	 * @return The amount of words in the text file
	 */
	@Override
	public int getWordCount() {

		return wordCount;
	}

	/**
	 * The amount of lines of a text file as an int.
	 * 
	 * @return The amount of lines of the text file
	 */
	@Override
	public int getLineCount() {

		return lineCount;
	}

	/**
	 * Returns the array of letter frequencies in the text file with
	 * index 0 = 'a' .... 25 = 'z'.
	 * 
	 * @return an integer array of letter frequencies.
	 */
	@Override
	public int[] getLetterCount() {

		return freq;
	}

	/**
	 * An array of integers where the index is the length
	 * of the word and the value of that index is the 
	 * amount of times that length of word occured in the 
	 * text file.
	 * 
	 * @return an integer array of word lengths
	 */
	@Override
	public int[] getWordLengthCount() {

		return wordLength;
	}

	/**
	 * Gives the length of the average word for that text file.
	 * 
	 * @return a double value for the average word length
	 */
	@Override
	public double getAverageWordLength() {

		return (double) AvgWordLengthCount / wordCount;
	}

}
