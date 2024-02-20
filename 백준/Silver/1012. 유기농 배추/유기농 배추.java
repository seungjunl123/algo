
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int M, N, K;
	public static int a, b;
	public static boolean[][] visited;
	public static int[][] adjMatrix;
	public static int[] dx = new int[] { 0, -1, 0, 1 };
	public static int[] dy = new int[] { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			visited = new boolean[M + 1][N + 1];
			adjMatrix = new int[M + 1][N + 1];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				adjMatrix[a][b] = 1;
			}
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (adjMatrix[i][j] == 1 && !visited[i][j]) {
						cnt++;
						BFS(i, j);
					}
				}
			}
			System.out.println(cnt);
		}
	} // end main method()

	public static void BFS(int x, int y) {
		Queue<Integer[]> queue = new LinkedList<>();

		visited[x][y] = true;
		queue.offer(new Integer[] { x, y });

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		
		while (!queue.isEmpty()) {
			Integer[] node = queue.poll();
			int wx = node[0];
			int wy = node[1];
			for (int d = 0; d < 4; d++) {
				int nx = wx + dx[d];
				int ny = wy + dy[d];
				if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
					continue;
				}
				if (visited[nx][ny] == true || adjMatrix[nx][ny] != 1) {
					continue;
				}
				visited[nx][ny] = true;
//				System.out.println(nx + " " + ny);

				queue.offer(new Integer[] { nx, ny });
			}
		} // end while()

//		for (int i = 0; i < M; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

	} // end BFS()

}
