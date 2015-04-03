package hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ChessMetric {

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
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	public long howMany(int size, int[] start, int[] end, int numMoves) {
		
		return numMoves;

	}

	public boolean isPointWithinBoundaries(int a, int b, int size) {
		if (a < 1 || b < 1)
			return false;
		if (a > size - 1 || b > size - 1)
			return false;
		return true;
	}
}
