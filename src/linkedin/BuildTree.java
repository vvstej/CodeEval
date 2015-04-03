package linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BuildTree {
	public static void main(String[] arg) {
		Entry e1 = new Entry(15, 20, true);
		Entry e2 = new Entry(19, 80, true);
		Entry e3 = new Entry(17, 20, false);
		Entry e4 = new Entry(16, 80, false);
		Entry e5 = new Entry(80, 50, false);
		Entry e6 = new Entry(50, null, false);
		Entry e7 = new Entry(20, 50, true);
		List<Entry> entries = new ArrayList<Entry>();
		entries.add(e1);
		entries.add(e2);
		entries.add(e3);
		entries.add(e4);
		entries.add(e5);
		entries.add(e6);
		entries.add(e7);

		Map<Integer, Pair<Integer>> relationShips = buildHash(entries);
		Tree<Integer> tree = buildTree(relationShips);
		LevelOrderTraversor<Integer> traversor= new LevelOrderTraversor<>();
		traversor.traverse(tree);
	}

	private static Tree<Integer> buildTree(
			Map<Integer, Pair<Integer>> relationShips) {
		Pair<Integer> first = relationShips.get(null);
		Node<Integer> firstNode = new Node<Integer>(first.getY());
		Tree<Integer> tree = new Tree<>(firstNode);	
		Queue<Node<Integer>> elementList = new LinkedList<Node<Integer>>();
		Node<Integer> left = new Node<Integer>(relationShips.get(first.getY()).getX());
		Node<Integer> right = new Node<Integer>(relationShips.get(first.getY()).getY());
		elementList.add(left);
		elementList.add(right);		
		firstNode.setLeft(left);
		firstNode.setRight(right);
		while(!elementList.isEmpty()){
			Node<Integer> elem = elementList.remove();
			Pair<Integer> children = relationShips.get(elem.elem);
			if(children!=null){
				left = new Node<Integer>(children.getX());
				right = new Node<Integer>(children.getY());
				elem.left=left;
				elem.right=right;
				elementList.add(left);
				elementList.add(right);
			}			
			
		}
		return tree;
	}

	private static Map<Integer, Pair<Integer>> buildHash(List<Entry> entries) {
		Map<Integer, Pair<Integer>> relationShips = new HashMap<Integer, Pair<Integer>>();
		for (Entry entry : entries) {
			Integer parent = entry.getParent();
			Pair<Integer> children = relationShips.get(parent);
			if (children == null) {
				children = new Pair<Integer>();
			}
			if (entry.isLeft()) {
				children.setX(entry.getChild());
			} else {
				children.setY(entry.getChild());
			}
			relationShips.put(parent, children);
		}
		return relationShips;
	}
}

class Entry {
	private Integer child;
	private Integer parent;
	private boolean isLeft;

	public Entry(Integer child, Integer parent, boolean isLeft) {
		this.setChild(child);
		this.setParent(parent);
		this.setLeft(isLeft);
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

}
