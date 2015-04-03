package hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UglyNumbers {

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
				int countOfUgly = 0;
				List<Long> permutations = permute(line);
				// Collections.reverseOrder();
				Iterator<Long> it = permutations.iterator();
				while (it.hasNext()) {
					long num = it.next();
					if (isNumberUgly(num)) {
						countOfUgly++;
					}
				}
				System.out.println(countOfUgly);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	private static boolean isNumberUgly(long num) {
		if (num == 0) {
			return true;
		}
		if (num % 2 == 0 || num % 3 == 0 || num % 5 == 0 || num % 7 == 0) {
			return true;
		}
		return false;
	}

	private static List<Long> permute(String line) {
		List<Long> permutations = new ArrayList<Long>();
		if (line.length() == 2) {
			permutations.add(Long.parseLong(line.substring(0, 1))
					+ Long.parseLong(line.substring(1, 2)));
			permutations.add(Long.parseLong(line.substring(0, 1))
					- Long.parseLong(line.substring(1, 2)));
			permutations.add(Long.parseLong(line));
			return permutations;
		}
		if (line.length() == 1) {
			permutations.add(Long.parseLong(line));
			return permutations;
		}
		if (line.length() > 0)
			permutations.add(Long.parseLong(line));
		for (int i = 0; i < line.length(); i++) {
			String base = line.substring(0, i + 1);
			String rest = line.substring(i + 1, line.length());
			List<Long> recursivePermutations = permute(rest);
			Iterator<Long> it = recursivePermutations.iterator();
			while (it.hasNext()) {
				long num = it.next();
				permutations.add(Long.parseLong(base) + num);
				permutations.add(Long.parseLong(base) - num);
			}
		}
		return permutations;

	}
}
