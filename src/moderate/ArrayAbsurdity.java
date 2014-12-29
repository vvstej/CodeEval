package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ArrayAbsurdity {

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

			int character = 0;

			while (character != -1) {
				int number = 0;
				while ((character = inputReader.read()) != 59
						&& character != 32 && character != 10
						&& character != -1) {
					number = (number * 10) + (character - 48);
				}
				if (number > 0) {
					boolean[] presenceArray = new boolean[number - 1];
					while ((character = inputReader.read()) != 10
							&& character != -1 && character != 32) {
						int number1 = 0;
						while (character != 44 && character != 10
								&& character != -1 && character != 32) {
							number1 = (number1 * 10) + (character - 48);
							character = inputReader.read();
						}

						if (presenceArray[number1]) {
							System.out.println(number1);
							// isFound=true;
						} else {
							presenceArray[number1] = true;
						}
						if (character == 10 && character != -1)
							break;

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
