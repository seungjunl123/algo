import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[k+1];
		int[] coin = new int[n];
		
		dp[0] = 1;
		for(int i=0;i<n;i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0;i<n;i++) {
			for(int j=1;j<=k;j++) {
				if(j>=coin[i])	dp[j] += dp[j-coin[i]];
			}
		}
		
		System.out.println(dp[k]);
	}
}