package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

public class TreeAncestor {
	public static void main(String[] arg) throws Exception {
		BST bst = new BST();
		Node<Integer> node = new Node<Integer>(30);
		bst.root = node;
		bst.insert(8, bst.root);
		bst.insert(52, bst.root);
		bst.insert(3, bst.root);
		bst.insert(20, bst.root);
		bst.insert(10, bst.root);
		bst.insert(29, bst.root);
		File f = new File(arg[0]);
		if (!f.exists()) {
			throw new Exception("Given input file not present");
		}
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = inputReader.readLine())!=null) {
				String[] nos = line.split("\\s");
				// System.out.println(nos[0]+":"+nos[1]);
				Stack<Integer> s1 = new Stack<Integer>();
				Stack<Integer> s2 = new Stack<Integer>();
				bst.inOrderPath(bst.root, Integer.parseInt(nos[0].trim()), s1);
				bst.inOrderPath(bst.root, Integer.parseInt(nos[1].trim()), s2);
				if (s1.search(Integer.parseInt(nos[1].trim())) >= 0) {
					// s1 contains 2nd number, so 2nd number is the common
					// ancestor
					System.out.println(nos[1]);
					continue;
				} else if (s2.search(Integer.parseInt(nos[0].trim())) >= 0) {
					System.out.println(nos[0]);
					continue;
				}
				s1.pop();// remove the topmost to find element.
				s2.pop();
				int lenS1 = s1.size();
				int lenS2 = s2.size();
				boolean isS1Bigger = lenS1 - lenS2 > 0 ? true : false;
				while (!(Math.abs(s1.size()) - Math.abs(s2.size()) == 0)) {
					if (isS1Bigger) {
						s1.pop();
					} else {
						s2.pop();
					}
				}
				int s1Ancestor = 0;
				int s2Ancestor = 0;
				boolean isAncestorFound = false;
				while (!isAncestorFound && !s1.isEmpty() && !s2.isEmpty()) {
					s1Ancestor = s1.pop();
					s2Ancestor = s2.pop();
					if (s1Ancestor == s2Ancestor) {
						isAncestorFound = true;
						break;
					}
				}
				 if (isAncestorFound) {
				 System.out.println(s1Ancestor);
				 } else {
				 throw new Exception("No Common ancestor");
				 }
			}
		} catch (Exception e) {

		} finally {
			inputReader.close();
		}

	}
}

class BST {
	Node<Integer> root;

	public void insert(Integer element, Node<Integer> parent) {
		if (element >= parent.element) {
			if (parent.right == null) {
				parent.insertRight(element);
			} else {
				insert(element, parent.right);
			}
		} else if (element < parent.element) {
			if (parent.left == null) {
				parent.insertLeft(element);
			} else {
				insert(element, parent.left);
			}
		}
	}

	public void inOrderPath(Node<Integer> node, Integer e, Stack<Integer> s)
			throws Exception {
		if (node == null) {
			throw new Exception("Element not found");
		}
		if (node.element.equals(e)) {
			s.push(e);
			return;
		}
		s.push(node.element);
		if (node.element < e) {
			inOrderPath(node.right, e, s);
		} else if (node.element > e) {
			inOrderPath(node.left, e, s);
		}

	}
}

class Node<E> {
	E element;
	Node<E> left;
	Node<E> right;

	Node(E n) {
		this.element = n;
		this.left = null;
		this.right = null;
	}

	public E getElement() {
		return this.element;
	}

	public void insertLeft(E n) {
		this.left = new Node<E>(n);
		this.left.left = null;
		this.left.right = null;
	}

	public void insertRight(E n) {
		this.right = new Node<E>(n);
		this.right.left = null;
		this.right.right = null;
	}
}
