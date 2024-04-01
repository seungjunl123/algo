import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static int V,E;
	static List<Integer>[] adj;
	static int[] degree;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[V+1];
		for(int i=0;i<=V;i++) {
			adj[i] = new ArrayList<>();
		}
		degree = new int[V+1];
		
		for(int i=1;i<=E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			degree[b]++;
		}
		
		PriorityQueue<Integer> q = new PriorityQueue();
		
		for(int i=1;i<=V;i++) {
			if(degree[i]==0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			bw.write(node+" ");
			for(int i=0;i<adj[node].size();i++) {
				degree[adj[node].get(i)]--;
					
				if(degree[adj[node].get(i)]==0) {
					q.add(adj[node].get(i));
				}
			}
		}
		
		bw.flush();
		bw.close();
			
			
	}
}