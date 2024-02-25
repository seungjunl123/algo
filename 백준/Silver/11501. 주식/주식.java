import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
	// TC, N 선언
	static int TC;
	static int N;
	static int[] arr;
	// 주가 받는 배열 arr 선언
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// TC 입력 후 케이스 수 만큼 반복
		TC = Integer.parseInt(br.readLine());
		for (int T = 0; T < TC; T++) {
			// N 입력 및 N의 길이를 갖는 arr 생성
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			long sum = 0;
			int max = arr[N-1];
			for(int i=N-1;i>=0;i--) {
				if(arr[i]>max) {
					max = arr[i];
				}
				sum += max-arr[i];
			}
			// 가장 마지막 점부터 탐색 시작
			bw.write(sum + "\n");
		}
		bw.flush();
		bw.close();
	}
}