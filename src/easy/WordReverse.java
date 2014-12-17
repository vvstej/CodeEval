package easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WordReverse {

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
				String[] words = line.split("\\s+");
				//StringBuilder lineBuilder = new StringBuilder("");
				if (words != null && words.length > 0) {
					for (int i = words.length - 1; i >= 0; i--) {
						//lineBuilder.append(words[i]);
						System.out.print(words[i]);
						if (i != 0){
							System.out.print(" ");
						}else{
							System.out.println("");
						}
							
					}
				}
				line=inputReader.readLine();
			}
		} catch (Exception e) {

		}
		finally{
			inputReader.close();
		}
	}
}
