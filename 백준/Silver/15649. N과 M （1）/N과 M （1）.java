
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] arr;
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

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		} // arrInput

		NM(0, 0);

	} // end main method

	public static void NM(int m, int n) {
		if (m == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(store[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visit[i] == 0) {
				visit[i] = 1;
				store[m] = arr[i];
				NM(m + 1, n + 1);
				visit[i] = 0;
			}
		}
	} // end NM()

}
