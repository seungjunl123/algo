import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static int[][] map;
	static int count;

	static int[] visited;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}

		count = 0;
		visited = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			if (visited[i] != 1) {
				BFS(i);
				count++;
			}
		}

		bw.write(count + "");
		bw.flush();
		bw.close();

	}

	static void BFS(int idx) {
		Queue<Integer> q = new LinkedList<>();

		q.offer(idx);
		visited[idx] = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int node = q.poll();
				for (int j = 1; j <= N; j++) {
					if (map[node][j] == 1 && visited[j] == 0) {
						visited[j] = 1;
						q.offer(j);
					}
				}
			}
		}

	}
}