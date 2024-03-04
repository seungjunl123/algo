
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		Stack<Character> stack = new Stack<Character>();
		Queue<Character> queue = new LinkedList<Character>();
		String str = sc.nextLine();
		
		sc.close();

		int k = 0;
		char[] charr = str.toCharArray();
		while (k != charr.length) {
			if (charr[k] == '<') {
				while (charr[k] != '>') {
					queue.offer(charr[k]);
					k++;
				}
				queue.offer(charr[k]);
				while (!queue.isEmpty()) {
					sb.append(queue.poll());
				}
				k++;
			}
			if (k != charr.length &&  charr[k] != ' ' && charr[k] != '<') {
				while (k != charr.length && charr[k] != ' ' && charr[k] != '<') {
					stack.add(charr[k]);
					k++;
				}
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
			}
			if (k != charr.length && charr[k] == ' ') {
				sb.append(charr[k]);
				k++;
			}
		}
		
		System.out.println(sb);

	}

}
