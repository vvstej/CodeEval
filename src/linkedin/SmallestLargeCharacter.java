package linkedin;

public class SmallestLargeCharacter {

	public static void main(String[] arg) {
		String s = "abcdefghijklmnop";
		char answer = findInsPoint(s, 'y');
		System.out.println(answer);
	}

	private static char findInsPoint(String s, char c) {
		int index = bSearch(s, c, 0, s.length() - 1);
		return s.charAt(index);
	}

	private static int bSearch(String s, char target, int begin, int end) {
		if (begin == end - 1) {
			if (target <= s.charAt(begin)) {
				if (target < s.charAt(begin)) {
					return begin;
				}
				return end;
			} else if (target == s.charAt(end)) {
				int toReturn = end + 1 == s.length() ? 0 : end + 1;
				return toReturn;
			} else if (target > s.charAt(end)) {
				// out of range case only
				return 0;
			} else {
				return end;
			}
		}
		int midPoint = (end + begin) / 2;
		if (target <= s.charAt(midPoint)) {
			return bSearch(s, target, begin, midPoint);
		} else {
			return bSearch(s, target, midPoint, end);
		}
	}
}
