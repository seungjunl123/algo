import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	// 지역 별 정보를 담을 N과 map 선언
	static int N;
	static int[][] map;

	// d1,d2를 결정할 수열 생성
	static int[] numbers;

	// 최솟값을 저장할 변수
	static int ans;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		numbers = new int[2];
		ans = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// d1, d2를 정하는 메서드 실행
		selectd(0);

		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

	static void selectd(int idx) {
		if (idx == 2) {
			// r과 c는 d1, d2크기에 따라 정할 수 있다
			for (int i = 1; i <= N - (numbers[0] + numbers[1]); i++) {
				for (int j = numbers[0] + 1; j <= N - numbers[1]; j++) {
					// 정해진 r,c 값에 따라 지역의 인구수를 결정한다
					population(i, j);
				}
			}
			// System.out.print(Arrays.toString(numbers));
			return;
		}
		for (int i = 1; i <= N / 2; i++) {
			numbers[idx] = i;
			selectd(idx + 1);
		}
	}

	static void population(int r, int c) {
		// 지역의 조건에 따라 지역 인구수를 더한다
		int[] ter = new int[5];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int[][] check = new int[N + 1][N + 1];

		//System.out.println("r :" + r + "c :" + c + "d1 :" + numbers[0] + "d2 :" + numbers[1]);
		for (int i = 1; i < r; i++) {
			for (int j = 1; j <= N; j++) {
				if (j <= c) {
					check[i][j] = 1;
				} else {
					check[i][j] = 2;
				}
			}
		}
		// 구역1
		for (int i = r; i < r + numbers[0]; i++) {
			for (int j = 1; j < c - (i - r); j++) {
				check[i][j] = 1;
			}
		}
		// 구역 2
		for (int i = r; i <= r + numbers[1]; i++) {
			for (int j = c + 1 + (i - r); j <= N; j++) {
				check[i][j] = 2;
			}
		}
		// 구역 3
		for (int i = r + numbers[0]; i <= r + numbers[1] + numbers[0]; i++) {
			for (int j = 1; j < (c - numbers[0]) + (i - (r + numbers[0])); j++) {
				check[i][j] = 3;
			}
		}
		// 구역 4
		for (int i = r + numbers[1] + 1; i <= r + numbers[1] + numbers[0]; i++) {
			for (int j = (c + numbers[1]) - (i - (r + numbers[1])) + 1; j <= N; j++) {
				check[i][j] = 4;
			}
		}
		for (int i = r + numbers[0] + numbers[1] + 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (j < c - numbers[0] + numbers[1]) {
					check[i][j] = 3;
				} else {
					check[i][j] = 4;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (check[i][j] == 0) {
					check[i][j] = 5;
				}
				ter[check[i][j]-1] += map[i][j];
				//System.out.print(check[i][j] + " ");
			}
			//System.out.println();
		}
		
		for (int i = 0; i < 5; i++) {
			//System.out.print(ter[i] + " ");
			if (ter[i] > max) {
				max = ter[i];
			}
			if (ter[i] < min) {
				min = ter[i];
				//System.out.print("min " + ter[i] + " ");

			}
		}
		ans = Math.min(ans, max - min);
		//System.out.println("ans: "+ ans);
	}

}