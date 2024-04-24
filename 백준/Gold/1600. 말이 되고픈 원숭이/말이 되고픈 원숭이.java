import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r,c,jump,dim;
	public Node(int r,int c, int jump, int dim) {
		this.r = r;
		this.c = c;
		this.jump = jump;
		this.dim = dim;
	}
}

class Main {
	static int K, R, C;
	static int[][] map;
	static boolean[][][] visited;
	static final int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static final int[][] Hdir = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
	static int ans;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	// [BOJ] 1600_말이 되고픈 원숭이
	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[K+1][R][C];
		
		ans = Integer.MAX_VALUE;
		monkeyWantToBeHorse();
		if(ans==Integer.MAX_VALUE) {
			bw.write(-1+" ");
		} else {
			bw.write(ans+"");
		}
		bw.flush();
		bw.close();
	}

	private static void monkeyWantToBeHorse() {
		int count = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0,K,0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				Node node = q.poll();
				if(node.r==R-1&&node.c==C-1) {
					if(count<ans) ans = count;
					return;
				}
				for(int j=0;j<4;j++) {
					int nr = node.r + dir[j][0];
					int nc = node.c + dir[j][1];
					if(nr>=0&&nc>=0&&nr<R&&nc<C&&map[nr][nc]!=1&&!visited[node.dim][nr][nc]) {
						visited[node.dim][nr][nc]=true;
						q.add(new Node(nr,nc,node.jump,node.dim));
					}
				}
				if(node.jump>0) {
					for(int j=0;j<8;j++) {
						int nr = node.r + Hdir[j][0];
						int nc = node.c + Hdir[j][1];
						if(nr>=0&&nc>=0&&nr<R&&nc<C&&map[nr][nc]!=1&&!visited[node.dim+1][nr][nc]) {
							visited[node.dim+1][nr][nc]=true;
							q.add(new Node(nr,nc,node.jump-1,node.dim+1));
						}
					}
				}
			}
			count++;
		}
	}
}