import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 지도 크기 N 선언
	static int N;
	// visited 2차 배열 선언
	static int[][] visited;
	// 단지 위치 표기 위한 2차 배열 선언
	static int[][] map;
	// 단지 별 집의 수를 세기 위한 cnt 생성, 초기값은 0으로
	static int cnt = 0;
	// 단지 별 집의 수를 저장하기 위한 List 생성
	static List<Integer> list = new ArrayList<Integer>();
	// 사방 탐색을 위한 dr,dc 생성
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static BufferedReader br;
	static BufferedWriter bw;
	static String st;

	public static void main(String[] args) throws IOException {
		// br,bw,st 선언
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// N 입력
		N = Integer.parseInt(br.readLine());
		// N*N 크기로 map 배열, visited 배열 선언
		visited = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = st.charAt(j) - '0';
			}
		}
		// 배열의 크기 만큼 반복
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// map이 1이고 visited가 0인 경우
				if (map[i][j] == 1 && visited[i][j] == 0) {
					// BFS활용해서 인접 집의 cnt를 세는 함수 사용
					BFS(i, j);
					// BFS함수가 끝나면 검산이 끝난 cnt를 List에 저장
					list.add(cnt);
					// cnt 0으로 초기화(맨 나중)
					cnt = 0;
				}
			}
		}
		// 반복문이 끝난 후 list의 사이즈 및 오름차순한 인자 출력
		bw.write(list.size() + "\n");
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			bw.write(list.get(i)+"\n");
		}
		bw.flush();
		bw.close();
	}

	// BFS 함수
	public static void BFS(int i, int j) {
		// 자신의 visited 1로 저장하고
		visited[i][j] = 1;
		// cnt++
		cnt++;
		// 사방탐색
		int nr;
		int nc;
		// dr,dc 생성 후 반복문으로 탐색
		for (int d = 0; d < 4; d++) {
			nr = i + dr[d];
			nc = j + dc[d];
			// if map 1이고 visited가 0이면
			if (nc >= 0 && nc < N && nr >= 0 && nr < N && map[nr][nc] == 1 && visited[nr][nc] == 0) {
				// BFS함수 재귀 실행
				BFS(nr, nc);
			}
		}
	}

}
