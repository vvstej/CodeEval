package linkedin;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String res;
		String _ranges;
		_ranges = in.nextLine();

		res = compactAndSort(_ranges);
		System.out.println(res);

	}

	static String compactAndSort(String ranges) {
		try {
			if (!ranges.trim().equals("")) {
				String[] rangesArray = ranges.split(",");
				if (rangesArray.length > 0) {
					Map<Integer, Pair<Integer>> sortedRangesList = sortRanges(rangesArray);
					List<Pair<Integer>> sortedAndCompactedList = compactRanges(sortedRangesList);
					StringBuilder rangesBuilder = new StringBuilder();
					if (sortedAndCompactedList.size() == 0) {
						return "";
					}
					for (Pair<Integer> pair : sortedAndCompactedList) {
						rangesBuilder.append(pair.getX() + ":" + pair.getY());
						rangesBuilder.append(",");
					}
					return rangesBuilder.substring(0,
							rangesBuilder.length() - 1);
				}
			}
			return "";
		} catch (Exception e) {
			return "Exception during compact and sort" + e;
		}

	}

	// 3 ways to compact 1) Overlapping ranges ex : 1:4,2:5=> 1:5 2) Sequence
	// Ranges ex 1:4,5:6 => 1:6 3) Missing ranges 1:4, 6:10
	private static List<Pair<Integer>> compactRanges(
			Map<Integer, Pair<Integer>> sortedRangesList) {
		List<Pair<Integer>> compactedRanges = new ArrayList<Pair<Integer>>();
		Iterator<Integer> keyIterator = sortedRangesList.keySet().iterator();
		Integer currKey = keyIterator.next();
		Pair<Integer> currPair = sortedRangesList.get(currKey);
		if (!keyIterator.hasNext()) {
			compactedRanges.add(currPair);
		}
		while (keyIterator != null && keyIterator.hasNext()) {
			Integer nextKey = keyIterator.next();
			Pair<Integer> nextPair = sortedRangesList.get(nextKey);
			Pair<Integer> postMergePair = null;
			if ((nextKey >= currPair.getX() && nextKey <= currPair.getY())
					|| (nextKey == currPair.getY() + 1)) {
				// overlapping range
				Integer y = nextPair.getY() > currPair.getY() ? nextPair.getY()
						: currPair.getY();
				postMergePair = new Pair<Integer>(currKey, y);
				if (compactedRanges.size() > 0) {
					compactedRanges.remove(compactedRanges.size() - 1);
				}
				//compactedRanges.add(postMergePair);
				currPair = postMergePair;
			} else {
				compactedRanges.add(currPair);
				compactedRanges.add(nextPair);
				currKey = nextKey;
				currPair = nextPair;
			}
		}
		if(compactedRanges.isEmpty()){
			compactedRanges.add(currPair);
		}
		return compactedRanges;
	}

	private static Map<Integer, Pair<Integer>> sortRanges(String[] rangesArray) {
		Map<Integer, Pair<Integer>> sortedMap = new TreeMap<Integer, Pair<Integer>>();
		for (int i = 0; i < rangesArray.length; i++) {
			String pairAsString = rangesArray[i].trim();
			String[] pairArray = pairAsString.split(":");
			if (pairArray.length == 2) {
				if (!pairArray[0].trim().equals("")
						&& !pairArray[1].trim().equals("")) {
					Pair<Integer> pair = new Pair<Integer>(
							Integer.parseInt(pairArray[0].trim()),
							Integer.parseInt(pairArray[1].trim()));
					Pair<Integer> existingPair = sortedMap.get(pair.getX());
					if (existingPair != null) {
						if (!(existingPair.getY() >= pair.getY())) {
							sortedMap.put(pair.getX(), pair);
						}
					} else {
						sortedMap.put(pair.getX(), pair);
					}

				}

			}
		}

		return sortedMap;
	}

	static class IntegerPairComparator implements Comparator<Pair<Integer>> {
		@Override
		public int compare(Pair<Integer> o1, Pair<Integer> o2) {
			return o1.getX().compareTo(o2.getX());
		}

	}

	static class Pair<T> {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (x == null) {
				if (other.x != null)
					return false;
			} else if (!x.equals(other.x))
				return false;
			return true;
		}

		T x;
		T y;

		public Pair(T x, T y) {
			this.x = x;
			this.y = y;
		}

		public Pair() {
		}

		public T getX() {
			return this.x;
		}

		public void setX(T x) {
			this.x = x;
		}

		public T getY() {
			return this.y;
		}

		public void setY(T y) {
			this.y = y;
		}
	}
}
