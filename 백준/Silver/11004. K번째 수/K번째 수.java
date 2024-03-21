import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	// 맨 왼쪽 초기 값을 피벗으로 지정

	// 맨 왼쪽이랑 맨 오른쪽에서 탐색을 시작
	// 피벗보다 크면 오른쪽, 피벗보다 작으면 왼쪽

	// 왼쪽에서 피봇값보다 크거나, 오른쪽에서 피벗값보다 작은 값을 찾아서
	// 두 값을 교환해준다

	// 교환 후에 다시 피벗부터 왼쪽 오른쪽을 탐색
	// 탐색하는데 왼쪽부터 탐색하던 i가 오른쪽부터 탐색하던 j와 교차하면
	// 정렬이 끝났으므로 pivot을 중간에 위치

	// pivot의 오른쪽(그 다음으로 큰 값)부터 다시 정렬 시작

	static int N, K;
	static int[] arr;
	static int ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		bw.write(arr[K-1]+"");
		bw.flush();
		bw.close();
	}



}