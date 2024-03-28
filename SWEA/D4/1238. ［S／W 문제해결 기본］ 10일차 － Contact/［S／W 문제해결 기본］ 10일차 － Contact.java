import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N,start;
	static List<Integer>[] list;
	static boolean[] visited;
	static int ans;
	static boolean flag;
	
	static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		for(int t=1;t<=10;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			ans = 0;
			
			visited = new boolean[101];
			list = new ArrayList[101];
			for(int i=1;i<=100;i++) {
				list[i] = new ArrayList<>();
			}
			
			// N의 길이인데 from to 쌍이니까
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N/2;i++) {
				int in = Integer.parseInt(st.nextToken());
				int out = Integer.parseInt(st.nextToken());
				list[in].add(out);
			}
//			System.out.println("start " +start);
			BFS(start);
			bw.write("#"+t+" "+ans+"\n");
			
			
		}
		bw.flush();
		bw.close();
		
	}
	// BFS하면서 가장 노드를 counting될때마다 가장 큰 값을 넣어주나?
	private static void BFS(int idx) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(idx);
		while(!q.isEmpty()) {
			int size = q.size();
			int max = 0;
			
			for(int i=0;i<size;i++) {
				
				int node = q.poll();
				
				for(int j : list[node]) {
					if(!visited[j]) {
						visited[j] = true;
						flag =true;
//						System.out.println(j);
						q.offer(j);
						if(max<j) max = j;
					}
				}
			}
			// 깊이 추가될 때마다 max값을 ans로 저장하면?
			if(flag) {				
				ans = max;
			}
			flag = false;
//			System.out.println("ans =" +ans);
		}
	}
}
            