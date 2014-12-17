package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class NoRepetitions {

	public static void main(String[] arg) throws Exception {
		if (arg.length == 0) {
			throw new Exception("No input file specified");
		}
		File f = new File(arg[0]);
		if (!f.exists()) {
			throw new Exception("Given input file not present");
		}
		if (f.length() > 10 * 1000) {
			throw new Exception("Input file size greater than 10KB");
		}
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new FileReader(f));
			int ch = inputReader.read();
			// String line = inputReader.readLine();
			int noOfLines = 0;
			StringBuilder finalText = new StringBuilder();
			int prevChar = -1;
			int prevCharToPrint = -1;
			while (ch != -1) {
				if(noOfLines>40){
					throw new Exception("No of Lines greater than 40, max allowed 40");
				}
				if((char)ch=='\n'){
					noOfLines++;
				}
				char currChar = (char) ch;
				if (currChar != (char) prevChar && prevChar != -1) {
					if (prevCharToPrint != -1) {
						finalText.append((char) prevCharToPrint);
					} else {
						finalText.append((char) currChar);

					}
					prevCharToPrint = currChar;
				}
				if (prevChar == -1) {
					prevCharToPrint = currChar;
				}
				prevChar = currChar;
				ch = inputReader.read();
			}
			finalText.append((char) prevChar);
			System.out.println(finalText);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
