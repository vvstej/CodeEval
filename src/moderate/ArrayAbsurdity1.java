package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ArrayAbsurdity1 {

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
				String[] input = line.split(",");

				int arrayLength = Integer.parseInt(input[0].substring(0,
						input[0].length() - 1));
				boolean[] presenceArray = new boolean[Integer
						.parseInt(input[0]) - 1];
				for (int i = 0; i < arrayLength - 1; i++) {
					int no = Integer.parseInt(input[i + 1]);
					if (presenceArray[Integer.parseInt(input[i + 1])]) {
						System.out.println(no);
					} else {
						presenceArray[no] = true;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
