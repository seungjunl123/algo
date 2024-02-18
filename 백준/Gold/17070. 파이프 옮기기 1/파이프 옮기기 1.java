import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	// 변수 N 선언
	static int N;
	// 장애물 여부 arr 배열 선언
	static boolean[][] arr;
	// 이동 방법 cnt 선언
	static int cnt = 0;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 변수 입력 및 배열 생성
		N = Integer.parseInt(br.readLine());
		arr = new boolean[N + 1][N + 1];
		// 벽 정보 입력, 1이 장애물이지만 외곽 지역과 같이 구분 위해
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// 0인 지점을 true로 설정 -> 벽이랑 배열을 벗어나는 부분 모두 false
				if (st.nextToken().equals("0")) {
					arr[i][j] = true;
				} else {
					arr[i][j] = false;
				}
			}
		}
		// 방향은 3가지, DFS로 검증
		// 처음 0,1번 포인터 (1,1), (1,2)
		// 포인터 좌표와 이동할 좌표를 활용한다
		DFS(0, 0, 0, 1);

		// DFS가 끝난 후 cnt 출력
		bw.write(cnt + "");
		bw.flush();
		bw.close();
	}

	static void DFS(int r1, int c1, int r2, int c2) {
		// i-2번 위치와 i-1위치의 r,c 좌표 차이에 따라 파이프의 이동 방향을 결정
		// 아래의 경우
		if (r2 - r1 == 1&&c2-c1 == 0) {
			if (arr[r2 + 1][c2] == true) {
				DFS(r2, c2, r2 + 1, c2);
				if (arr[r2 + 1][c2] == true && arr[r2][c2 + 1] == true && arr[r2 + 1][c2 + 1] == true) {
					DFS(r2, c2, r2 + 1, c2 + 1);
				}
				// 아래쪽은 아래 방향이 false면 이동이 불가하므로
			} else {
				// 이때 꼭지점에 도착했는지 확인한다
				if (r2 == N - 1 && c2 == N - 1) {
					cnt++;
					return;
				}
			}
			// 아래쪽은 (r-1,c) 이 0일 때
		}
		if (c2 - c1 == 1&&r2-r1 == 0) {
			if (arr[r2][c2 + 1] == true) {
				DFS(r2, c2, r2, c2 + 1);
				if (arr[r2][c2 + 1] == true && arr[r2 + 1][c2] == true && arr[r2 + 1][c2 + 1] == true) {
					DFS(r2, c2, r2 + 1, c2 + 1);
				}
				// 오른쪽은 오른쪽 방향이 false면 이동이 불가하므로
			} else {
				// 이때 꼭지점에 도착했는지 확인한다
				if (r2 == N - 1 && c2 == N - 1) {
					cnt++;
					return;
				}
			}
		}
		if (c2 - c1 == 1 && r2 - r1 == 1) {
			// 주어진 방향에 모두 이동 불가면 끝
			if (arr[r2 + 1][c2] == true || arr[r2][c2 + 1] == true) {
				if (arr[r2 + 1][c2] == true) {
					DFS(r2, c2, r2 + 1, c2);
				}
				if (arr[r2][c2 + 1] == true) {
					DFS(r2, c2, r2, c2 + 1);
				}
				if (arr[r2 + 1][c2] == true && arr[r2][c2 + 1] == true && arr[r2 + 1][c2 + 1] == true) {
					DFS(r2, c2, r2 + 1, c2 + 1);
				}
				// 대각선은 (r,c+1), (r+1,c)이 0일 때 이동 불가이므로
			} else {
				// 이동 불가 지점이 N-1,N-1인 경우 cnt를 1 더한다
				if (r2 == N - 1 && c2 == N - 1) {
					cnt++;
					return;
				}
			}
			
		}
	}

}