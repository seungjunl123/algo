
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int[] store;
	private static int[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		store = new int[M];
		visit = new int[N];

		NM(0, 0, 0);

	} // end main method

	private static void NM(int t, int n, int m) {
		if (m == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(store[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = t; i < N; i++) {
			if (visit[i] == 0) {
				visit[i] = 1;
				store[m] = i + 1;
				NM(++t, n + 1, m + 1);
				visit[i] = 0;
			}
		}

	} // end NM()

}
