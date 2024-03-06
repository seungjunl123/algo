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

	// visited 설정, 벽을 한번 넘어갈 경우를 구분하기 위해 1개의 차원을 더 늘린다
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
		// map은 0일 때  false, 1일때 true로 받고
		map = new boolean[N][M];
		// visited의 첫번째 차원은 벽을 넘었냐, 안넘었냐를 구분
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
		// BFS를 실행했을 때 목표점에 도달하지 못했을 경우 -1출력
		if (ans == Integer.MAX_VALUE)
			ans = -1;

		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

	static void findway() {
		// BFS로 풀기 // DFS 시간초과 , 경로 구분 없이 BFS 진행 시 메모리 초과
		Queue<int[]> q1 = new LinkedList<>();

		// ans로 전환할 distance => 필요 없다 static 변수인 ans로 바로 저장해도 가능
		int distance = 0;
		
		// 0으로 들어갔는지 1로 들어갔는지 어케아냐 -> 넣는 배열의 원소를 하나 더 넣자(드가는 차원)
		// 시작지점에 0,0으로 출발하고, 벽을 넘은채로 왔는지/안왔는지를 기록하는 3번째 인자를 생성
		int[] start = { 0, 0 , 0};
		q1.offer(start);
		visited[0][0][0] = true;

		// q의 사이즈만큼 반복
		while (!q1.isEmpty()) {
			distance++;
			int size = q1.size();
			for (int i = 0; i < size; i++) {
				int[] node1 = q1.poll();
				// 추출한 노드가 도착점이면 그때까지의 distance를 ans에 저장
				if (node1[0] == N - 1 && node1[1] == M - 1) {
					ans = distance;
					return;
				}
				// 이동가능한 델타배열로의 이동을 위한 반복문 실행
				for (int j = 0; j < 4; j++) {
					int nr = node1[0] + dr[j];
					int nc = node1[1] + dc[j];
					if (nr >= 0 && nc >= 0 && nr < N && nc < M ) {
						
						// map이 0일 때
						if(!map[nr][nc]) {							
						// 이전 노드의 이동 값(node의 3번째 인자)이 0이면 visited[0]을 채운다
							if(node1[2]==0 && !visited[0][nr][nc]) {
								visited[0][nr][nc]= true;
								int[] newNode = {nr,nc,0};
								q1.offer(newNode);
							} 
						// 이전 노드의 이동 값이 1이면 visited[1]을 채운다
							if(node1[2]==1&&!visited[1][nr][nc]){
								// 벽을 넘은 차원인 경우에는 계속 그 차원에서 이동해야 한다
								visited[1][nr][nc]=true;
								int[] newNode = {nr,nc,1};
								q1.offer(newNode);
							}
						// map이 1일 때
						} else {
							// 이전 노드가 0이고(1일 경우 더이상 벽넘는게 불가능), 벽을 넘은 차원이 false일 때
							// 벽을 넘지 않은 경우에서 넘은 경우로 차원을 변경해서 이동한다
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
