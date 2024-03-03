import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	// 컴퓨터 수
	static int N;
	// 관계 수
	static int M;
	static boolean[][] map;
	static int[] visited;
	static boolean[] check;
	
	// 최솟값을 저장할 변수
	static int ans = 0;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine()); 
		
		map = new boolean[N+1][N+1];
		visited = new int[N+1];
		check = new boolean[N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
			map[b][a] = true;
		}
		
		fuck();
		
		for(int i=2;i<=N;i++) {
			if(visited[i]==1) {
				ans++;
			}
		}

		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

	private static void fuck() {
		Queue<Integer> q = new LinkedList<>();
		 q.offer(1);
		 visited[1] = 1;
		 while(!q.isEmpty()) {
			 int size = q.size();
			 for(int i=0;i<size;i++) {
				 int node= q.poll();
				 for(int j=1;j<=N;j++) {
					 if(map[node][j]&&visited[j]!=1) {
						 q.offer(j);
						 visited[j] = 1;
					 }
				 }
			 }
		 }

	}
}