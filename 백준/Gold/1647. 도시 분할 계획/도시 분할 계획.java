import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int w;
	int cost;
	
	public Edge (int w, int cost) {
		this.w = w;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return cost - o.cost;
	}
}

public class Main{
	static int N, M;
	static boolean[] visited;
	static int max, ans_dist;
	static List<Edge>[] list;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		 st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 
		 visited = new boolean[N+1];
		 max = 0;
		 ans_dist = 0;
		 list = new ArrayList[N+1];
		 int start = 0;
		 
		 for(int i=1;i<=N;i++) {
			 list[i] = new ArrayList<>();
		 }
		 
		 for(int i=0;i<M;i++) {
			 st = new StringTokenizer(br.readLine());
			 int a = Integer.parseInt( st.nextToken());
			 int b = Integer.parseInt(st.nextToken());
			 int c = Integer.parseInt(st.nextToken());
			 list[a].add(new Edge(b,c));
			 list[b].add(new Edge(a,c));
			 if(i==0) {
				 start = a;
			 }
		 }
		 
		 prim(start);
		 bw.write(ans_dist-max+"");
		 bw.flush();
		 bw.close();
	}
	
	private static void prim(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			Edge node = pq.poll();
			if(visited[node.w]) {
				continue;
			}
			visited[node.w]=true;
//			System.out.print("??");
			ans_dist+=node.cost;
			if(node.cost>max) {
				max = node.cost;
			}
			for(Edge e :list[node.w]) {
				if(!visited[e.w]) {
					pq.offer(e);
				}
			}
		}
	}
	
	
	

}