package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SumOfIds {

	public static void main(String[] arg) throws Exception{
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
				Pattern regexp = Pattern.compile("\"id\":\\s*[0-9]{1,2},\\s*\"label\":\\s*\"\\w*\\s*\\w*\"");
				Matcher matcher = regexp.matcher(line);
				int sum = 0;
				while(matcher.find()){
					Pattern regexp1 = Pattern.compile("\"id\":\\s*[0-9]{1,2}");
					Matcher matcher1 = regexp1.matcher(matcher.group());
					if(matcher1.find()){
						sum+=Integer.parseInt(matcher1.group().split(":")[1].trim());
					}
					//System.out.println(matcher.group());
				}
				System.out.println(sum);
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
