package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// dyamic programming solution
public class SequenceTransformation1 {
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
			int testCases = 0;
			while (line != null && testCases < 50) {
				testCases++;
				String[] input = line.split("\\s+");
				final String binaryInput = input[0];
				final String stringInput = input[1];
				int bLength = binaryInput.length();
				int sLength = stringInput.length();
				if (bLength < 1 || bLength > 150) {
					throw new Exception("Wrong length of input binary string");
				}

				if (sLength < 1 || sLength > 1000) {
					System.out.println(sLength);
					throw new Exception("Wrong length of input string length");
				}
				boolean[][] truthArray = new boolean[bLength + 1][sLength + 1];
				truthArray[0][0] = true;
				for (int i = 1; i < sLength; i++) {
					truthArray[0][i] = false;
				}
				for (int i = 1; i < bLength; i++) {
					truthArray[i][0] = false;
				}
				for (int i = 1; i < bLength + 1; i++) {
					for (int j = 1; j < sLength + 1; j++) {
						if (truthArray[i - 1][j - 1] == true) {
							if (binaryInput.charAt(i - 1) == '1') {
								if (stringInput.charAt(j - 1) == 'A'
										|| stringInput.charAt(j - 1) == 'B') {
									truthArray[i][j] = true;
								}
							} else {
								if (stringInput.charAt(j - 1) == 'A') {
									truthArray[i][j] = true;
								} else {
									truthArray[i][j] = false;
								}
							}
						} else {
							boolean answer = truthArray[i][j - 1];
							if (j > 1) {
								answer = answer
										&& (stringInput.charAt(j - 1) == stringInput
												.charAt(j - 2));
							} else {
								answer = false;
							}
							truthArray[i][j] = answer;
						}
					}
				}
				if (truthArray[bLength][sLength]) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
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
