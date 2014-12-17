package easy;

public class PrimePalindrome {

	final static int NUM = 1000;

	public static void main(String[] arg) {
		double sqrt = Math.sqrt(1000);
		double floor = Math.floor(sqrt);
		System.out.println(findPrimePalindrome(floor, NUM));
	}

	private static int findPrimePalindrome(double floor, double maxToVerify) {
		boolean isPalindromeFound = false;
		double max = -1;
		if (floor == 1) {
			return 2;
		}
		double square = floor * floor;
		for (double i = square; i < maxToVerify; i++) {
			boolean isPrime = isPrime(i);
			if (isPrime) {
				if (isPalindrome(i)) {
					if (i > max) {
						max = i;
					}
					isPalindromeFound = true;
				}
			}
		}
		if (!isPalindromeFound) {
			return findPrimePalindrome(floor - 1, square);
		}
		return (int)max;
	}

	private static boolean isPrime(double n) {
		if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 == 0)
			return false;
		double sqrt = Math.sqrt(n);
		double ceil = Math.ceil(sqrt);
		for (int i = 3; i <= ceil; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	private static boolean isPalindrome(double n) {
		String val = Integer.toString((int)n);
		String reverse = new StringBuilder(val).reverse().toString();
		if (val.equals(reverse))
			return true;
		return false;
	}
}
