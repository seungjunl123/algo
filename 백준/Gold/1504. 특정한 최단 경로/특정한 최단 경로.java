import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int w;
	int cost;
	
	public Edge(int w, int cost) {
		this.w =w;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return cost - o.cost;
	}
}

public class Main {
	static int N;
	static long E;
	static int v1,v2;
	static long[] dist;
	// v1,v2 중 큰값 작은 값을 저장할 변수
	static long ans1, ans2;
	static List<Edge>[] list;
	static int INF = 200000000;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		
		// 풀이과정
		// 1. 1번에서 v1,v2로 가는 비용 중 작은 비용 선정(다익스트라)
		// 2. v1에서 v2로 가는 최소 비용 선정(다익스트라, 양방향)
		// 3. 1번에서 v1,v2로 가는 큰 비용을 가진 정점에서 N번으로 가는 최소 비용
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];

		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Edge(b,c));
			list[b].add(new Edge(a,c));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		Dijkstra(1);
//		System.out.print(Arrays.toString(dist));

		ans1 += dist[v1];
		ans2 += dist[v2];
//		System.out.println("small"+dist[small]);
		
		// 두가지 경우가 있다
		// 1. 1에서 v1을 거치고, v1에서 v2를 거치고, v2에서 N에 도달
		// 2. 1에서 v2을 거치고, v2에서 v1를 거치고, v1에서 N에 도달
		
		// v1과 v2에서 N에 도달하는 가장 짧은 거리를 위해 v2과 v1에서 다익스트라를 추가 수행
		// v1과 v2까지의 거리는 사실 같이 나온다(양방향이니까)
		Dijkstra(v1);
		
		ans1 += dist[v2];
		ans2 += dist[N];
//		System.out.println("big"+dist[big]);
		
		
		Dijkstra(v2);
		
		ans1 += dist[N];
		ans2 += dist[v1];
//		System.out.println("마지막 거리"+dist[N]);
		long ans = Math.min(ans1, ans2);
		
		if(ans>=INF) {
			bw.write(-1+"");
		} else {
			bw.write(ans+"");
		}
		bw.flush();
		bw.close();
		
	}
	private static void Dijkstra(int idx) {
		dist = new long[N+1];
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[idx] = 0;
		pq.offer(new Edge(idx,0));
	
		while(!pq.isEmpty()) {
			Edge node = pq.poll();
			
			for(Edge e : list[node.w]) {
//				System.out.println("node " + node.w + " e" + e.w +" "+e.cost);
				if(dist[e.w]>dist[node.w]+e.cost){
					dist[e.w]=dist[node.w]+e.cost;
					pq.offer(e);
				}
			}
		}
		
	}
}
