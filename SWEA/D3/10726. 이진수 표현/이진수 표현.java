import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M;
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = 0;
			for(int i=0;i<N;i++) {
				if((M & (1<<i)) == (1<<i)) ans++;
			}
			if(ans==N) {
				System.out.println("#"+t+" ON");
			} else {
				System.out.println("#"+t+" OFF");
			}
		}
	
	
	}

}