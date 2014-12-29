package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sudoku {

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
			String line = null;
			while ((line = inputReader.readLine()) != null) {
				String[] inputLine = line.split(";");
				String[] input = inputLine[1].split(",");
				int n = Integer.parseInt(inputLine[0]);
				if (!(n == 4 || n == 9)) {
					System.out.println("False");
					continue;
				}
				int[][] twoDArray = new int[n][n];
				int k = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n && k < input.length; j++) {
						twoDArray[i][j] = Integer.parseInt(input[k]);
						k++;
					}
				}
				// column wise uniqueness
				// row wise uniqueness
				// n*n wise uniquness
				boolean testFailed = false;
				Map<Integer, Boolean> presenceMap = new HashMap<Integer, Boolean>();
				for (int j = 0; j < n; j++) {
					for (int i = 0; i < n; i++) {
						if (presenceMap.get(twoDArray[i][j]) != null
								&& presenceMap.get(twoDArray[i][j])) {
							testFailed = true;
						} else {
							presenceMap.put(twoDArray[i][j], true);
						}

					}
					presenceMap.clear();
				}
				presenceMap.clear();
				if (testFailed) {
					System.out.println("False");
					continue;
				}
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (presenceMap.get(twoDArray[i][j]) != null
								&& !presenceMap.get(twoDArray[i][j])) {
							testFailed = true;
						} else {
							presenceMap.put(twoDArray[i][j], true);
						}

					}
					presenceMap.clear();
				}
				presenceMap.clear();
				if (testFailed) {
					System.out.println("False");
					continue;
				}

				for (int i = 0; i < Math.sqrt(n); i = i + (int) Math.sqrt(n)) {
					presenceMap.clear();
					for (int j = 0; j < Math.sqrt(n); j = j
							+ (int) Math.sqrt(n)) {
						presenceMap.clear();
						for (int m = i; m < i + Math.sqrt(n); m++) {
							for (int l = j; l < j + Math.sqrt(n); l++) {
								if (presenceMap.get(twoDArray[m][l]) != null
										&& !presenceMap.get(twoDArray[m][l])) {
									testFailed = true;
								} else {
									presenceMap.put(twoDArray[m][l], true);
								}
							}
							// presenceMap.clear();
						}
					}
				}

				if (testFailed) {
					System.out.println("False");
					continue;
				} else {
					System.out.println("True");
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
