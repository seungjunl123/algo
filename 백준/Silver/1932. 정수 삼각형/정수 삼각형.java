import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int[][] max = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<i+1;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max[0][0] = arr[0][0];
		for(int i=1;i<N;i++) {
			for(int j=0;j<=i;j++) {
				if(j-1>=0) {
					max[i][j] = Math.max(max[i-1][j-1]+arr[i][j],max[i-1][j]+arr[i][j]);
				} else {
					max[i][j] = max[i-1][j] + arr[i][j];
				}
			}
		}
		int ans =0;
		for(int i=0;i<N;i++) {
			if(ans<max[N-1][i]) {
				ans = max[N-1][i];
			}
		}
		bw.write(ans+"");
        bw.flush();
        bw.close();
	}
}
