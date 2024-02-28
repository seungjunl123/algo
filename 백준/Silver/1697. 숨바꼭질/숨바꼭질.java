import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, K;
	static int min;
	static boolean[] visited;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// BFS만 돌리면 끝나는 무시무시한 문제
		visited = new boolean[100002];
		BFS();

		bw.write(min + "");
		bw.flush();
		bw.close();
	}

	static void BFS() {
		// BFS를 위한 queue 생성
		Queue<Integer> q = new LinkedList<>();
		// 시작 지점인 N 넣고
		q.offer(N);
		visited[N] = true;

		wp: while (!q.isEmpty()) {
			// 한 레벨이 다 조사되기 위해 node 설정 전에 q의 size를 저장하고 반복문을 실행
			int size = q.size();
			for (int i = 0; i < size; i++) {
				// 조사할 노드 뽑고~~~
				int node = q.poll();
				// K 도달하면 break, while문이 나가야 하니까 플래그 꽂아줘야돼용
				if (node == K) {
					break wp;
				}
				// visited확인하면서 경우의 수 넣어주면 된다!!

				if ((node - 1) >= 0 && !visited[node - 1]) {
					q.offer((node - 1));
					visited[node - 1] = true;
				}
				if ((node + 1) <= 100000 && !visited[node + 1]) {
					q.offer((node + 1));
					visited[node + 1] = true;
				}
				if (node <= 50000) {
					if (!visited[node * 2]) {
						q.offer((node * 2));
						visited[node * 2] = true;
					}
				}

			}
			// count 대신 그냥 min에다가 레벨 탐색이 끝날때마다 1 추가 ~~
			min++;
		}
		return;
	}
}