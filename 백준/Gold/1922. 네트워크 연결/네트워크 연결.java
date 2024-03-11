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

	
	public Edge(int w, int cost) {
		this.w = w;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return cost - o.cost;
	}
	
}

class Main {
	static int N,M;
	static boolean[] visited;
	static int ans_dist;
	static List<Edge>[] list;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		list = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			list[i] = new ArrayList<>();
		}
		ans_dist = 0;
		int start = 0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
//			System.out.println(i+" "+a+" "+b+" "+c);
			if(i==0) {
				start = a;
			}
			list[a].add(new Edge(b,c));
			list[b].add(new Edge(a,c));
		}
		// prim 돌려보자
		prim(start);
		
		bw.write(ans_dist+"");
		bw.flush();
		bw.close();
		
	}
	private static void prim(int idx) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(idx,0));
		
		while(!pq.isEmpty()) {
			Edge node = pq.poll();
			int v = node.w;
			int cost = node.cost;
			if(visited[v]) continue;
			visited[v] = true;
			ans_dist += cost;
			for(int i=0;i<list[v].size();i++) {
				if(!visited[list[v].get(i).w]) {
//					System.out.println(node.w+" "+node.cost+" "+list[v].get(i).w);
					pq.offer(list[v].get(i));
				}
			}
			
		}
		
	}
}