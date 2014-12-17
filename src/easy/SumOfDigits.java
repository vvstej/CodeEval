package easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SumOfDigits {

	public static void main(String[] arg) throws Exception{
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
				if (lineArray.length != 1) {
					throw new Exception("Incorrect input format");
				}
				int number = Integer.parseInt(lineArray[0]);
				int quotient = number;
				int sum =0 ;
				while(quotient>=10){
					sum+=quotient%10;
					quotient/=10;
				}
				sum+=quotient;
				System.out.println(sum);
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
