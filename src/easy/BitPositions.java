package easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BitPositions {

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
				if (lineArray.length != 3) {
					throw new Exception("Incorrect input format");
				}
				int number = Integer.parseInt(lineArray[0]);
				int b1 = Integer.parseInt(lineArray[1]);
				int b2 = Integer.parseInt(lineArray[2]);
				int b1Num = (int)Math.pow(2, b1-1);
				int b2Num = (int)Math.pow(2, b2-1);
				boolean b1Result = false;
				boolean b2Result = false;
				if((number&b1Num)!=0){
					b1Result = true;
				}
				if((number&b2Num)!=0){
					b2Result = true;
				}
				System.out.println(b1Result==b2Result);
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
