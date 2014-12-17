package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
				isMatches(0, sLength - 1, 0, bLength - 1, stringInput,
						binaryInput);

				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	private static boolean isMatches(int startStringIndex, int endStringIndex,
			int startBinaryIndex, int endBinaryIndex, String stringInput,
			String binaryInput) {
		int stringLength = stringInput.length();
		int binaryLength = binaryInput.length();
		if (startBinaryIndex == endBinaryIndex) {
			// base case
			char ch = binaryInput.charAt(startBinaryIndex);
			int prev = -1;
			for (int i = startStringIndex; i <= endStringIndex; i++) {
				if (prev!=-1 && stringInput.charAt(i) == stringInput.charAt(prev)) {
					if(ch == '0'){
						if(!(stringInput.charAt(i) == 'A')){
							return false;
						}
					}
					prev = i;
				} else {
					return false;
				}
			}
			return true;

		}

		for (int index = endStringIndex - 1; index > startStringIndex; index--) {
			if (isMatches(startStringIndex, index, startBinaryIndex,
					endBinaryIndex - 1, stringInput, binaryInput)) {
				if (isMatches(index + 1, stringLength - 1, endBinaryIndex,
						binaryLength - 1, stringInput, binaryInput)) {
					System.out.println("True");
					return true;
				} else {
					System.out.println("False");
					return false;
				}
			}
		}
		return false;
	}
}
