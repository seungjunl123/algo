import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static long start, end;
	static int count;
	static boolean[] visited;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		visited= new boolean[1000000001];

		int ans = BFS();
		if(ans==0) {
			bw.write(-1+"");
		} else {			
			bw.write((ans+1)+"");
		}
		bw.flush();
		bw.close();
	}
	private static int BFS() {
		Queue<Long> q = new LinkedList<>();
		
		q.offer(start);
		visited[(int)start] = true;
		 while(!q.isEmpty()) {
			 int size = q.size();
			 for(int i=0;i<size;i++) {
				 long node = q.poll();
				 if(node==end) {
					 return count;
				 }
				 if(((node*10)+1)<=end&&!visited[(int)(node*10)+1]) {
					 visited[(int)(node*10)+1]=true;
//					 System.out.println(node*10+1);
					 q.offer((node*10)+1);
				 }
				 if((node*2)<=end&&!visited[(int)node*2]) {
					 visited[(int)node*2] =true;
//					 System.out.println(node*2);
					 q.offer(node*2);
				 }

			 }
			 count++;
		 }
		return 0;
	}	
}
