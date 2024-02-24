import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main{
	static int N;
	static int M;
	// 4방 탐색을 위한 델타 배열 생성
	static final int[] dr = {0,1,0,-1};
	static final int[] dc = {1,0,-1,0};
	
	static char[][] map;
	static int[][] visited;
	static int wsum;
	static int bsum;
	static int count;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		// DFS를 통한 군사 별 제곱값 비교
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N과 M 입력
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		// StringTokenizer로 1줄을 입력 받은 후
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			// char배열에 한글자씩 입력
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j);
			}
		}
		// visted[][] 배열 생성
		visited = new int[N][M];
		// 2차 반복을 통한 map 탐색
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				// visited[][]가 비어있다면 W,B에 따른 DFS 실행
				if(visited[i][j]==0) {
					if(map[i][j]=='W') {
						// W라면 wsum에 리턴되는 제곱값 추가, B라면 bsum에
						wsum += WDFS(i,j);
						count = 0;
					} else {
						bsum += BDFS(i,j);
						count = 0;
					}
				}
			}
		}
		// DFS순회가 끝나면 각 지역에 대한 위력의 합 출력
		bw.write(wsum+" "+bsum);
		bw.flush();
		bw.close();
	}
	
	// DFS 메서드
	static int WDFS(int r, int c) {
		visited[r][c] = 1;
		// count 1 추가
		count++;
		for(int i=0;i<4;i++) {
			// 사방탐색 진행
			int nr = r+dr[i];
			int nc = c+dc[i];
			// 사방 중에 같은 글자가 있고, visted가 비어있다면
			if(nr>=0&&nr<N&&nc>=0&&nc<M&&map[nr][nc]=='W'&&visited[nr][nc]==0) {
				// 그 지역에 대한 DFS 실행
				WDFS(nr,nc);
			}
		}
		// 모든 방향에서 DFS가 불가능하면 진행했던 카운트의 제곱값을 리턴
		int sum = count;
		return sum*sum;
	}
	static int BDFS(int r, int c) {
		visited[r][c] = 1;
		// count 1 추가
		count++;
		for(int i=0;i<4;i++) {
			// 사방탐색 진행
			int nr = r+dr[i];
			int nc = c+dc[i];
			// 사방 중에 같은 글자가 있고, visted가 비어있다면
			if(nr>=0&&nr<N&&nc>=0&&nc<M&&map[nr][nc]=='B'&&visited[nr][nc]==0) {
				// 그 지역에 대한 DFS 실행
				BDFS(nr,nc);
			}
		}
		// 모든 방향에서 DFS가 불가능하면 진행했던 카운트의 제곱값을 리턴
		int sum = count;
		return sum*sum;
	}
}