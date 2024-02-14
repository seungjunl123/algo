import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	// 계단의 개수 N 선언
	static int N;
	// 계단 별 점수 받을 배열 arr 선언
	static int[] arr;
	// 계단까지의 최대합을 기록하는 memoization 배열 선언
	static int[] memoization;
	// br, bw 선언
	static BufferedWriter bw;
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// N 변수 입력
		N = Integer.parseInt(br.readLine());
		// arr,memo 생성(N+1로!)
		arr = new int[N+1];
		memoization = new int[N+1];
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		// memoization 배열에 처음값 입력
		memoization[1] = arr[1];
		if(N>=2) {
			memoization[2] = arr[1]+arr[2];
			if(N>=3) {
				// DFS 실행
				DFS();				
			}
		}
		
		// memoization의 마지막 값 출력
		bw.write(memoization[N]+"");
		bw.flush();
		bw.close();
	}
	public static void DFS() {
		//초기 memo값인 1,2를 통해 수행
		for(int i=3;i<=N;i++) {
			// memo i값은 i-2의 memo값과 더하는 방법과
			// i-3에서의 momo값과 i-1의 arr을 더할 수 있다
			memoization[i] = Math.max(memoization[i-2]+arr[i], memoization[i-3]+arr[i-1]+arr[i]);
		}
		// N까지 해당 DP를 완료
	}
	
}
