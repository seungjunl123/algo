import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {
	static int T,N,K;
	static String[] arr;
	static PriorityQueue<Integer> pq;
	static Set<Integer> s;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			pq = new PriorityQueue<>(Collections.reverseOrder());
			s = new HashSet();
			
			String line = br.readLine();
			arr = line.split("");
			//System.out.print(arr[1]);
			
			for(int i=0;i<N/4;i++) {
				rotate(i);
			}
			for(int idx : s) {
				//System.out.println(idx);
				pq.add(idx);
			}
			//System.out.println(t+" "+pq.size());
			for(int i=1;i<K;i++) {
				//System.out.println(t +" pq 폴" +pq.peek());
				pq.poll();
			}
			bw.write("#"+t+" "+pq.peek()+"\n");
			
		}
		bw.flush();
		bw.close();
	}
	private static void rotate(int start) {
		List<Integer> list = new ArrayList<>();
		for(int i=start;i<N+start;i++) {
			list.add(Integer.parseInt(arr[i%N],16));
			
			//System.out.println(list.size()+"of"+N/4);
			if(list.size()==N/4) {
				int sum  = 0;
				int size = list.size();
				for(int j=0;j<size;j++) {
					int a = list.get(size-1-j)*(int)Math.pow(16,j);
					
					sum+=a;
				}
				list.clear();
				//System.out.println("사이즈" +list.size()+" sum"+sum);
				s.add(sum);
			}
		}
	}
}