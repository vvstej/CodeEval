package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StringRotation {

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
				String[] strings = line.split(",");
				StringBuilder appendedString = new StringBuilder();
				appendedString.append(strings[0]).append(strings[0]);
				if ((appendedString.indexOf(strings[1]) > 0)
						&& (strings[1].length() == strings[0].length())) {
					System.out.println("True");
				} else {
					System.out.println("False");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
