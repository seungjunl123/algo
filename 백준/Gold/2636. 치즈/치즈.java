import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };
	static Queue<int[]> q;

	static int count;
	static int cheeze;
	static int a, b;
	static int ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// 풀이 과정
		// 1. 전체 배열을 생성
		// 2. (0,0)부터 BFS 시작, 1을 만나면 3으로 변경
		// 3. BFS가 완료된 후 3인 지점을 모두 0으로 변경,
		// 이때 3인 지점의 수가 녹아 없어진 치즈의 수고, 카운트는 1증가
		// 4. 3으로 변한 지점이 0일 경우 카운트와 이전 치즈 값을 반환
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		q = new LinkedList<>();
		visited = new boolean[N][M];
		ans = 0;
		cheeze = -1;

			// 2.BFS 시작
//			System.out.println(a + " ? " + b);
		BFS(0, 0);
			// BFS가 끝난 후에 전체 탐색으로 3을 찾는다
			// 치즈가 녹는 카운트를 세주고

			// 치즈가 0이 아닌 경우 이번 타임에 녹은 치즈 수를 ans에 저장
			// 치즈가 0인 경우 아래 if문을 지나고 while문이 종료된다.

		
		bw.write((count - 1) + "\n" + ans);
		bw.flush();
		bw.close();

	}

	private static void BFS(int r, int c) {
		Queue<int[]> q2 = new LinkedList<>();
		q.add(new int[] { r, c, });

		while (!q.isEmpty()) {

			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();
				visited[node[0]][node[1]] = true;
				//System.out.println(node[0] + " " + node[1]);
				for (int j = 0; j < 4; j++) {
					int nr = node[0] + dr[j];
					int nc = node[1] + dc[j];
					if (nr >= 0 && nc >= 0 && nr < N && nc < M && !visited[nr][nc]) {
						visited[nr][nc] = true;
						if (arr[nr][nc] == 0) {
							// 0인 지점을 모두 탐색하며 visited 진환
							q.add(new int[] { nr, nc });
						} else if (arr[nr][nc] == 1) {

//							System.out.println("r " + nr + " c " + nc);
							// 치즈를 만나면 3으로 설정 -> bfs 끝나고 처리
							q2.add(new int[] {nr,nc});


						}
					}
				}
			}
			// q가 다 뽑아져 있으면 3이 남아있는지 보고
			if (q.isEmpty()) {
				count++;
				cheeze= 0;
				while(!q2.isEmpty()) {
					int[] ang = q2.poll();
					arr[ang[0]][ang[1]]=0;
					//System.out.println(ang[0]+" "+ang[1]);
					q.add(ang);
					cheeze++;
				}
			}
			if (cheeze != 0) {
				ans = cheeze;
			}
		}

	}
}