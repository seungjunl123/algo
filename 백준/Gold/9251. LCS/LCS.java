import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static String s1, s2;
	static int[][] dp;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		s1 = br.readLine();
		s2 = br.readLine();
		
		dp = new int[s1.length()+1][s2.length()+1];
		
		for(int i=1;i<s1.length()+1;i++){
			for(int j=1;j<s2.length()+1;j++) {
				if(s1.charAt(i-1)==s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
 				}
			}
		}
		
		bw.write(dp[s1.length()][s2.length()]+"");
		bw.flush();
		bw.close();

	}

}
