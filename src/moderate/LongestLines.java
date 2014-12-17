package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LongestLines {
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
			int lineCount = 0;
			int noOfOutputLines = Integer.parseInt(line);
			TreeMap<Integer, List<String>> answerMap = new TreeMap<Integer, List<String>>();
			line = inputReader.readLine();
			while (line != null) {				
				List<String> similarLengthList = answerMap.get(line.length());
				if (similarLengthList == null) {
					similarLengthList = new ArrayList<String>();
				}
				similarLengthList.add(line);
				answerMap.put(line.length(), similarLengthList);
				lineCount++;
				line = inputReader.readLine();
			}

			Map<Integer, List<String>> descMap = answerMap.descendingMap();
			int outputVals = noOfOutputLines;
			Iterator<Integer> descMapIterator = descMap.keySet().iterator();
			while (outputVals > 0 && descMapIterator.hasNext()) {
				List<String> lineList = descMap.get(descMapIterator.next());
				Iterator<String> it = lineList.iterator();
				while (it.hasNext() && outputVals > 0) {
					System.out.println(it.next());
					outputVals--;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
