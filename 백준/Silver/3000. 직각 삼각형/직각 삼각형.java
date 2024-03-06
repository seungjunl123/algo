import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	// 점의 개수 N 선언
	static int N;
	static int[][] map;
	static long[] x;
	static long[] y;

	// 직각 삼각형 개수
	static long count = 0;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// N
		N = Integer.parseInt(br.readLine());

		// 각 점의 x,y좌표를 저장할 배열 생성
		map = new int[N][2];
		x = new long[100002];
		y = new long[100002];

		// N만큼 반복 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[i][0] = a;
			map[i][1] =b;
			x[a]++;
			y[b]++;
		}

		// 3개의 점에 대한 검증 진행
		// 각 변수가 N-2, N-1, N에서 끝나서 3개의 점이 중복없이 검증될 수 있게 한다
		for(int i=0;i<N;i++) {
			count += (x[map[i][0]]-1)*(y[map[i][1]]-1);
		}
		
		bw.write(count + "");
		bw.flush();
		bw.close();
	}

}