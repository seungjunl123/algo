import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	public static class Node implements Comparable<Node>{
		int start;
		int end;
		
		public Node(int start,int end){
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int count = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(start, end));
			
//			System.out.println(pq.peek().start);
		}
		
		int nul = -1;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
//			System.out.println(node.start +" " +node.end);
			if(node.start>=nul) {
				int plus = (node.end-node.start)%L == 0 ? 
						(node.end-node.start)/L : ((node.end-node.start)/L)+1;
				count += plus;
				
//				System.out.println("start>nul, count: " + count +", end: "+ node.end);
				nul = node.start + plus*L;
//				System.out.println("nul "+nul);
			} else {
				int start = nul;
				if(node.end > start) {
					
					int plus = (node.end-start)%L == 0 ? 
							(node.end-start)/L : ((node.end-start)/L)+1;
					count += plus;
//				System.out.println("start<nul, count: " + count +", end: "+ node.end);
					nul = start + (plus)*L;
				}
//				System.out.println(start);
				
			}
		}
		
		System.out.println(count);

	}
}