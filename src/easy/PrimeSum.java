package easy;

public class PrimeSum {

	public static void main(String[] arg) {
		int sum = 0;
		int count = 0;
		for (int i = 2;; i++) {
			if (isPrime(i)) {
				count++;
				sum += i;
			}
			if (count == 1000)
				break;
		}
		System.out.println(sum);

	}

	private static boolean isPrime(double n) {
		if(n==2 || n==3 || n==5 || n==7 || n==11 || n==13 || n==17 || n==19 || n==23 || n==29) return true;
		if ((n % 2 == 0) || (n % 3 == 0) || (n % 5 == 0) || (n % 7 == 0)
				|| (n % 11 == 0) || (n % 13 == 0) || (n % 17 == 0) || (n % 19 == 0) || (n % 23 == 0) || (n % 29 == 0))
			return false;
		double sqrt = Math.sqrt(n);
		double ceil = Math.ceil(sqrt);
		for (int i = 3; i <= ceil; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
