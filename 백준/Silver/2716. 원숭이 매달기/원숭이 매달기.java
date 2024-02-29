import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int T;
	// [가 나오면 +1, ]가 나오면 -1
	// count가 0이 되었을 때 가장 높았던 수만큼 2를 제곱한다(비트 연산하면 될듯)
	static int count;
	static int max;
	static String str;
	static Queue<Character> q;
	static BufferedReader br;
	static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			q = new LinkedList<>();
			str = br.readLine();
			for(int i=0;i<str.length();i++) {
				q.add(str.charAt(i));
			}
			count = 0;
			max = Integer.MIN_VALUE;
			while(!q.isEmpty()) {
				char a = q.poll();
				if(a == '[') {
					count++;
					if(count>max) {
						max = count;
					}
				}
				else {
					count--;	
					}
			}
			bw.write((1<<max)+"\n");
		}
		bw.flush();
		bw.close();
		
	}
}
