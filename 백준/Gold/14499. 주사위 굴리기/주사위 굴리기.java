import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 14499 주사위 굴리기(풀었다)))))))))
class Main{
	static int n,m,K,x,y;
	static int[][] map;
	static int[][] dir = {{},{0,1},{0,-1},{-1,0},{1,0}};
	static int[] order;
	static int[] dice;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		map = new int[n][m];
		order = new int[K];
		dice = new int[7];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		// bottom,top,E,W,S,N 방향을 정해서 움직이는 방향에 따라 이동
		rollTheDice(x,y,1,6,3,4,5,2,0,false);
		
		bw.flush();
		bw.close();
	}
	// 아래 위 동 서 남 북 체크
	private static void rollTheDice(int r, int c, int bot, int top, int E, int W, int S, int N, int k, boolean pass) throws IOException {
		if(!pass) {
			if(k!=0) bw.write(dice[top]+"\n");
			if(map[r][c]==0) map[r][c] = dice[bot];
			else {
				dice[bot] = map[r][c];
				map[r][c]= 0;
			}
		}
		// 현재 지점에서 top의 값을 출력
		//if(k!=0) {
		//	bw.write(dice[top]+"\n");
		//}
		// map의 값에 따라 bot의 값을 저장
		//if(map[r][c]==0) map[r][c] = dice[bot];
		//else dice[bot] = map[r][c];
		
		// k가 K번의 이동을 한거면 그만 return
		if(k==K) return;
		else if((r+dir[order[k]][0]<0||r+dir[order[k]][0]>=n||c+dir[order[k]][1]<0||c+dir[order[k]][1]>=m)) {
			rollTheDice(r,c,bot,top,E,W,S,N,k+1,true);
		}
		// 그게 아니라면 주어진 방향에 따라 주사위를 돌린다
		else {
			// 동
			if(order[k]==1) {
				// map의 값에 따라 bot의 값을 저장
				//if(r+dir[1][0]>=0&&r+dir[1][0]<N&&c+dir[1][1]<M&&c+dir[1][1]>=0) {
				rollTheDice(r+dir[1][0],c+dir[1][1],E,W,top,bot,S,N,k+1,false);
				//}
			// 서
			} else if(order[k]==2) {
				// map의 값에 따라 bot의 값을 저장
			
				rollTheDice(r+dir[2][0],c+dir[2][1],W,E,bot,top,S,N,k+1,false);
			// 북
			} else if(order[k]==3) {
				// map의 값에 따라 bot의 값을 저장

				rollTheDice(r+dir[3][0],c+dir[3][1],S,N,E,W,top,bot,k+1,false);
			// 남
			} else {
				// map의 값에 따라 bot의 값을 저장
				rollTheDice(r+dir[4][0],c+dir[4][1],N,S,E,W,bot,top,k+1,false);
			}
		}
	}
}