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
	// TC, 가로 M, 세로 N, 배추 개수 K 선언
	static int T;
	static int M;
	static int N;
	static int K;

	// map 배열 선언, visit 체크 여부는 그냥 배열에 +를 해준다
	static int[][] map;
	// 지렁이 수 cnt 선언
	static int cnt;
	// 델타배열 생성
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// T 입력
		T = Integer.parseInt(br.readLine());
		// T 만큼 케이스 반복문 생성
		for (int TC = 1; TC <= T; TC++) {
			// M,N,K 초기화 및 값 입력
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// K개의 배추 위치를 map에 1로 입력해준다
			map = new int[M][N];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			// map 탐색
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					// 1인 위치(배추) 도착 시 BFS 실행
					if (map[i][j] == 1) {
						func1(i, j);
						// 끝나면 cnt를 1 추가해준다
						cnt++;
					}
				}
			}
			// cnt출력
			bw.write(cnt + "\n");
			bw.flush();
			// cnt 0으로 초기화
			cnt = 0;
		}
		bw.close();
	}

	// BFS 실행
	public static void func1(int i, int j) {
		// 해당 위치 +1
		map[i][j]++;
		int nr;
		int nc;
		// 사방 탐색으로 1인 지역 찾으면
		for (int q = 0; q < 4; q++) {
			nr = i + dr[q];
			nc = j + dc[q];
			if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] == 1) {
				// 재귀함수 실행으로 추가 탐색
				func1(nr,nc);
			}
		}
	}

}
