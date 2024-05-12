import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] map;
	static int max = 0;
	static int ans = 0;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// 풀이
		// 1. 최대한 커질 수 있는 4개의 값을 주어진 테트로미노에 맞춰야 한다
		// 2. 맵을 돌면서 최대값을 찾는다
		// 3. 이 때 최대 값을 가지는 네모에서 시작해서 테트로미노에 있는 값을 찾아준다
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
					tetromino1(i, j);
					tetromino2(i, j);
					tetromino3(i, j);
			}
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();

	}
	
	// 일자형
	private static void tetromino1(int r, int c) {
		int tmp;
		if(r+3<n) {
			tmp = map[r][c]+map[r+1][c]+map[r+2][c]+map[r+3][c];
			if(tmp>ans) ans = tmp;
		}
		if(r-3>=0) {
			tmp = map[r][c]+map[r-1][c]+map[r-2][c]+map[r-3][c];
			if(tmp>ans) ans = tmp;
		}
		if(c+3<m) {
			tmp = map[r][c]+map[r][c+1]+map[r][c+2]+map[r][c+3];
			if(tmp>ans) ans = tmp;
		}
		if(c-3>=0) {
			tmp = map[r][c]+map[r][c-1]+map[r][c-2]+map[r][c-3];
			if(tmp>ans) ans = tmp;
		}
	}
	// 사각형
	private static void tetromino2(int r, int c) {
		int tmp;
		if(r+1<n) {
			if(c+1<m) {				
				tmp = map[r][c]+map[r+1][c]+map[r][c+1]+map[r+1][c+1];
				if(tmp>ans) ans = tmp;
			}
			if(c-1>=0) {
				tmp = map[r][c]+map[r+1][c]+map[r][c-1]+map[r+1][c-1];
				if(tmp>ans) ans = tmp;
				
			}
		}
		if(r-1>=0) {
			if(c+1<m) {				
				tmp = map[r][c]+map[r-1][c]+map[r][c+1]+map[r-1][c+1];
				if(tmp>ans) ans = tmp;
			}
			if(c-1>=0) {
				tmp = map[r][c]+map[r-1][c]+map[r][c-1]+map[r-1][c-1];
				if(tmp>ans) ans = tmp;
				
			}
		}

	}
	// 나머지는 긴쪽으로 3칸, 짧은쪽은 1칸이니까 한꺼번에 검사
	private static void tetromino3(int r, int c) {
		int tmp;
		if(r+2<n) {
			if(c+1<m) {
				tmp = map[r][c]+map[r+1][c]+map[r+2][c]+map[r+2][c+1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r+1][c]+map[r+1][c+1]+map[r+2][c+1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r+1][c]+map[r+1][c+1]+map[r+2][c];
				if(tmp>ans) ans = tmp;
			}
			if(c-1>=0) {
				tmp = map[r][c]+map[r+1][c]+map[r+2][c]+map[r+2][c-1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r+1][c]+map[r+1][c-1]+map[r+2][c-1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r+1][c]+map[r+1][c-1]+map[r+2][c];
				if(tmp>ans) ans = tmp;
			}
		}
		if(r-2>=0) {
			if(c+1<m) {
				tmp = map[r][c]+map[r-1][c]+map[r-2][c]+map[r-2][c+1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r-1][c]+map[r-1][c+1]+map[r-2][c+1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r-1][c]+map[r-1][c+1]+map[r-2][c];
				if(tmp>ans) ans = tmp;
			}
			if(c-1>=0) {
				tmp = map[r][c]+map[r-1][c]+map[r-2][c]+map[r-2][c-1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r-1][c]+map[r-1][c-1]+map[r-2][c-1];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r-1][c]+map[r-1][c-1]+map[r-2][c];
				if(tmp>ans) ans = tmp;
			}
		}
		if(c+2<m) {
			if(r+1<n) {
				tmp = map[r][c]+map[r][c+1]+map[r][c+2]+map[r+1][c+2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c+1]+map[r+1][c+1]+map[r+1][c+2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c+1]+map[r+1][c+1]+map[r][c+2];
				if(tmp>ans) ans = tmp;
			}
			if(r-1>=0) {
				tmp = map[r][c]+map[r][c+1]+map[r][c+2]+map[r-1][c+2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c+1]+map[r-1][c+1]+map[r-1][c+2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c+1]+map[r-1][c+1]+map[r][c+2];
				if(tmp>ans) ans = tmp;
			}
		}
		if(c-2>=0) {
			if(r+1<n) {
				tmp = map[r][c]+map[r][c-1]+map[r][c-2]+map[r+1][c-2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c-1]+map[r+1][c-1]+map[r+1][c-2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c-1]+map[r+1][c-1]+map[r][c-2];
				if(tmp>ans) ans = tmp;
			}
			if(r-1>=0) {
				tmp = map[r][c]+map[r][c-1]+map[r][c-2]+map[r-1][c-2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c-1]+map[r-1][c-1]+map[r-1][c-2];
				if(tmp>ans) ans = tmp;
				
				tmp = map[r][c]+map[r][c-1]+map[r-1][c-1]+map[r][c-2];
				if(tmp>ans) ans = tmp;
			}
		}
	}

}
