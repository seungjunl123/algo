import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int N;
	static int[][] arr;
	static int[][] max;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[2][N];
			max= new int[2][N];
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<2;j++) {
					if(i==0) {
						max[j][i] = arr[j][i];
					} else if(i==1) {
						max[j][i] = Math.max(max[j][i-1], max[Math.abs(j-1)][i-1]+arr[j][i]) ;
					} else {
						max[j][i] =Math.max(max[Math.abs(j-1)][i-1]+arr[j][i], Math.max(max[j][i-2]+arr[j][i], max[Math.abs(j-1)][i-2]+arr[j][i]));
					}
				}
			}
			bw.write(Math.max(max[1][N-1], max[0][N-1])+"\n");
		}
		bw.flush();
		bw.close();
	}
}
