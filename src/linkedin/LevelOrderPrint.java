package linkedin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderPrint {
	public static void main(String[] arg) {
		Node<Integer> root = new Node<Integer>(1);
		Node<Integer> e1 = new Node<Integer>(3);
		Node<Integer> e2 = new Node<Integer>(5);
		Node<Integer> e3 = new Node<Integer>(2);
		Node<Integer> e4 = new Node<Integer>(4);
		Node<Integer> e5 = new Node<Integer>(7);
		Node<Integer> e6 = new Node<Integer>(9);
		Node<Integer> e7 = new Node<Integer>(6);
		Node<Integer> e8 = new Node<Integer>(8);
		root.setLeft(e1);
		root.setRight(e2);
		e1.setLeft(e3);
		e1.setRight(e4);
		e2.setRight(e5);
		e3.setLeft(e6);
		e3.setRight(e7);
		e4.setLeft(e8);
		Tree<Integer> tree = new Tree<Integer>(root);
		Traversor<Integer> levelOrder = new LevelOrderTraversor<>();
		levelOrder.traverse(tree);
	}
}

class Tree<T> {
	Node<T> root;

	// private InOrderTraversor<T> inOrder = null;
	// private LevelOrderTraversor<T> levelOrder = null;

	public Tree(Node<T> root) {
		this.root = root;
	}

	// public Traversor<T> inOrderVisitor() {
	// if (inOrder == null) {
	// inOrder = new InOrderTraversor<>();
	// }
	//
	// return inOrder;
	// }
	//
	// public Traversor<T> levelOrderVisitor() {
	// if (levelOrder == null) {
	// levelOrder = new LevelOrderTraversor<>();
	// }
	//
	// return levelOrder;
	// }

}

class Node<T> {
	T elem;
	Node<T> left;
	Node<T> right;

	public Node(T elem) {
		this.elem = elem;
	}

	public Node<T> getLeft() {
		return this.left;
	}

	public Node<T> getRight() {
		return this.right;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

}

class LevelOrderTraversor<T> implements Traversor<T> {

	@Override
	public void traverse(Tree<T> tree) {
		Node<T> root = tree.root;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		doIt(queue);
	}

	private void doIt(Queue<Node<T>> queueFromParent) {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		while (!queueFromParent.isEmpty()) {
			Node<T> elem = queueFromParent.remove();
			if (elem.left != null) {
				queue.add(elem.left);
			}
			if (elem.right != null) {
				queue.add(elem.right);
			}
			System.out.print(elem.elem+" ");
		}
		System.out.println();
		if (queue.size() != 0) {
			 doIt(queue);
		}

	}

}

class InOrderTraversor<T> implements Traversor<T> {

	@Override
	public void traverse(Tree<T> node) {
		// TODO Auto-generated method stub

	}

}

class PreOrderTraversor<T> implements Traversor<T> {

	@Override
	public void traverse(Tree<T> tree) {
		Node<T> root = tree.root;
		// List<Node<T>> list = new ArrayList<Node<T>>();
		// list.add(root);
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		doIt(queue);
	}

	private void doIt(Queue<Node<T>> queueFromParent) {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		while (!queueFromParent.isEmpty()) {
			Node<T> elem = queueFromParent.remove();
			if (elem.left != null) {
				queue.add(elem.left);
			}
			if (elem.right != null) {
				queue.add(elem.right);
			}
			System.out.print(elem);
		}
		System.out.println();
		doIt(queue);

	}
}

interface Traversor<T> {
	public void traverse(Tree<T> node);
}
