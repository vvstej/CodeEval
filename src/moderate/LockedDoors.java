package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LockedDoors {
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
				int noOfDoors = Integer.parseInt(lineArray[0]);
				boolean[] doorArray = new boolean[noOfDoors + 1];
				int noOfIterations = Integer.parseInt(lineArray[1]);
				for (int door = 1; door <= noOfDoors; door++) {
					doorArray[door] = true;
				}
				while (noOfIterations > 1) {
					for (int door = 1; door <= noOfDoors; door++) {
						if (door % 2 == 0) {
							if (doorArray[door] == true) {
								doorArray[door] = false;
							}
						}
						if (door % 3 == 0) {
							doorArray[door] = !doorArray[door];
						}

					}
					noOfIterations--;
				}
				doorArray[noOfDoors] = !doorArray[noOfDoors];
				int finalCount = 0;
				for (int i = 1; i <= noOfDoors; i++) {
					if (doorArray[i]) {
						finalCount++;
					}
				}
				System.out.println(finalCount);
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
