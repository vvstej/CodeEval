package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Corner {
	public Corner(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int x, y;

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
}

class Rectangle {
	public Rectangle(Corner corner1, Corner corner2, Corner corner3,
			Corner corner4) {
		this.corner1 = corner1;
		this.corner2 = corner2;
		this.corner3 = corner3;
		this.corner4 = corner4;
	}

	public Iterator<Corner> cornerIterator() {
		List<Corner> cornerList = new ArrayList<Corner>();
		cornerList.add(corner1);
		cornerList.add(corner2);
		cornerList.add(corner3);
		cornerList.add(corner4);
		return cornerList.iterator();

	}

	private Corner corner1;
	private Corner corner2;
	private Corner corner3;
	private Corner corner4;

	public void setCorner1(Corner corner1) {
		this.corner1 = corner1;
	}

	public Corner getCorner1() {
		return corner1;
	}

	public void setCorner2(Corner corner2) {
		this.corner2 = corner2;
	}

	public Corner getCorner2() {
		return corner2;
	}

	public void setCorner3(Corner corner3) {
		this.corner3 = corner3;
	}

	public Corner getCorner3() {
		return corner3;
	}

	public void setCorner4(Corner corner4) {
		this.corner4 = corner4;
	}

	public Corner getCorner4() {
		return corner4;
	}

	public boolean enclosesRectangle(Rectangle rect2) {
		int x1Edge = this.corner1.getX();
		int x2Edge = this.corner2.getX();
		int greaterX = x1Edge > x2Edge ? x1Edge : x2Edge;
		int smallerX = (greaterX == x1Edge) ? x2Edge : x1Edge;

		int y1Edge = this.corner1.getY();
		int y2Edge = this.corner3.getY();
		int greaterY = y1Edge > y2Edge ? y1Edge : y2Edge;
		int smallerY = (greaterY == y1Edge) ? y2Edge : y1Edge;

		Iterator<Corner> it = rect2.cornerIterator();
		while (it.hasNext()) {
			Corner targetCorner = it.next();
			if (targetCorner.getX() >= smallerX
					&& targetCorner.getX() <= greaterX
					&& targetCorner.getY() >= smallerY
					&& targetCorner.getY() <= greaterY) {
				return true;
			}
		}
		return false;
	}

}

public class RectangleOverlap {
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
			while (line != null) {
				String[] points = line.split(",");

				Corner rect1Corner1 = new Corner(Integer.parseInt(points[0]),
						Integer.parseInt(points[1]));
				Corner rect1Corner4 = new Corner(Integer.parseInt(points[2]),
						Integer.parseInt(points[3]));
				Corner rect1Corner2 = new Corner(Integer.parseInt(points[2]),
						Integer.parseInt(points[1]));
				Corner rect1Corner3 = new Corner(Integer.parseInt(points[0]),
						Integer.parseInt(points[3]));

				Corner rect2Corner1 = new Corner(Integer.parseInt(points[4]),
						Integer.parseInt(points[5]));
				Corner rect2Corner4 = new Corner(Integer.parseInt(points[6]),
						Integer.parseInt(points[7]));
				Corner rect2Corner2 = new Corner(Integer.parseInt(points[6]),
						Integer.parseInt(points[5]));
				Corner rect2Corner3 = new Corner(Integer.parseInt(points[4]),
						Integer.parseInt(points[7]));

				Rectangle rect1 = new Rectangle(rect1Corner1, rect1Corner2,
						rect1Corner3, rect1Corner4);
				Rectangle rect2 = new Rectangle(rect2Corner1, rect2Corner2,
						rect2Corner3, rect2Corner4);

				if (rect1.enclosesRectangle(rect2)
						|| rect2.enclosesRectangle(rect1)) {
					System.out.println("True");
				} else {
					System.out.println("False");
				}
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}
}
