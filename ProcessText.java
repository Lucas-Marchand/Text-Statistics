import java.io.File;

/**
 * ProcessText.java
 * 
 * Driver class that gets a list of one or more filenames from the command line
 * and collects statistics on each of the files using an instance of
 * TextStatistics Object
 * 
 * @author Lucas Marhand
 *
 */

public class ProcessText {
	/**
	 * The entry point of the program
	 * 
	 * @param names
	 *            of files to be processed
	 */
	public static void main(String[] args) {
		// check to see if user specified a file.
		if (args.length > 0) {
			// loop through all the files the user specifies.
			for (int i = 0; i < args.length; i++) {
				// make a file object
				File file = new File(args[i]);
				// check if the file exists.
				if (file.getAbsoluteFile().exists()) {
					// make a TextStatistics object that will handle the analysis.
					TextStatistics stats = new TextStatistics(file);

					System.out.println("Statistics for " + file);
					System.out.println("===============================");
					System.out.println(stats.getLineCount() + " lines");
					System.out.println(stats.getWordCount() + " words");
					System.out.println(stats.getCharCount() + " characters");
					System.out.println("-------------------------------");

					// print out letter frequencies
					int[] letterCount = stats.getLetterCount();
					int j = 13;
					int k = 0;
					char y = 'n';
					for (char c = 'a'; c < 'n'; c++) {
						System.out.print(c + " = " + letterCount[k] + "\t\t");
						k++;
						System.out.println(y + " = " + letterCount[j]);
						y++;
						j++;
					}
					System.out.println("-------------------------------");

					// Output length frequencies
					System.out.println("length" + "\tfrequency");
					System.out.println("------" + "\t---------");
					int[] wordLength = stats.getWordLengthCount();

					for (int n = 1; n < 12; ++n) {
						System.out.printf("%6d %10d\n", n, wordLength[n]);
					}

					System.out.println();
					System.out.print("Average word length = ");
					System.out.printf("%.2f", stats.getAverageWordLength());

				}else {
					System.out.println("Invalid file path: " + file);
				}
			}
		} else {
			System.out.println("Usage: java ProcessText file1 [file2 ...]");
		}
	}
}
