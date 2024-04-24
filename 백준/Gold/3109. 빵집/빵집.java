import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int R, C;
	static boolean[][] map;
	static final int[][] dir = {{-1,1},{0,1},{1,1}};
	static int ans;
	static boolean flag;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	// [BOJ] 3109_빵집
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		ans = 0;
		map = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if(line.charAt(j)=='.') map[i][j] = false;
				else map[i][j] = true;
			}
		}
		// DFS 실행
		for(int i=0;i<R;i++) {	
			flag = false;
			findRoad(i,0);
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}

	static void findRoad(int r, int c) {
		if(c==(C-1)) {
			ans++;
			// 마지막에 도달한 경우 그냥 DFS를 멈춰야 한다
			flag = true;
			return;
		}
		for(int i=0;i<3;i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if(!flag&&nr>=0&&nc>=0&&nr<R&&nc<C&&!map[nr][nc]) {
				map[nr][nc]=true;
				findRoad(nr,nc);
			}
		}
	}

}