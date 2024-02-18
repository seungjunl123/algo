import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 변수 N, M 선언
	static int N;
	static int M;
	// 2차 배열 arr 선언
	static int[][] arr;
	// 델타배열 생성
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	// N,M 재귀 numbers, visited 선언
	static int[] numbers;
	static int[] visited;
	// 영역의 크기를 셀 cnt와 최대 영역 max를 설정
	static int cnt = 0;
	static int max = Integer.MIN_VALUE;

	static List<int[]> list;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		// 변수 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 배열 입력
		arr = new int[N][M];

		// 0인 지점 넣을 리스트 생성
		list = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// arr[i][j]가 0이면 i와 j를 원소로 가지는 배열을 list에 넣는다
				// 리스트 내용 만큼 수열 재귀를 통해 랜덤 지정할 0의 지점을 찾을 거임
				if (arr[i][j] == 0) {
					int[] point = new int[2];
					point[0] = i;
					point[1] = j;
					list.add(point);
				}

			}
		}

		// 수열 재귀를 위한 numbers, vistied 생성
		// 수열에서 N : list의 size, M : 3개
		numbers = new int[3];
		visited = new int[list.size() + 1];
		// 수열 재귀인 func1 실행
		func1(0, 0);

		// func1이 끝난 후에는 3개의 기둥이 생기는 모든 경우의 수가 진행된 후
		// max값이 지정된 후므로 max를 출력해주면 된다

		bw.write(max + "");
		bw.flush();
		bw.close();
	}

	static void func1(int count, int index) {
		// 일단 벽 세울 데를 랜덤 지정
		if (count == 3) {
			// 임시로 벽을 세울 배열 선언
			int[][] cor = new int[N][M];
			// cor을 arr과 연결시키면 arr의 값이 변경되므로 일일히 변경해야되는데 이거는 강사님 물어보기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					cor[i][j] = arr[i][j];
				}
			}
			// 수열 재귀를 통해 3개의 좌표 추출
			// numbers에 기록된 idx의 list 내 i,j좌표를 1로 수정
			for (int i = 0; i < 3; i++) {
				cor[list.get(numbers[i])[0]][list.get(numbers[i])[1]] = 1;
			}
			// 수정된 상태에서 1을 만날때까지 2가 퍼지는 함수 func2 실행
			func2(cor);
			return;
		}
		for (int i = index; i < list.size(); i++) {
			if (visited[i] != 1) {
				// 좌표의 중복없이 재귀 실행을 위한 visited
				visited[i] = 1;
				numbers[count] = i;
				func1(count + 1, i);
			}
			visited[i] = 0;

		}
	}

	static void func2(int[][] cor) {
		// 주어진 2차 배열에서 2를 찾는다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 2를 발견하면 사방탐색으로 1아닌 부분을 2로 변환
				if (cor[i][j] == 2) {
					// 이거도 재귀를 해야 가능(이동한 지점에서 사방탐색을 다시 해야 하므로)
					roop(cor,i,j);

				}
			}
		}
		// 탐색이 끝난 후 다시 반복문으로 0의 수를 cnt로 센다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cor[i][j] == 0) {
					cnt++;
				}
			}
		}
		// cnt를 max와 비교 후 수정
		if (cnt > max) {
			max = cnt;
		}
		cnt = 0;
	}
	
	// 사방탐색 메서드
	static void roop(int[][] cor,int i,int j) {
		if(i<0||i>=N||j<0||j>=M) {
			return;
		}
		for(int k=0;k<4;k++) {
			int nr  = i+dr[k];
			int nc = j+dc[k];
			// 사방탐색이 가능하고 0인 지점만 2로 변환시켜주면 된다
			if(nr >= 0 && nr < N && nc >= 0 && nc < M && cor[nr][nc] == 0) {
				cor[nr][nc] = 2;
				// 변환이 된 후에는 다시 그 지점에서 사방 탐색
				roop(cor,nr,nc);
			}
		}
	}
}
