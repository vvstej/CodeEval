package moderate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
	public static void main(String[] arg) throws Exception {
		Graph<Integer> graph = new Graph<Integer>();
		Vertex<Integer> start = new Vertex<Integer>(6);
		Vertex<Integer> v2 = new Vertex<Integer>(5);
		Vertex<Integer> v3 = new Vertex<Integer>(4);
		Vertex<Integer> v4 = new Vertex<Integer>(3);
		Vertex<Integer> v5 = new Vertex<Integer>(2);
		Vertex<Integer> v6 = new Vertex<Integer>(1);
		Vertex<Integer> v7 = new Vertex<Integer>(7);
		List<Vertex<Integer>> adjList = new ArrayList<Vertex<Integer>>();
		adjList.add(v2);
		adjList.add(v3);
		start.setAdjacencyList(adjList);
		adjList = new ArrayList<Vertex<Integer>>();
		adjList.add(v4);
		adjList.add(v5);
		v2.setAdjacencyList(adjList);
		adjList = new ArrayList<Vertex<Integer>>();
		adjList.add(v6);
		v5.setAdjacencyList(adjList);
		adjList = new ArrayList<Vertex<Integer>>();
		adjList.add(v7);
		v6.setAdjacencyList(adjList);
		
		graph.dfs(start);

	}

}

class Graph<T> {

	public void bfs(Vertex<T> startNode) {
		Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		queue.add(startNode);
		while (!queue.isEmpty()) {
			Vertex<T> v = queue.remove();
			System.out.println(v.getKey());
			List<Vertex<T>> adjList = v.getAdjacencyList();
			for (Vertex<T> elem : adjList) {
				if (!elem.isVisited()) {
					elem.setVisited(true);
					queue.add(elem);
				}
			}
		}
	}

	public void dfs(Vertex<T> startNode) throws Exception {
//		Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
//		stack.push(startNode);
//		while (!stack.isEmpty()) {
//			Vertex<T> v = stack.pop();
//			System.out.println(v.getKey());
//			List<Vertex<T>> adjList = v.getAdjacencyList();
//			for (Vertex<T> elem : adjList) {
//				if (!elem.isVisited()) {
//					elem.setVisited(true);
//					stack.push(elem);
//				}
//			}
//		}
		System.out.println(startNode.getKey());
		List<Vertex<T>> adjList = startNode.getAdjacencyList();
		for(Vertex<T> elem : adjList){
			if(!elem.isVisited()){
				dfs(elem);
			}
		}
		
	}
}

class Vertex<T> {
	public Vertex(T val) {
		this.key = val;
	}

	private T key;

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	private boolean isVisited;

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	List<Vertex<T>> adjacencyList = new ArrayList<Vertex<T>>();

	public void setAdjacencyList(List<Vertex<T>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public List<Vertex<T>> getAdjacencyList() {
		return this.adjacencyList;
	}
}
