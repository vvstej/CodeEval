package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SumOfContiguousIntegers {
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
				int max = -500;
				String[] lineArray = line.split(",");
				int[] numbers = new int[lineArray.length];
				int[] sumArray = new int[lineArray.length];
				int k = 0;
				for (String no : lineArray) {
					numbers[k++] = Integer.parseInt(no);
				}
				for (int i = 0; i < lineArray.length; i++) {
					sumArray[i] = numbers[i];
				}
				for (int i = 1; i < numbers.length; i++) {
					if (sumArray[i - 1] + numbers[i] > sumArray[i]) {
						sumArray[i] = sumArray[i - 1] + numbers[i];
					}
				}

				for (int i = 0; i < numbers.length; i++) {
					if (sumArray[i] > max) {
						max = sumArray[i];
					}
				}
				System.out.println(max);

				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
