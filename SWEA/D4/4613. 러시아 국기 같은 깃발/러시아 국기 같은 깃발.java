import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N;
	static int M;

	static char[][] arr;
	static int[][] col;

	static int min;

	public static void main(String[] args) throws IOException {
		// 값 다 받고
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int TC = 1; TC <= T; TC++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new char[N][M];
			col = new int[N][3];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				String row = br.readLine();
				for (int j = 0; j < M; j++) {
					arr[i][j] = row.charAt(j);
					// 국기에 대한 색 입력
					if (arr[i][j] == 'W') {
						col[i][0]++;
					} else if (arr[i][j] == 'B') {
						col[i][1]++;
					} else if (arr[i][j] == 'R') {
						col[i][2]++;
					}
				}
			}

			// i=0,i=N-1 일때는 W, R
			// B는 i=1부터 N-2까지 올 수 있다
			// 그리고 1줄부터 N-2줄까지 생성이 가능하다
			// 그렇다면? B의 시작 위치와 B의 길이를 지정해서 모든 경우를 계산하자
			int sp = 1;
			int blen = 1;
			int sum = 0;
			// B의 길이가 N-2가 되어 마지막 크기를 구할 때 까지 반복
			while (blen < N - 1) {
//				System.out.println("blen"+blen);
				// 스타트 지점은 계속 증가할건데 sp+blen-1이 N-2가 될때까지 한다
				while (sp + blen - 1 < N - 1) {
//					System.out.println("sp"+sp);
					// sp가 쭉쭉 올라서 sp+blen-1 = N-2가 만족되면 sp를 1로 초기화하고 blen++
					// 흰색 부분에 대한 변경 값
					for (int i = 0; i < sp; i++) {
//						System.out.println(M- col[i][0]);
						sum += M - col[i][0];
					}
					// 청색 부분에 대한 변경 값
					for (int i = sp; i < sp + blen; i++) {
//						System.out.println(M- col[i][1]);
						sum += M - col[i][1];
					}
					// 적색 부분에 대한 변경 값
					for (int i = sp + blen; i < N; i++) {
//						System.out.println(M- col[i][2]);
						sum += M - col[i][2];
					}
					// 모든 행에 대한 변경값을 구하고 min값과 비교
//					System.out.println("sum"+sum);
					if (sum < min) {
						min = sum;
					}
//					System.out.println("min"+min);
					// 비교가 끝나면 스타트 지점을 1늘리고 sum 초기화
					sum = 0;
					sp++;
				}
				blen++;
				sp = 1;
			}
			// 전부 구하고 난 후 min값 뽑자
			bw.write("#"+TC+" "+min+"\n");

		}
		bw.flush();
		bw.close();
	}
}
