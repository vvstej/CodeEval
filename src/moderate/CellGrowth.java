package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CellGrowth {

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
			int size = 100;
			int row = 0;
			String[][] cells = new String[size][size];
			String[][] resultCells = new String[size][size];
			// initialize cells
			while (line != null) {
				// String[] tempArray = line.split("\\s");
				for (int i = 0; i < size; i++) {
					cells[row][i] = Character.toString(line.charAt(i));
				}
				row++;
				line = inputReader.readLine();
			}
			int iteraton = 0;
			while (iteraton < 10) {
				for (int r = 0; r < cells.length; r++) {
					for (int c = 0; c < cells.length; c++) {
						int aliveNeighbourCount = 0;
						// examine neighbours and determine alive or dead
						if (r - 1 >= 0) {
							if (c - 1 >= 0) {
								if (cells[r - 1][c - 1].equals("*")) {
									aliveNeighbourCount++;
								}
							}
							if (c + 1 < cells.length) {
								if (cells[r - 1][c + 1].equals("*")) {
									aliveNeighbourCount++;
								}
							}
							// third r-1,c
							if (cells[r - 1][c].equals("*")) {
								aliveNeighbourCount++;
							}
						}
						if (r + 1 < cells.length) {
							if (c - 1 >= 0) {
								if (cells[r + 1][c - 1].equals("*")) {
									aliveNeighbourCount++;
								}
							}
							if (c + 1 < cells.length) {
								if (cells[r + 1][c + 1].equals("*")) {
									aliveNeighbourCount++;
								}
							}
							// third r+1,c
							if (cells[r + 1][c].equals("*")) {
								aliveNeighbourCount++;
							}
						}
						if (c - 1 >= 0) {
							if (cells[r][c - 1].equals("*")) {
								aliveNeighbourCount++;
							}
						}
						if (c + 1 < cells.length) {
							if (cells[r][c + 1].equals("*")) {
								aliveNeighbourCount++;
							}
						}
						if (cells[r][c].equals("*")) {
							if (aliveNeighbourCount < 2
									|| aliveNeighbourCount > 3) {
								resultCells[r][c] = ".";
							} else {
								resultCells[r][c] = "*";
							}
						} else if (cells[r][c].equals(".")) {
							if (aliveNeighbourCount == 3) {
								resultCells[r][c] = "*";
							} else {
								resultCells[r][c] = ".";
							}
						}

					}
				}
				for (int r = 0; r < cells.length; r++) {
					for (int c = 0; c < cells.length; c++) {
						// copy back
						cells[r][c] = resultCells[r][c];
					}
				}
				iteraton++;
			}
			for (int r = 0; r < cells.length; r++) {
				for (int c = 0; c < cells.length; c++) {
					// copy back
					System.out.print(cells[r][c]);
					//System.out.print(" ");
				}
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
