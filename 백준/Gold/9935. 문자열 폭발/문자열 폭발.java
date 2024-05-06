import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	static String bomb;
	static String ans;
	static Stack<Character> stack = new Stack<>();

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		// stack에 넣는다
		// bomb의 마지막 문자가 뜬다
		// 그러면 bomb의 길이만큼 pop을 하고
		// bomb과 비교해서 다르면 다시 stack에 집어 넣는다
		// 마지막에 stack을 전부 빼내면 끝
		ans = br.readLine();
		bomb = br.readLine();
		char alp = bomb.charAt(bomb.length() - 1);

		cp: for (int i = 0; i < ans.length(); i++) {
			stack.push(ans.charAt(i));
			if (stack.size() >= bomb.length()) {
				if (ans.charAt(i) == alp) {
					int size = bomb.length() - 1;
					for (int j = stack.size() - 1; j > stack.size() - bomb.length() - 1; j--) {
						if (stack.get(j) != bomb.charAt(size--))
							continue cp;
					}

					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}

		if (stack.size() == 0)
			bw.write("FRULA");
		else {
			for (int i = 0; i < stack.size(); i++) {
				bw.write(stack.get(i));
			}
		}
		bw.flush();
		bw.close();
	}
}
