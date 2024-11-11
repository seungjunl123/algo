import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	static int[][] map;
	static int[][] melt;
	static boolean[][] check;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int N;
	static int M;
	static int x;
	static int y;
	static boolean flag = false;
	static int meltCount = 0;

	public static void main(String[] args) throws IOException {
		// 맵의 정보를 저장하면서 얼음 지역중 하나를 스타트 지역으로 지정
		// 스타트 지역을 중심으로 dfs를 통해 얼음 지역이 얼마나 녹을 것인지 측정
		// 측정은 melt Arr로, 이미 측정했다면 check 표시로 저장
		// 측정이 완료되면 현재 얼어있는 지역에서 얼마나 녹는지 저장(map - melt)
		// 기존 얼어있던 지역과 이번 측정간 탐색한 지역의 수를 비교
		// 두 값이 틀리면 지역이 두 군데로 찢어진 것이므로 반복문을 멈추고 답을 도출
		// 그렇지 않다면 dfs를 추가로 탐색
		// 만약 현재 얼어있는 지역이 0이면 반복문을 멈추고 답을 0으로 도출
		// 반복문이 나가질 때까지 answer 카운트를 1씩 올리면 찾을 수 있다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int answer= 0;

		 x = -1;
		 y = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					x = i;
					y = j;
				}
			}
		}
		while(!flag) {
//			System.out.println("tlqkf");
			melt = new int[N][M];
			check = new boolean[N][M];
			meltCount = 0;
			iceAge(x, y);
			
			int totalIce = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] != 0 ) totalIce++;
				}
			}
			if(totalIce == 0) break;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					map[i][j] = map[i][j] - melt[i][j] >0 ? 
							map[i][j] - melt[i][j] : 0;
					if(map[i][j] >0) {
						x = i;
						y = j;
					}
				}
			}
			
			
			
			if(totalIce != meltCount) {
				flag = true;
				break;
			}
			answer++;
		}
		
		if(!flag) answer = 0;
		
		System.out.println(answer);

	}

	private static void iceAge(int x, int y) {
		meltCount++;
		check[x][y] = true;
		for(int i=0;i<4;i++) {
			int nr = x + dr[i];
			int nc = y + dc[i];
			if(nr>=0 && nc>=0 && nr<N && nc<M) {
				if(map[nr][nc] == 0) {
					melt[x][y]++;
				}
			}
		}
		for(int i=0;i<4;i++) {
			int nr = x + dr[i];
			int nc = y + dc[i];
			if(nr>=0 && nc>=0 && nr<N && nc<M) {
				if(map[nr][nc] != 0 && !check[nr][nc]) {
					iceAge(nr,nc);
				}
			}
		}
	}
}
