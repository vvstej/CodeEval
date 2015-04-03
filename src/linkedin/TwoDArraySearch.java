package linkedin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TwoDArraySearch {

	public static void main(String[] arg) throws Exception {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(arg[0])));
			int[][] inputArray = new int[3][4];
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				String[] lineArray = line.split(" ");
				for (int j = 0; j < lineArray.length; j++) {
					inputArray[i][j] = Integer.parseInt(lineArray[j]);
				}
				i++;
			}
			Pair<Integer> answer = findTarget(inputArray,
					Integer.parseInt(arg[1]));
			if (answer.getX() == -1) {
				System.out.println("Outside range of the 2d array");
			} else if (answer.getY() == -1) {
				System.out.println("Not Present");
			} else {
				System.out.print("X Coordinate is " + answer.getX()
						+ " and Y coordinate is " + answer.getY());
			}
		} finally {
			reader.close();
		}

	}

	private static Pair<Integer> findTarget(int[][] inputArray, int target) {
		// do binary search column wise on the last column and return the row in
		// which the element is present
		int searchRow = searchLastColumn(inputArray, target, 0,
				inputArray.length - 1);
		if (searchRow == -1) {
			return new Pair<Integer>(-1, 0);
		}
		int searchCol = searchTargetRow(inputArray[searchRow], target, 0,
				inputArray[0].length - 1);
		Pair<Integer> pair = new Pair<Integer>(searchRow, searchCol);
		return pair;

	}

	private static int searchTargetRow(int[] input1DArray, int target,
			int colBeg, int colEnd) {
		if (colBeg == colEnd - 1) {
			if (target == input1DArray[colBeg]) {
				return colBeg;
			} else if (target == input1DArray[colEnd]) {
				return colEnd;
			} else {
				// non existence
				return -1;
			}
		}
		int midPoint = (colEnd + colBeg) / 2;
		if (target >= input1DArray[midPoint]) {
			return searchTargetRow(input1DArray, target, midPoint, colEnd);
		} else {
			return searchTargetRow(input1DArray, target, colBeg, midPoint);
		}
	}

	private static int searchLastColumn(int[][] inputArray, int target,
			int rowBeg, int rowEnd) {
		if (rowBeg == rowEnd - 1) {
			if (target > inputArray[rowBeg][inputArray[0].length - 1]
					&& target <= inputArray[rowEnd][inputArray[0].length - 1]) {
				return rowEnd;
			} else if (target <= inputArray[rowBeg][inputArray[0].length - 1]) {
				return rowBeg;
			} else {
				// non existence, outside the boundary of the last row.
				return -1;
			}
		}

		int midPoint = (rowEnd + rowBeg) / 2;
		if (target > inputArray[midPoint][inputArray[0].length - 1]) {
			return searchLastColumn(inputArray, target, midPoint, rowEnd);
		} else {
			return searchLastColumn(inputArray, target, rowBeg, midPoint);
		}
	}

}


