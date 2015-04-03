package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LongestSubSequence {
	public static void main(String[] arg) throws Exception {
		if (arg.length == 0) {
			throw new Exception("No input file specified");
		}
		File f = new File(arg[0]);
		if (!f.exists()) {
			throw new Exception("Given input file not present");
		}
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new FileReader(f));
			String line = inputReader.readLine();
			while (line != null) {
				String[] lineArray = line.split("\\s");
				int[] numbers = new int[lineArray.length];
				int k = 0;
				for (String no : lineArray) {
					numbers[k++] = Integer.parseInt(no);
				}
				int[] longestArray = new int[lineArray.length];
				longestArray[0] = 1;
				for (int i = 0; i < lineArray.length; i++) {
					longestArray[i] = 1;
					for (int j = 0; j < i; j++) {
						if (numbers[j] < numbers[i]) {
							if (longestArray[j] + 1 > longestArray[i]) {
								longestArray[i] = longestArray[j] + 1;
							}
						}
					}
				}
				for(int i : longestArray){
					System.out.println(i);
				}
				
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
