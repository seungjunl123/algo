import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[] number;
	static int[] cal = new int[4];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		number = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}

		DFS(0, number[0]);
		
		bw.write(max+"\n"+min);
		bw.flush();
		bw.close();

	}

	private static void DFS(int idx, int num) {
		if (idx == N - 1) {
			if (max < num)
				max = num;
			if (min > num)
				min = num;
			return;
		}
		if (cal[0] != 0) {
			cal[0]--;
			DFS(idx+1,num+number[idx+1]);
			cal[0]++;
		}
		if (cal[1] != 0) {
			cal[1]--;
			DFS(idx+1,num-number[idx+1]);
			cal[1]++;
		}
		if (cal[2] != 0) {
			cal[2]--;
			DFS(idx+1,num*number[idx+1]);
			cal[2]++;
		}
		if (cal[3] != 0) {
			cal[3]--;
			DFS(idx+1,num/number[idx+1]);
			cal[3]++;
		}
	}
}