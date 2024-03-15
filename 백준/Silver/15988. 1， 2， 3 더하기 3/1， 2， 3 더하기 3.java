import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		long[] dp = new long[1000001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= 1000000; i++) {
			dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]) % 1000000009;
		}

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());// 숫자
			sb.append(dp[N] + "\n");
		}
		
		System.out.println(sb.toString());

	}

}