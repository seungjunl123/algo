
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투포인터
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int minSize = 100000;

		// 배열 입력
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		int sum = arr[0];

		while (true) {
			if (end == N || start > end) {
				break;
			}
			if (sum >= S) {
				minSize = Math.min(minSize, end - start + 1);
				if (minSize == 1) {
					break;
				}
				sum -= arr[start++];
			} else if (sum < S) {
				++end;
				if (end == N) {
					break;
				}
				sum += arr[end];

			}
		} // end while()

		if (minSize == 100000) {
			System.out.println(0);
		} else {
			System.out.println(minSize);

		}

	} // end main method

}
