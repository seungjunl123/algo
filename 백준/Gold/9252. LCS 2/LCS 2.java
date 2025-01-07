import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static String fir, sec;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();
	// 일단 완전 탐색
		// fir과 sec 중 같은 글자가 있다
		// 이전까지의 최댓값과 현재 값에 +1한 것 중 큰 걸 고른다 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		fir = br.readLine();
		sec = br.readLine();
		String str="";
		if(fir.length()<sec.length()) {
			String tmp = sec;
			sec = fir;
			fir = tmp;
		}
		dp = new int[fir.length()+1][sec.length()+1];
		
		for(int i=1;i<=fir.length();i++) {
			for(int j=1;j<=sec.length();j++) {
				if(fir.charAt(i-1)==sec.charAt(j-1)) {
					dp[i][j] =  dp[i-1][j-1]+1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		int x = fir.length();
		int y = sec.length();

		while (x != 0 && y != 0) {
//			System.out.println(x+" "+y+" "+dp[x][y]);
			if (dp[x - 1][y] == dp[x][y]) { // 왼쪽값과 같다
				x -= 1;
			} else if (dp[x][y - 1] == dp[x][y]) { // 윗쪽값과 같다.
				y -= 1;
			} else { // 왼쪽값과 윗쪽값과 같은 경우가 없다.
				str += fir.charAt(x-1);
				x -= 1;
				y -= 1;
			}

		}

		System.out.println(dp[fir.length()][sec.length()]);
		if(dp[fir.length()][sec.length()]!=0) {
			for (int i = str.length()-1; i >= 0 ; i--) {
				System.out.print(str.charAt(i));
			}
		}
		
	}

}