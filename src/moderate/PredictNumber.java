package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class PredictNumber {
	public static void main(String[] arg) throws Exception {
		if (arg.length == 0) {
			throw new Exception("No input file specified");
		}
		File f = new File(arg[0]);
		if (!f.exists()) {
			throw new Exception("Given input file not present");
		}
		BufferedReader inputReader = null;
		//int firstChar = 0;
		int secondChar = 1;
		try {
			inputReader = new BufferedReader(new FileReader(f));
			String line = inputReader.readLine();
			while (line != null) {
				long val = Long.parseLong(line.trim());
				long maxLimit = Long.parseLong("3000000000");
				if(val > maxLimit || val < 0){
					System.out.println("Number exceeds limit");
					line = inputReader.readLine();
					continue;
				}
				int noOfTransformations = 0;
				double pow = 1;
				while (val > secondChar) {
					pow = Math.floor(calculateBase2Log(val));
					// pow = Math.floor(pow);
					//pow -= 1;
					val -= Math.pow(2, pow);
					noOfTransformations++;
				}
				if (noOfTransformations>0) {
					System.out.println(applyTransform((int)val,
							noOfTransformations));
				}
				else{
					System.out.println(val);
				}
				line = inputReader.readLine();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	private static double calculateBase2Log(long val) {
		double logVal = Math.log(val) / Math.log(2);
		return logVal;
	}

	private static int applyTransform(int seedVal, int noOfTransformations) {
		while (noOfTransformations > 0) {
			switch (seedVal) {
			case 0:
				seedVal = 1;
				break;
			case 1:
				seedVal = 2;
				break;
			case 2:
				seedVal = 0;
				break;
			}
			noOfTransformations--;
		}
		return seedVal;
	}
}
