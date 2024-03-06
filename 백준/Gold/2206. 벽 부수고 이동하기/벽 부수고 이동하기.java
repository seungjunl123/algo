import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	// 지역 별 정보를 담을 N과 map 선언
	static int N, M;
	static boolean[][] map;

	// 델타 배열
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { -1, 0, 1, 0 };

	static boolean[][][] visited;

	// 최솟값을 저장할 변수
	static int ans;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		visited = new boolean[2][N][M];

		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			for (int j = 0; j < M; j++) {
				if (a.charAt(j) == '1') {
					map[i][j] = true;
				} else {
					map[i][j] = false;
				}
//				System.out.print(map[i][j]+" ");
			}
//			System.out.println();
		}
		// 경로 찾기 실행
		findway();
		if (ans == Integer.MAX_VALUE)
			ans = -1;

		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

	static void findway() {
		// BFS로 풀기 // DFS 시간초과
		Queue<int[]> q1 = new LinkedList<>();
		// 벽을 만나는 카운트가 2가 되면 탈락
		int distance = 0;
		// 시작지점인 0,0을 넣고 시작
		// nr,nc 넣고 들어온 부분이 0차원인지 1차원인지 보자
		int[] start = { 0, 0 , 0};
		q1.offer(start);
		visited[0][0][0] = true;

		// q의 사이즈만큼 반복
		while (!q1.isEmpty()) {
			distance++;
			int size = q1.size();
			for (int i = 0; i < size; i++) {
				int[] node1 = q1.poll();
//				System.out.println(node1[0]+" "+node1[1]+" "+visited[1][node1[0]][node1[1]]);
				// 추출한 노드가 도착점이면 그때까지의 distance를 ans에 저장
				if (node1[0] == N - 1 && node1[1] == M - 1) {
					ans = distance;
					return;
				}
				// node2에서 각 노드를 더할 때 map의 인자를 더해준다
				for (int j = 0; j < 4; j++) {
					// visited를 2개의 차원으로 나눈다(3차원 배열)
					// 1을 만나면 다음 차원에서 이동
					// 1을 두번 만나면 더 이상 이동할 차원이 없어 리턴
					int nr = node1[0] + dr[j];
					int nc = node1[1] + dc[j];
					if (nr >= 0 && nc >= 0 && nr < N && nc < M ) {
						// 이전 노드의 값이 1이면 visited[1]을 채운다
						// 이전 노드의 값이 0이면 visited[0]을 채운다
							// 0으로 들어갔는지 1로 들어갔는지 어케아냐 -> 넣는 배열의 원소를 하나 더 넣자(드가는 차원)
						// 현재 노드의 값이 1일 때 vistied[1]이 true면 continue;
						// 현재 노드 값이 1일 때 visited[1] false 면 visited[1] true, q1.에 추가
						
						// map이 0일 때
						if(!map[nr][nc]) {							
							if(node1[2]==0 && !visited[0][nr][nc]) {
								visited[0][nr][nc]= true;
								int[] newNode = {nr,nc,0};
								q1.offer(newNode);
							} 
							if(node1[2]==1&&!visited[1][nr][nc]){
								visited[1][nr][nc]=true;
								int[] newNode = {nr,nc,1};
								q1.offer(newNode);
							}
						// map이 1일 때
						} else {
							if(node1[2]==0&&!visited[1][nr][nc]) {
								visited[1][nr][nc]=true;
								int[] newNode = {nr,nc,1};
								q1.offer(newNode);
							}
						}
	

					}
				}
			}
		}
	}

}