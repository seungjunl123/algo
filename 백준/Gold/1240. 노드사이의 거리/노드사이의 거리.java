import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int N,M,ans;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		for(int i=0;i<N-1;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[a][b] = c;
			arr[b][a] = c;
		}
		
		for(int i=0;i<M;i++) {
			ans = 0;
			visited = new boolean[N+1];
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			visited[a]=true;
			findPath(a,b,0);
			
			bw.write(ans+"\n");
		}
		bw.flush();
		bw.close();
		
	}

	private static void findPath(int start, int end, int sum) {
		if(ans!=0) return;
		if(start == end) {
			ans = sum;
			return;
		} 
		for(int i=1;i<=N;i++) {
			if(!visited[i]&&arr[start][i]!=0) {
				visited[i]=true;
//				System.out.println("end"+end+" "+i);
				findPath(i,end,sum+arr[start][i]);
				visited[i] =false;
			}
		}
		
	}
}