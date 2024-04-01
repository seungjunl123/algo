import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	static int T,N;
	static int[][] map;
	static final int[] dr= {-1,0,1,0};
	static final int[] dc = {0,-1,0,1};
	static int ans;
	
	static int stR,stC,enR,enC;
	static boolean[][] visited;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map= new int[N][N];
			visited= new boolean[N][N];
			ans = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					 map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			stR = Integer.parseInt(st.nextToken());
			stC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			enR = Integer.parseInt(st.nextToken());
			enC = Integer.parseInt(st.nextToken());
			
			BFS(stR,stC);
			
			if(ans == 0) ans = -1;
			bw.write("#"+t+" "+ans+"\n");
			
		}
		bw.flush();
		bw.close();
	}
	private static void BFS(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		int count= 0;
		
		q.add(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			count++;
			for(int i=0;i<size;i++) {
				int[] node = q.poll();
				if(node[0]==enR&&node[1]==enC) {
					ans = count-1;
					return;
				}
				for(int j=0;j<4;j++) {
					int nr = node[0]+dr[j];
					int nc = node[1]+dc[j];
					if(nr>=0&&nc>=0&&nr<N&&nc<N&&!visited[nr][nc]) {
						if(map[nr][nc]==0) {
							visited[nr][nc]= true;
							q.add(new int[] {nr,nc});
						} else if(map[nr][nc]==2) {
							if(count%3==0) {
								visited[nr][nc]= true;
								q.add(new int[] {nr,nc});
							} else {
								q.add(node);
							}
						}
					}
				}
			}
		} 
		
	}
}