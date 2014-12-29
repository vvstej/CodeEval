package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddressHacker {
	public static void main(String[] arg) throws Exception {
		if (arg.length == 0) {
			throw new Exception("No input file specified");
		}
		File f = new File(arg[0]);
		if (!f.exists()) {
			throw new Exception("Given input file not present");
		}
		BufferedReader inputReader = null;
		int maxLines = 0;
		try {
			List<String> maxOccurringIps = new ArrayList<String>();
			inputReader = new BufferedReader(new FileReader(f));
			// String line = inputReader.readLine();
			// int len = line.length();
			String line = null;

			Map<String, Integer> ipMap = new HashMap<String, Integer>();
			int maxOccurances = 0;
			// StringBuilder inputText = new StringBuilder();

			while ((line = inputReader.readLine()) != null) {
				int len = line.length();
				if (len < 100 || len > 300) {
					throw new Exception("Not within limits");
				}
				maxLines++;
				if (maxLines % 200 == 0) {
					System.gc();
				}
				// inputText.append(line);

				for (IPAddress.IP_TYPES ipType : IPAddress.IP_TYPES.values()) {
					Pattern pattern = Pattern.compile(ipType.getPattern());
					Matcher matcher = pattern.matcher(line);

					while (matcher.find()) {
						String ip = matcher.group();
						boolean isIpValid = false;
						String ipInBase255 = null;
						if (ipType == IPAddress.IP_TYPES.BINARY
								|| ipType == IPAddress.IP_TYPES.HEX
								|| ipType == IPAddress.IP_TYPES.OCTAL
								|| ipType == IPAddress.IP_TYPES.DECIMAL) {
							isIpValid = IPAddress.isValidIP(ip, ipType);
							if (!isIpValid)
								continue;
							ipInBase255 = IPAddress
									.convertToBase255(ip, ipType);
							//System.out.println(ipInBase255+"  "+ip);
						} else {
							ipInBase255 = IPAddress
									.convertToBase255(ip, ipType);
							//System.out.println(ipInBase255+"  "+ip);
							isIpValid = IPAddress.isValidIP(ipInBase255,
									IPAddress.IP_TYPES.DOTTED_DECIMAL);
						}
						if (isIpValid) {
							Integer val = ipMap.get(ipInBase255);
							if (val != null) {
								val++;

							} else {
								val = 1;
							}
							ipMap.put(ipInBase255, val);
							if (val > maxOccurances) {
								maxOccurances = val;
								// maxOccuringIP = decimalVal;
							}
						}

					}
				}
				// line = inputReader.readLine();
			}
			// inputText.trimToSize();

			// if (maxLines < 800 || maxLines > 1200) {
			// throw new Exception("Not within limit");
			// }

			Iterator<String> it = ipMap.keySet().iterator();
			while (it.hasNext()) {
				String ipVal = it.next();
				if (ipMap.get(ipVal) == maxOccurances) {
					maxOccurringIps.add(ipVal);
				}
			}
			for (String maxOccuringIP : maxOccurringIps) {
				System.out.println(maxOccuringIP);
			}

		} catch (Exception e) {
			for (StackTraceElement el : e.getStackTrace()) {
				System.out.println(el.getLineNumber());
			}
			System.out.println(e.getLocalizedMessage());
		} finally {
			System.gc();
			inputReader.close();
		}
	}

}

class IPAddress {
	// 1.0.0.0 to 255.255.255.255
	public static final String DOTTED_DECIMAL_IP_PATTERN = "[1-2]{0,1}[0-9]{0,1}[0-9]{1}\\.[1-2]{0,1}[0-9]{0,1}[0-9]{1}\\.[1-2]{0,1}[0-9]{0,1}[0-9]{1}\\.[1-2]{0,1}[0-9]{0,1}[0-9]{1}";
	public static final String DOTTED_HEX_IP_PATTERN = "0x[0-9a-e]{0,1}[0-9a-e]{1}\\.0x[0-9a-e]{0,1}[0-9a-e]{1}\\.0x[0-9a-e]{0,1}[0-9a-e]{1}\\.0x[0-9a-e]{0,1}[0-9a-e]{1}";
	public static final String DOTTED_OCTAL_IP_PATTERN = "[0-7]{4}\\.[0-7]{4}\\.[0-7]{4}\\.[0-7]{4}";
	public static final String DOTTED_BINARY_IP_PATTERN = "[0-1]{8}\\.[0-1]{8}\\.[0-1]{8}.\\[0-1]{8}";
	public static final String OCTAL_IP_PATTERN = "0[0-7]{11}";
	public static final String DECIMAL_IP_PATTERN = "[0-9]{8,10}";
	public static final String HEX_IP_PATTERN = "0[xX][0-9abcdef]{8}";
	public static final String BINARY_IP_PATTERN = "[0-1]{32}";

