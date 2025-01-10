import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 해당 칸의 가로, 세로,3*3의 정사각형이 모두 중복 없어야 함
	// 가로, 세로, 정사각형에서 모두 없는 수 중에 큰 수로 
	static int[][] map = new int[9][9];
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 입력
		for(int i=0;i<9;i++) {
			String str = br.readLine();
			for(int j=0;j<9;j++) {
				map[i][j] =str.charAt(j)-'0';
			}
		}
		
		dfs(0);
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
		
	}
	private static void dfs(int idx) {
		if(idx == 81) {
			flag =true;
			return;
		}
		
		int r = idx/9;
		int c = idx%9;
		if(map[r][c]!=0) dfs(idx+1);
		else {
			for(int i=1;i<=9;i++) {
				if(!isValid(r,c,i)) continue;
				map[r][c] = i;
				dfs(idx+1);
				
				if(flag) return;
				map[r][c] = 0;
				
			}
		}
		
		
	}
	private static boolean isValid(int r, int c, int i) {
		for(int idx=0;idx<9;idx++) {
			if(map[idx][c]==i || map[r][idx]==i)return false;
		}

		int sr = r/3*3;
		int sc = c/3*3;
		for(int rw =sr;rw<sr+3;rw++) {
			for(int cl = sc ;cl<sc+3;cl++) {
				if(map[rw][cl]==i) return false;
			}
		}
		return true;
	}


}