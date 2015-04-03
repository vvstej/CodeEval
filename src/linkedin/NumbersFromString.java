package linkedin;

import java.util.ArrayList;
import java.util.List;

public class NumbersFromString {

	public static void main(String[] arg) {
		String str = "dFD$#23+++12@#T1234;/.,10";
		int result = result(str);
		System.out.println(result);
	}

	public static int result(String input) {
		int length = input.length();
		List<Integer> numbers = new ArrayList();
		boolean isPrevCharNum = false;
		StringBuilder numberBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
				if (!isPrevCharNum) {
					numberBuilder = new StringBuilder();
				}
				numberBuilder.append(input.charAt(i));
				isPrevCharNum = true;
			} else {
				if (isPrevCharNum) {
					numbers.add(Integer.parseInt(numberBuilder.toString()));
					numberBuilder.delete(0, numberBuilder.length());
				}
				isPrevCharNum = false;
			}
			if (i == length - 1 && numberBuilder.length() > 0) {
				numbers.add(Integer.parseInt(numberBuilder.toString()));
			}
		}
		int sum = 0;
		for (int i = 0; i < numbers.size(); i++) {
			sum += numbers.get(i);
		}
		return sum;
	}
}
