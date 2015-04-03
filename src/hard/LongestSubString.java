package hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LongestSubString {
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
				int longest = -1;
				int[][] subStringArray = new int[line.length() + 1][line
						.length() + 1];
				for (int i = 0; i <= line.length(); i++) {
					for (int j = 0; j <= line.length(); j++) {
						subStringArray[i][j] = 0;
					}

				}
				for (int i = 1; i <= line.length(); i++) {
					for (int j = 1; j <= line.length(); j++) {
						if (line.charAt(i - 1) == line.charAt(j - 1)) {
							if (i < j) {
								subStringArray[i][j] = subStringArray[i - 1][j - 1] + 1;
								if (longest < subStringArray[i][j]) {
									longest = subStringArray[i][j];
								}
							}
						}
					}
				}
				// System.out.println(longest);
				int longestIIndex = 0, longestJIndex = 0;
				boolean isFound = false;
				for (int i = 0; i <= line.length(); i++) {
					for (int j = 0; j <= line.length(); j++) {
						if (subStringArray[i][j] == longest) {
							longestIIndex = i - 1;
							longestJIndex = j - 1;
							isFound = true;
							break;
						}
					}
					if (isFound)
						break;
				}
				// System.out.println(longestIIndex+" "+longestJIndex);
				if (longestJIndex < 1) {
					System.out.println("NONE");
				} else {
					if (longest == 1 && line.charAt(longestJIndex) == ' ') {
						System.out.println("NONE");
					} else {
						for (int index = longestJIndex - longest + 1; index <= longestJIndex; index++) {
							if (line.charAt(index) != ' ')
								System.out.print(line.charAt(index));
						}
						System.out.println();
					}

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
