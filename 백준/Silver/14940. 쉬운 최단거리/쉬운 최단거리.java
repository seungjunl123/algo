import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int n;
	static int m;
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, -1, 0, 1 };
	static int[][] arr, distance;
	static int[][] visited;
	static int min;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// 변수 입력
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new int[n][m];
		distance = new int[n][m];
		
		int x= 0;
		int y= 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == 2){
					x = i;
					y = j;
				}
			}
		}
		// 각 배열 정보에 대한 BFS
		// 반복문을 실행
		BFS(x, y);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(visited[i][j]==0) {
					distance[i][j] = -1;
				}
				if(arr[i][j] == 0) {
					distance[i][j] = 0;
				}
				// 카운트 초기화
				bw.write(distance[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();

		// 배열 값에 대한 BFS 실행
		// 카운트값 출력(배열 처럼)

	}

	static void BFS(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		int[] Arr = new int[] {r,c};
		q.offer(Arr);
		visited[r][c] = 1;
		int count = 1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] node = q.poll();
				int a = node[0];
				int b = node[1];
				for(int j=0;j<4;j++) {
					int nr = a+dr[j];
					int nc = b+dc[j];
					if(nr>=0&&nc>=0&&nr<n&&nc<m&&visited[nr][nc]==0&&arr[nr][nc]==1) {
						int[] narr = new int[] {nr,nc};
						distance[nr][nc] = count;
						q.offer(narr);
						visited[nr][nc]= 1;
					}
				}
				
			}
			count++;
		}
	}

	// BFS 메서드
	// 큐 생성
	// 사방탐색
	// 사방 중에 1이 있으면 count++ while문으로 사방 다시 탐색
	// 2발견하면 count 리턴
}