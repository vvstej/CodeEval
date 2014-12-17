package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DoubleSquares {
	public static void main(String[] arg) throws Exception {
		if (arg.length == 0) {
			throw new Exception("No input file specified");
		}
		File f = new File(arg[0]);
		if (!f.exists()) {
			throw new Exception("Given input file not present");
		}
		BufferedReader inputReader = null;
		// int firstChar = 0;
		int count = 0;
		int linesRead=0;
		final int maxQuestions = 100;
		final long maxLimit = Long.parseLong("2147483647");
		try {
			inputReader = new BufferedReader(new FileReader(f));
			String line = inputReader.readLine();
			if (line != null) {
				count = Integer.parseInt(line);
				if (count > maxQuestions) {
					throw new Exception("Limit Exceeded");
				}
			}
			line = inputReader.readLine();
			while (line != null || linesRead<maxQuestions) {
				int countOfDoubleSquares = 0;
				long number = Long.parseLong(line);
				if (number > maxLimit) {
					line = inputReader.readLine();
					continue;
				}

				double val = Math.sqrt(number);
				boolean isWholeSqrt = isWholeSqrt(val);
//				if (isWholeSqrt) {
//					countOfDoubleSquares++;
//				}
				double leftVal = Math.floor(val);
				double rightVal = -1;
				while (leftVal > rightVal) {
					double reminder = number - (leftVal * leftVal);
					rightVal = Math.sqrt(reminder);
					if (isWholeSqrt(rightVal)) {
						countOfDoubleSquares++;
					}
					leftVal--;
				}
				System.out.println(countOfDoubleSquares);
				linesRead++;
				line = inputReader.readLine();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	private static boolean isWholeSqrt(double val) {
		String[] array = new Double(val).toString().split("\\.");
		if (array[1].equals("0"))
			return true;
		return false;
	}
}
