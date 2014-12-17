package easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FizzBuzz {
	final static int NO_OF_TESTCASES = 20;
	final static int MIN_XY_RANGE = 1;
	final static int MAX_XY_RANGE = 20;
	final static int MAX_N_RANGE = 100;
	final static int MIN_N_RANGE = 21;
	final static char F = 'F';
	final static char B = 'B';

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
			int lines = 0;
			while (line != null) {
				if (lines > NO_OF_TESTCASES) {
					throw new Exception(
							"No of test cases exceeded, accepted only a max of 20, but given "
									+ lines);
				}
				lines++;
				String[] lineArray = line.split("\\s");
				if (lineArray.length != 3) {
					throw new Exception("Incorrect input format");
				}
				int x = Integer.parseInt(lineArray[0]);
				int y = Integer.parseInt(lineArray[1]);
				int n = Integer.parseInt(lineArray[2]);
				if (x < MIN_XY_RANGE || x > MAX_XY_RANGE || y < MIN_XY_RANGE
						|| y > MAX_XY_RANGE || n < MIN_N_RANGE
						|| n > MAX_N_RANGE) {
					String errorMsg = "Input out of range, input value of x is %s while accepted range is %s-%s,y is %s while accepted range is %s-%s, n is %s while accepted range is %s-%s";
					throw new Exception(String.format(errorMsg, x,
							MIN_XY_RANGE, MAX_XY_RANGE, y, MIN_XY_RANGE,
							MAX_XY_RANGE, n, MIN_N_RANGE, MAX_N_RANGE));
				}
				StringBuffer output = new StringBuffer("");
				for (int i = 1; i <= n; i++) {
					boolean divisibleByX = (i % x == 0);
					boolean divisibleByY = (i % y == 0);
					boolean divisbleByXY = divisibleByX && divisibleByY;
					if (divisbleByXY) {
						output.append(F).append(B);
					} else if (divisibleByX) {
						output.append(F);
					} else if (divisibleByY) {
						output.append(B);
					} else {
						output.append(i);
					}
					output.append(" ");
				}
				System.out.println(output);
				line = inputReader.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (inputReader != null)
				inputReader.close();
		}

	}
}
