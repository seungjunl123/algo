import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Camera {
		int r;
		int c;
		int type;
		int dir;

		Camera(int r, int c, int type, int dir) {
			this.r = r;
			this.c = c;
			this.type = type;
			this.dir = dir;
		}
	}

	static int N, M, camNum;
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };
	static final int[][] cameraDir = {{},{1,0,0,0},{1,0,1,0},{1,1,0,0},{1,1,0,1},{1,1,1,1}};
	static ArrayList<Camera> cameras;
	static int answer = Integer.MAX_VALUE;
	static int[][] map;

	// cctv의 방향을 저장할 수 있는가 -> 클래스를 따로 만들어서 x,y,방향으로 설정?
	// dfs로 카메라 방향들을 설정한 다음 모든 카메라 방향이 설정되면 사각지대를 탐색
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cameras = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					cameras.add(new Camera(i, j, map[i][j], 0));
				}
			}
		}

		CameraSetting(0);
		
		System.out.println(answer+" ");
	}

	private static void CameraSetting(int idx) {
		if (idx == cameras.size()) {
			// 방향 설정 다 했으니까 사각지대 찾기
			countDarkSide();
			return;
		}

		for (int i = 0; i < 4; i++) {
			// 카메라 방향 설정 후
			cameras.get(idx).dir = i;
			// 다음 카메라 확인
			CameraSetting(idx + 1);
		}

	}

	private static void countDarkSide() {
		int[][] tmpMap = new int[N][M];
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		// 카메라의 위치부터 시작
		// 카메라의 방향을 기준으로
			// 1번은 직진
			// 2번은 직진 + 뒤
			// 3번은 직진 + 오른쪽
			// 4번은 직 + 오 + 왼
			// 5번은 전 방향 체크
		// 6을 만날 경우 종료
		for (int i = 0; i < cameras.size(); i++) {
			Camera node = cameras.get(i);
			for(int j=0;j<4;j++) {
				// 카메라 타입에 따라 추가로 볼 수 있다.
				if(cameraDir[node.type][j]==1) {	
					int nr = node.r + dr[(node.dir+j)%4];
					int nc = node.c + dc[(node.dir+j)%4];
					while (nr >= 0 && nr < N && nc >= 0 && nc < M && tmpMap[nr][nc] != 6) {
						// cctv를 굳이 냅둘 필요 없을듯?
						tmpMap[nr][nc] =  -1;
						nr += dr[(node.dir+j)%4];
						nc += dc[(node.dir+j)%4];
					}
				}
			}
		}
		// 다 찾고 나면 tmpMap에서 바뀌지 않은(0) 부분을 찾기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tmpMap[i][j]==0) {
					count++;
				}
			}
		}
		
		if(count<answer) answer = count;

	}

}