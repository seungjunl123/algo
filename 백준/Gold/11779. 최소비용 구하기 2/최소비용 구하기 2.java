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
	int v;
	int w;
	 
	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return this.w-o.w;
	}
	
}

public class Main {
	static int N,M;
	static int start, end;
	static int[] dist;
	static boolean[] checked;
	static List<Integer>[] way;
	static List<Edge>[] arr;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];
		Arrays.fill(dist, 100_000_000);
		
		checked = new boolean[N+1];
		way = new ArrayList[N+1];
		arr = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			arr[i] = new ArrayList<>();
			way[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			arr[s].add(new Edge(e,w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		bw.write(dist[end]+"\n"+way[end].size()+"\n");
		for(int i=0;i<way[end].size();i++) {
			bw.write(way[end].get(i)+" ");
		}
		bw.flush();
		bw.close();
		
	}
	private static void dijkstra(int s) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.add(new Edge(s,0));
		dist[s] = 0;
		way[s].add(s);
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int cur = edge.v;
			
			if(checked[cur]) continue;
			
			checked[cur] = true;
			
			for(int i=0;i<arr[cur].size();i++) {
				if(dist[arr[cur].get(i).v] > dist[cur]+arr[cur].get(i).w) {
					dist[arr[cur].get(i).v] = dist[cur]+arr[cur].get(i).w ;
					pq.add(new Edge(arr[cur].get(i).v,dist[arr[cur].get(i).v]));
					way[arr[cur].get(i).v].clear();
					for(int j :way[cur]) {
						way[arr[cur].get(i).v].add(j);
					}
					way[arr[cur].get(i).v].add(arr[cur].get(i).v);
					//System.out.println(arr[cur].get(i).v+" "+way[arr[cur].get(i).v]);
				}
			}
			
		}
	}
}
