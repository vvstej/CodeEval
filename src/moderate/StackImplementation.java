package moderate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StackImplementation {

	public static void main(String[] arg) throws Exception {
		Stack<Integer> stack = new Stack<Integer>();
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
			String line = null;
			while ((line = inputReader.readLine()) != null) {
				String[] input = line.split("\\s");
				for (int i = 0; i < input.length; i++) {
					stack.push(Integer.parseInt(input[i]));
				}
				int countPrinted = 0;
				while (!stack.isEmpty()) {
					int elem = stack.pop();
					if (countPrinted % 2 == 0) {
						if(countPrinted==0){
							System.out.print(elem);
						}else{
							System.out.print(" "+elem);
						}
						
					}
					countPrinted++;
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

class Stack<T> {
	int top;
	List<T> stack = new ArrayList<T>(50);

	void push(T element) {
		stack.add(element);
		top++;
	}

	T pop() throws Exception {
		if (top <= 0) {
			throw new Exception("No more elements to pop");
		}
		T elem = stack.get(top-1);
		stack.remove(top-1);
		top--;
		return elem;
	}

	boolean isEmpty() {
		if (top <= 0)
			return true;
		else
			return false;
	}
}
