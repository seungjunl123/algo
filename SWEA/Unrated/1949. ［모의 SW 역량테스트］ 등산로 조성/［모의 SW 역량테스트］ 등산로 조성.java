import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	static int T, N, K;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int ans, Max;
	static List<int[]> Bong;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;
			Max = 0;

			arr = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// 봉우리 찾기
					if (arr[i][j] > Max) {
						Max = arr[i][j];
						// 이래 하면 메모리 낭비 좀 있지 싶은데
						Bong = new ArrayList<>();
						Bong.add(new int[] { i, j });
					} else if (arr[i][j] == Max) {
						Bong.add(new int[] { i, j });
					}
				}
			}
			// 찾은 봉우리부터 내려가보자
			// 필요한 인자는 위치랑 길이랑 공사 진행 여부
			for (int i = 0; i < Bong.size(); i++) {
				visited[Bong.get(i)[0]][Bong.get(i)[1]]=true;
				makeRoad(Bong.get(i)[0], Bong.get(i)[1], 1, 1);
				visited[Bong.get(i)[0]][Bong.get(i)[1]]=false;
			}

			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void makeRoad(int r, int c, int length, int work) {
		// 내려 갈 수 있는 만큼만 하기
		if (length > ans) {
			ans = length;
		}
		//
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
				
				if (arr[r][c] > arr[nr][nc]) {
					visited[nr][nc] = true;
					makeRoad(nr, nc, length + 1, work);
					visited[nr][nc] = false;
				}
				if(work==1) {
					for (int j = 1; j <= K; j++) {
						if (arr[r][c] > arr[nr][nc] - j) {
							arr[nr][nc] -= j;
							visited[nr][nc]= true;
							makeRoad(nr, nc, length + 1, 0);
							visited[nr][nc]= false;
							arr[nr][nc] += j;
						}
					}	
				}
			}
		}

	}

}