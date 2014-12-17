package easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class NumberMultiples {

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
				String[] lineArray = line.split(",");
				if (lineArray.length != 2) {
					throw new Exception("Incorrect input format");
				}
				int x = Integer.parseInt(lineArray[0]);
				int y = Integer.parseInt(lineArray[1]);
				int count=1;
				int multiplier = x-y;
				while (multiplier>y) {
					multiplier-=y;
					count++;
				}
				if(multiplier>0) System.out.println(y*(count+1));
				else System.out.println(y*count);
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
