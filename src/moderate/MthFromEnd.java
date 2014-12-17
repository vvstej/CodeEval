package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MthFromEnd {
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
				String[] lineArray = line.split("\\s");
				int n = Integer.parseInt(lineArray[lineArray.length-1]);
				if(n > lineArray.length-1){
					line = inputReader.readLine();
					continue;
				}
				System.out.println(lineArray[lineArray.length-1-n]);
				line = inputReader.readLine();
				
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
