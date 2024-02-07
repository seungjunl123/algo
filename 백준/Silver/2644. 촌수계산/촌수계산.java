import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// DFS문제
	// N과 x,y을 static으로 선언
	static int N;
	static int x;
	static int y;
	// 중복 방지 위한 visited배열 선언, 길이는 N+1
	// 경로 배열 생성
	static int[] visited;
	static int[][] adjMatrix;
	// 촌수 이동 확인을 위한 cnt 변수 생성, 0으로 초기화
	static int cnt = 0;
	// 경로가 존재할 경우의 cnt 값을 저장할 result 변수 생성
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// N,x,y 변수 입력
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		// visited 배열 생성
		// N+1*N+1의 베열 생성, 받는대로 받으려고
		visited = new int[N+1];
		adjMatrix = new int[N+1][N+1];
		// 4번째줄 부터 받는 숫자의 경로 정보 입력+전치도
		int M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}
		// x visited 설정
		visited[x] = 1;
		//DFS 실행(초기 입력 정수는 x)
		DFS(x);
		// cnt==0 일경우 -1 출력
		if(result == 0) {
			result = -1;
		}
		System.out.print(result);
		
	}
	// DFS 함수 생성
	private static int DFS(int node) {
		// for문으로 N번의 반복을 통한 경로 확인, next로 변수 설정하면 보기 편하다
		 for(int next = 1;next<=N;next++) {
			 // if문으로 경로 이동 여부 확인
			 if(visited[next]!=1 && adjMatrix[node][next]==1) {
				 // 경로가 존재하고 아직 방문하지 않았을 경우(조건) cnt++, 해당 next에서 DFS 함수 재귀 실행
				 cnt++;
				 // next 값이 y 인데 if 문에 통과되면 해당 시기 cnt를 result에 저장
				 //System.out.println(node+" "+next+" "+cnt);
				 if(next == y) {
					 result = cnt;
					 cnt = 0;
					 break;
				 }
				 visited[next]=1;
				 DFS(next);
				 // 같은 분기 내에서 y값으로 가는게 아닌 다른 길이 있을 경우 되돌아가므로 cnt를 1 내린다
				 cnt--;
			 }
			 
		 }
		 return 0;
	}
	
}
