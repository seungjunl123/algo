import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, M, H;
	static final int[] dr = { 0, 0, -1, 1, 0, 0 };
	static final int[] dc = { -1, 1, 0, 0, 0, 0 };
	static final int[] dh = { 0, 0, 0, 0, -1, 1 };

	static int[][][] map;
	static int count;
	static int check;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N][M];
		count = 0;
		check = 0;

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					map[h][n][m] = Integer.parseInt(st.nextToken());
					if (map[h][n][m] == 0) {
						check++;
					}
//					System.out.print(map[h][n][m]+" ");
				}
//				System.out.println();
			}
		}
		// 1인 지점을 찾아서 BFS를 돌리는건 -> 토마토가 여러 군데라서
		// 배열을 순환하면서 큐에다가 넣을까?

		// 0을 체크했는데 0이 한군데도 없다? 그럼 카운트는 0이니까 BFS를 넘긴다
		if (check != 0) {
			BFS();
			//마지막에 0으로 다 채운후에도 q while문이 돌아서 1빼줘야 되나?
			count--;
			if (check != 0) {
				// BFS 이후에는 모든 가능한 지점이 1이 되어있어야 한다
				// 0을 바꿔주면서 내린 check가 0이 아니면 도달 불가능한 0이 있다는 뜻
				count = -1;
			}

		}
		bw.write(count + "");
		bw.flush();
		bw.close();
	}

	private static void BFS() {
		Queue<int[]> q = new LinkedList<>();

		// 초기 1인 지점을 q에 넣어줌
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (map[h][n][m] == 1) {
						int[] point = { h, n, m };
						q.add(point);
//						System.out.print(Arrays.toString(point));
					}
				}
			}
		}
		// while문을 반복하면서 가능한 1인 모든 부분의 주변을 1로 바꿈(0아면)
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();
				// 델타 배열 적용
				for (int j = 0; j < 6; j++) {
					int nr = node[1] + dr[j];
					int nc = node[2] + dc[j];
					int nh = node[0] + dh[j];
					// 범위를 만족하고 0인 경우(아직 방문하지 않은 경우)
					if (nr >= 0 && nc >= 0 && nh >= 0 && nr < N && nc < M && nh < H && map[nh][nr][nc] == 0) {
//						System.out.println(nc + " " + nr + " " + nh);
						map[nh][nr][nc] = 1;
						// 0을 바꿔주면서 check를 깎는다
						check--;
						int[] newNode = { nh, nr, nc };
						q.offer(newNode);
					}
				}
			}
			// 1회전이 끝나면 하루가 끝난 것이므로 count++;
			count++;
		}
	}
}