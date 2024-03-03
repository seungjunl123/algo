import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	// 지역 별 정보를 담을 N과 map 선언
	static int N;
	
	// 최솟값을 저장할 변수
	static int ans = 0;

	static BufferedReader br;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		
		fuck();

		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

	private static void fuck() {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(N);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int node = q.poll();
//				System.out.println(node);
				if(node == 1) {
					return;
				}
				if(node%3==0) {
					q.add(node/3);
				}
				if(node%2==0) {
					q.add(node/2);
				}
				q.add(node-1);
			}
			ans++;
		}
	}
}