package hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class Stack<T> {
	int head = -1;
	private static final int MAX_SIZE = 50;
	Object[] array;

	Stack() {
		array = new Object[MAX_SIZE];
	}

	public int getHead() {
		return head;
	}

	public void push(T element) throws Exception {
		if (head == MAX_SIZE - 1) {
			throw new Exception();
		} else {
			array[++head] = element;
		}
	}

	public Object pop() throws Exception {
		if (head == -1) {
			throw new Exception("No Element to pop");
		}
		Object element = array[head];
		array[head] = null;
		head--;
		return element;

	}
	
	public boolean isEmpty(){
		if(head == -1) return true;
		return false;
	}

	public Object peek() throws Exception {
		if (head == -1) {
			throw new Exception("No Element to peek");
		}
		return array[head];

	}

	public int getLength() {
		return head;

	}
}

public class PrefixTree {
	final static List<String> OPERATORS = new ArrayList<String>();

	public static void main(String[] arg) throws Exception {
		OPERATORS.add("+");
		OPERATORS.add("*");
		OPERATORS.add("/");
		OPERATORS.add("-");
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
				int result = 0;
				String[] lineArray = line.split("\\s+");
				Stack<Integer> operandStack = new Stack<Integer>();
				for (int i = lineArray.length-1; i >=0; i--) {
					if (isOperator(lineArray[i])) {
						int left = (int) operandStack.pop();
						int right = (int) operandStack.pop();
						result = evaluateExpression(lineArray[i], left, right);
						operandStack.push(result);
					} else {
						operandStack.push(Integer.parseInt(lineArray[i]));
					}
				}
				System.out.println(result);
				line = inputReader.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			inputReader.close();
		}
	}

	static int evaluateExpression(String operator, int left, int right) {
		int result = 0;
		switch (operator) {
		case "*":
			result = left * right;
			break;
		case "+":
			result = left + right;
			break;
		case "-":
			result = left - right;
			break;
		case "/":
			result = left / right;
			break;
		}
		return result;
	}

	public static boolean isOperator(String s) {
		if (OPERATORS.contains(s))
			return true;
		return false;
	}
}
