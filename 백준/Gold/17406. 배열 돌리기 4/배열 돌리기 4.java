import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] arr;
	static int[][] info;
	static int ans;
	static boolean[] visited;
	static int[] numbers;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 전체 배열의 정보를 저장
		arr = new int[N][M];
		// 회전 배열의 정보를 저장
		info = new int[K][3];
		visited = new boolean[K + 1];
		numbers = new int[K];
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 수열의 순서를 먼저 정하고 그 순서에 따라 회전시킨다
		order(0);

		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

	private static void order(int idx) {
		if (idx == K) {
//			System.out.println(Arrays.toString(numbers));
			rotate(0, arr);
			return;
		}
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				numbers[idx] = i;
				order(idx + 1);
				visited[i] = false;

			}
		}

	}

	private static void rotate(int k, int[][] res2) {
		if (k == K) {
			int sum = 0;
			// 마지막에 N만큼 반복하면서 각 열의 합 중 최솟값을 구한다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					sum += res2[i][j];
				}
				if (sum < ans) {
					ans = sum;
				}
				sum = 0;
			}
			return;
		}

		int[][] res = new int[N][M];

		// 돌릴 배열 재생성
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				res[i][j] = res2[i][j];
			}
		}

		int r = info[numbers[k]][0] - 1;
		int c = info[numbers[k]][1] - 1;
		int s = info[numbers[k]][2];
		// s에 따라 사분면 만큼의 작업을 수행

		for (int i = r - s; i < r; i++) {
			for (int j = c - s + 1 + (i-(r-s)); j <= c + s - (i-(r-s)); j++) {
				res[i][j] = res2[i][j - 1];
			}
		}
		for (int i = r + s; i > r; i--) {
			for (int j = c - s + ((r+s)-i); j <= c + s - 1 - ((r+s)-i); j++) {
				res[i][j] = res2[i][j + 1];
			}
		}
		for (int j = c - s; j < c; j++) {
			for (int i = r - s + (j-(c-s)); i <= r + s - 1 - (j-(c-s)); i++) {
				res[i][j] = res2[i + 1][j];
			}
		}
		for (int j = c + s; j > c; j--) {
			for (int i = r - s + ((c+s)-j) + 1; i <= r + s - ((c+s)-j); i++) {
				res[i][j] = res2[i - 1][j];
			}
		}
		rotate(k + 1, res);

	}
}