	public enum IP_TYPES {
		BINARY(BINARY_IP_PATTERN), OCTAL(OCTAL_IP_PATTERN), HEX(HEX_IP_PATTERN), DECIMAL(
				DECIMAL_IP_PATTERN), DOTTED_DECIMAL(DOTTED_DECIMAL_IP_PATTERN), DOTTED_HEX(
				DOTTED_HEX_IP_PATTERN), DOTTED_OCTAL(DOTTED_OCTAL_IP_PATTERN), DOTTED_BINARY(
				DOTTED_BINARY_IP_PATTERN);
		String pattern;

		IP_TYPES(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return this.pattern;
		}

	}

	public static boolean isValidIP(String ip, IP_TYPES ipType)
			throws Exception {
		try {
			switch (ipType) {
			case DOTTED_DECIMAL: {
				return IPAddress.isDottedDecimalValid(ip);
			}
			case DOTTED_HEX: {
				String convertedIp = IPAddress.dottedHexToDottedBase255(ip);
				return IPAddress.isDottedDecimalValid(convertedIp);
			}
			case DOTTED_OCTAL: {
				String convertedIp = IPAddress.dottedOctToDottedBase255(ip);
				return IPAddress.isDottedDecimalValid(convertedIp);
			}
			case DOTTED_BINARY: {
				String convertedIp = IPAddress.dottedBinaryToDottedBase255(ip);
				return IPAddress.isDottedDecimalValid(convertedIp);
			}
			case DECIMAL: {
				long ipVal = Long.parseLong(ip);
				long maxRange = Long.parseLong("4294967295");
				if (ipVal < 16777216 || ipVal > maxRange) {
					return false;
				}

			}

			case OCTAL: {
				long ipVal = Long.parseLong(ip);
				long maxRange = Long.parseLong("037777777777");
				if (ipVal < 100000000 || ipVal > maxRange) {
					return false;
				}
			}
			case HEX: {
				long ipVal = IPAddress.toDecimal(ip, IP_TYPES.HEX);
				long maxRange = Long.parseLong("4294967295");
				if (ipVal < 16777216 || ipVal > maxRange) {
					return false;
				}
			}
			case BINARY: {
				long ipVal = IPAddress.toDecimal(ip, IP_TYPES.BINARY);
				long maxRange = Long.parseLong("4294967295");
				if (ipVal < 16777216 || ipVal > maxRange) {
					return false;
				}
			}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String convertToBase255(String ip, IP_TYPES ipType)
			throws Exception {
		String convertedIp = null;
		switch (ipType) {
		case DOTTED_DECIMAL: {
			return ip;
		}
		case DOTTED_HEX: {
			convertedIp = IPAddress.dottedHexToDottedBase255(ip);
			break;
		}
		case DOTTED_OCTAL: {
			convertedIp = IPAddress.dottedOctToDottedBase255(ip);
			break;
		}
		case DOTTED_BINARY: {
			convertedIp = IPAddress.dottedBinaryToDottedBase255(ip);
			break;
		}
		case DECIMAL: {
			long ipVal = Long.parseLong(ip);
			convertedIp = IPAddress.decimalToBase255Ip(ipVal);
			break;
		}
		case OCTAL: {
			convertedIp = IPAddress.decimalToBase255Ip(IPAddress.toDecimal(ip,
					IP_TYPES.OCTAL));
			break;
		}
		case HEX: {
			convertedIp = IPAddress.decimalToBase255Ip(IPAddress.toDecimal(ip,
					IP_TYPES.HEX));
			break;
		}
		case BINARY: {
			convertedIp = IPAddress.decimalToBase255Ip(IPAddress.toDecimal(ip,
					IP_TYPES.BINARY));
			break;
		}
		}
		return convertedIp;
	}

	private static boolean isDottedDecimalValid(String ip) throws Exception {
		String[] base255IP = ip.split("\\.");
		for (int i = 0; i < base255IP.length; i++) {
			if (Integer.parseInt(base255IP[i]) < 0
					|| Integer.parseInt(base255IP[i]) > 255) {
				return false;
			}
		}
		return true;

	}

	static String dottedHexToDottedBase255(String fromString) throws Exception {
		StringBuilder answerInDottedBase255 = new StringBuilder();
		String[] dottedHexArray = fromString.split("\\.");
		for (int i = 0; i < dottedHexArray.length; i++) {
			String temp = dottedHexArray[i].substring(dottedHexArray[i]
					.indexOf("x") + 1);
			if (temp.length() == 1) {
				answerInDottedBase255.append(1 * (IPAddress
						.findIntegerValueForHex(temp.charAt(0))));
			} else if (temp.length() == 2) {
				answerInDottedBase255.append((16*IPAddress
						.findIntegerValueForHex(temp.charAt(0))) + (IPAddress
						.findIntegerValueForHex(temp.charAt(1))));
			}
			if (i != dottedHexArray.length - 1) {
				answerInDottedBase255.append(".");
			}

		}
		return answerInDottedBase255.toString();
	}

	private static int findIntegerValueForHex(char hex) throws Exception {
		switch (hex) {
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		default:
			return (int) hex - 48;

		}
	}

	static String dottedOctToDottedBase255(String fromString) throws Exception {
		StringBuilder answerInDottedBase255 = new StringBuilder();
		String[] dottedOctArray = fromString.split("\\.");
		for (int i = 0; i < dottedOctArray.length; i++) {
			String temp = dottedOctArray[i];
			long answer = 0;
			for (int j = temp.length() - 1; j > 0; j--) {
				answer += (long) Math.pow(8, (temp.length() - 1 - j))
						* ((int) temp.charAt(j) - 48);
			}
			answerInDottedBase255.append(answer);
			if (i != dottedOctArray.length - 1) {
				answerInDottedBase255.append(".");
			}
		}
		return answerInDottedBase255.toString();
	}

	static String dottedBinaryToDottedBase255(String fromString)
			throws Exception {
		StringBuilder answerInDottedBase255 = new StringBuilder();
		String[] dottedBinaryArray = fromString.split("\\.");
		for (int i = 0; i < dottedBinaryArray.length; i++) {
			String temp = dottedBinaryArray[i];
			long answer = 0;
			for (int j = temp.length() - 1; j > 0; j--) {
				answer += (long) Math.pow(2, (temp.length() - 1 - j))
						* ((int) temp.charAt(j) - 48);
			}
			answerInDottedBase255.append(answer);
			if (i != dottedBinaryArray.length - 1) {
				answerInDottedBase255.append(".");
			}

		}
		return answerInDottedBase255.toString();
	}

	static long toDecimal(String fromString, IP_TYPES ipType) throws Exception {
		long answer = 0;
		switch (ipType) {
		case DOTTED_DECIMAL: {
			answer = dottedBase255ToDecimal(fromString);
			break;
		}
		case DOTTED_HEX: {
			answer = dottedBase255ToDecimal(dottedHexToDottedBase255(fromString));
			break;
		}
		case DOTTED_OCTAL: {
			answer = dottedBase255ToDecimal(dottedOctToDottedBase255(fromString));
			break;
		}
		case DOTTED_BINARY: {
			answer = dottedBase255ToDecimal(dottedBinaryToDottedBase255(fromString));
			break;
		}
		case HEX: {
			String temp = fromString.substring(fromString.indexOf("x") + 1);
			for (int i = temp.length() - 1; i >= 0; i--) {
				answer += (long) Math.pow(16, (temp.length() - 1 - i))
						* findIntegerValueForHex(temp.charAt(i));
			}
			break;
		}

		case OCTAL: {
			for (int i = fromString.length() - 1; i >= 0; i--) {
				answer += (long) Math.pow(8, (fromString.length() - 1 - i))
						* ((int) fromString.charAt(i) - 48);
			}
			break;
		}

		case BINARY: {
			for (int i = fromString.length() - 1; i >= 0; i--) {
				answer += (long) Math.pow(2, (fromString.length() - 1 - i))
						* ((int) fromString.charAt(i) - 48);
			}
			break;
		}
		}
		return answer;
	}

	static long dottedBase255ToDecimal(String fromString) throws Exception {
		long answer = 0;
		String[] dottedDecimalArray = fromString.split("\\.");
		for (int i = 0; i < dottedDecimalArray.length; i++) {
			answer += Math.pow(256, (3 - i))
					* (Integer.parseInt(dottedDecimalArray[i]));
		}
		return answer;
	}

	static String decimalToBase255Ip(long value) throws Exception {
		String[] ip = new String[4];
		// initalize
		for (int i = 0; i < ip.length; i++) {
			ip[i] = "0";
		}
		StringBuilder builder = new StringBuilder();
		long reminder;
		int i = 3;
		while (value > 0) {
			reminder = value % 256;
			value /= 256;
			ip[i--] = Long.toString(reminder);
		}
		for (int j = 0; j < ip.length; j++) {
			builder.append(ip[j]);
			if (j != ip.length - 1) {
				builder.append(".");
			}
		}
		return builder.toString();
	}
}
