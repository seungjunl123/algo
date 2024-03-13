import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static int[][] min, max;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		min = new int[N][3];
		max = new int[N][3];

		min[0][0] = arr[0][0];
		min[0][1] = arr[0][1];
		min[0][2] = arr[0][2];
		max[0][0] = arr[0][0];
		max[0][1] = arr[0][1];
		max[0][2] = arr[0][2];
		for (int i = 1; i < N; i++) {
			// 왼쪽 최소, 최대값
			min[i][0] = Math.min(min[i-1][0] + arr[i][0], min[i-1][1] + arr[i][0]);
			max[i][0] = Math.max(max[i-1][0] + arr[i][0], max[i-1][1] + arr[i][0]);

			// 오른쪽 최소, 최대값
			min[i][2] = Math.min(min[i-1][2] + arr[i][2], min[i-1][1] + arr[i][2]);
			max[i][2] = Math.max(max[i-1][2] + arr[i][2], max[i-1][1] + arr[i][2]);
			
			// 중앙값은 왼쪽 중앙 오른쪽 다 봐야해서
			min[i][1] = Math.min(Math.min(min[i-1][0] + arr[i][1], min[i-1][1] + arr[i][1]),min[i-1][2]+arr[i][1]);
			max[i][1] = Math.max(Math.max(max[i-1][0] + arr[i][1], max[i-1][1] + arr[i][1]),max[i-1][2]+arr[i][1]);
			//System.out.println(min[0]+" "+min[1]+" "+min[2]);
		}
		bw.write(Math.max(max[N-1][1], Math.max(max[N-1][0], max[N-1][2]))+ " "+Math.min(min[N-1][1], Math.min(min[N-1][0], min[N-1][2])));
		bw.flush();
		bw.close();
	}
}
