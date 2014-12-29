package easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRecovery {

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
			int maxLines = 0;
			while ((line = inputReader.readLine()) != null && maxLines<=40) {
				maxLines++;
				String[] input = line.split(";");
				String[] inputWordSequence = input[0].split("\\s");
				String[] inputNumberSequence = input[1].split("\\s");
				String[] output = new String[inputWordSequence.length];
				int inputWordSequenceLen = inputWordSequence.length;
				int inputNumberSequenceLen = inputNumberSequence.length;
				int diff = inputWordSequenceLen - inputNumberSequenceLen;

				for (int i = 0; i < inputWordSequence.length; i++) {
					output[i] = "";
				}
				for (int i = 0; i < inputNumberSequence.length; i++) {
					int index = Integer.parseInt(inputNumberSequence[i]);
					output[index - 1] = inputWordSequence[i];
				}

				int diffIndex = inputWordSequenceLen - diff;

				for (int i = 0; i < inputWordSequenceLen; i++) {
					if (output[i].equals("")) {
						output[i] = inputWordSequence[diffIndex];
						diffIndex--;
					}
				}
				for (int i = 0; i < inputWordSequence.length; i++) {
					if (i == inputWordSequence.length - 1) {
						System.out.println(output[i]);
					} else {
						System.out.print(output[i] + " ");
					}

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
