import java.io.*;
import java.util.*;

class Node{
	int V;
	int W;
	
	Node(int V, int W){
		this.V = V;
		this.W = W;
	}
}

class Main {
	static int N, M;
	static List<Node>[] map;
	static int[][] dist;
	static final int INF = 987654321;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	N = Integer.parseInt(br.readLine());
    	M = Integer.parseInt(br.readLine());
    	
    	dist = new int[N+1][N+1];
    	map = new ArrayList[N+1];
    	
    	for(int i=1;i<=N;i++) {
    		map[i] = new ArrayList<>();
    	}
    	
    	for(int i=1;i<=N;i++) {
    		for(int j=1;j<=N;j++) {
    			if(i==j) dist[i][j] = 0;
    			else dist[i][j] =INF;
    		}
    	}
    	
    	for(int i=0;i<M;i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		int cost  = Integer.parseInt(st.nextToken());
    		
    		if(dist[start][end]>cost) dist[start][end] = cost;
    		
    		map[start].add(new Node(end, cost));
    	}
    	
    	for(int mid=1;mid<=N;mid++) {
    		for(int start=1;start<=N;start++) {
    			for(int end =1; end<=N;end++) {
    				if(dist[start][end]>dist[start][mid]+dist[mid][end]) {
    					dist[start][end]=dist[start][mid]+dist[mid][end];
    				}
    			}
    		}
    	}
    	
    	
    	for(int i=1;i<=N;i++) {
    		for(int j=1;j<=N;j++) {
    			if(dist[i][j] == INF) {
    				System.out.print("0" +" ");
    				
    			} else {    				
    				System.out.print(dist[i][j] +" ");
    			}
    		}
    		System.out.println();
    	}
    }
}
