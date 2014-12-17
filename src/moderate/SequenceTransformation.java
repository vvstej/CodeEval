package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.prefs.BackingStoreException;

public class SequenceTransformation {
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
			int testCases=0;
			while (line != null && testCases<50) {
				testCases++;
				String[] input = line.split("\\s+");
				final String binaryInput = input[0];
				final String stringInput = input[1];
				int bLength = binaryInput.length();
				int sLength = stringInput.length();
				
				if(bLength<1 || bLength>150){
					throw new Exception("Wrong length of input binary string");
				}
				
				if(sLength<1 || sLength>1000){
					System.out.println(sLength);
					throw new Exception("Wrong length of input string length");
				}
				int solutionArray[] = new int[bLength];
				boolean solutionFound = false;
				int binaryPointer = 0;
				int stringPointer = 0;
				int prev = -1;
				boolean shouldBackTrack = false;
				while (!solutionFound) {
					prev = stringPointer;
					shouldBackTrack = false;
					if (binaryPointer != bLength
							&& stringPointer == sLength) {
						shouldBackTrack = true;
					}
					boolean truthValFor1 = !shouldBackTrack
							&& truthValFor1(binaryInput, stringInput,
									binaryPointer, stringPointer);
					boolean truthValFor0 = !shouldBackTrack
							&& truthValFor0(binaryInput, stringInput,
									binaryPointer, stringPointer);
					if (truthValFor1 || truthValFor0) {
						shouldBackTrack = false;
						if (prev == -1) {
							stringPointer++;
							prev++;
						} else {
							while (stringPointer < sLength
									&& stringInput.charAt(prev) == stringInput
											.charAt(stringPointer)) {
								prev = stringPointer;
								stringPointer++;
//								if(sLength-stringPointer<bLength-binaryPointer){
//									shouldBackTrack = true;
//									break;
//								}

							}
							//if(!shouldBackTrack){
								solutionArray[binaryPointer++] = stringPointer - 1;
								if (binaryPointer == bLength) {
									solutionFound = true;
									System.out.println("True");
								}
							//}
							
						}
					} else {
						shouldBackTrack = true;
					}

					if (shouldBackTrack) {
						// time to backtrack
						int backTrackCount = binaryPointer - 1;

						while (backTrackCount >= 0) {
							boolean solutionEncroached = false;
							binaryPointer = (binaryPointer == 0) ? binaryPointer
									: --binaryPointer;
							int whereToStartBinary = binaryPointer; // prev
																	// bPointer
							int whereToStartString = solutionArray[whereToStartBinary]; //
							whereToStartString -= 1;
							if (whereToStartString < 0) {
								System.out.println("False");
								solutionFound = true;
								break;
							}

							if (!(truthValFor0(binaryInput, stringInput,
									whereToStartBinary, whereToStartString) || truthValFor1(
									binaryInput, stringInput,
									whereToStartBinary, whereToStartString))) {
								// encroach
								solutionEncroached = true;

							}
							if (whereToStartBinary > 0
									&& (whereToStartString <= solutionArray[whereToStartBinary - 1])) {
								solutionEncroached = true;
							}
							if (!solutionEncroached) {
								solutionArray[whereToStartBinary] = whereToStartString;
								whereToStartBinary++;
								whereToStartString++;
								binaryPointer = whereToStartBinary;
								stringPointer = whereToStartString;
								break;
							}
							backTrackCount--;
						}
						if (backTrackCount < 0) {
							System.out.println("False");
							solutionFound = true;
						}

					}

					// otherwise wrong binary character read for corresponding
					// string character like
					// 0 and B , so either backtrack or if all solutions
					// explored declare no match
				}
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	private static boolean truthValFor0(String binaryInput, String stringInput,
			int binaryPointer, int stringPointer) {
		boolean truthValFor0 = binaryInput.charAt(binaryPointer) == '0'
				&& stringInput.charAt(stringPointer) == 'A';
		return truthValFor0;
	}

	private static boolean truthValFor1(String binaryInput, String stringInput,
			int binaryPointer, int stringPointer) {
		boolean truthValFor1 = binaryInput.charAt(binaryPointer) == '1'
				&& (stringInput.charAt(stringPointer) == 'A' || stringInput
						.charAt(stringPointer) == 'B');
		return truthValFor1;
	}

}
