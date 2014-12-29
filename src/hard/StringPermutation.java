package hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringPermutation {

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
			while ((line = inputReader.readLine()) != null) {
				List<String> permutations = permute(line);
				Collections.sort(permutations);
				// Collections.reverseOrder();
				for (int i = 0; i < permutations.size(); i++) {
					if (i == 0) {
						System.out.print(permutations.get(i));
					} else {
						System.out.print("," + permutations.get(i));
					}

				}
				System.out.println();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	private static List<String> permute(String line) {
		List<String> permutations = new ArrayList<String>();
		if (line.length() == 2) {
			permutations.add(line.substring(0, 1) + line.substring(1, 2));
			permutations.add(line.substring(1, 2) + line.substring(0, 1));
			return permutations;
		}
		for (int i = 0; i < line.length(); i++) {
			StringBuilder builder = new StringBuilder();
			builder.append(line.substring(0, i)).append(
					line.substring(i + 1, line.length()));
			String charAtIndex = line.substring(i, i + 1);
			List<String> recursivePermutations = permute(builder.toString());
			Iterator<String> it = recursivePermutations.iterator();
			while (it.hasNext()) {
				permutations.add(charAtIndex + it.next());
			}

		}
		return permutations;

	}
}
