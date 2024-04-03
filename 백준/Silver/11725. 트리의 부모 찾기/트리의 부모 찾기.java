import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	static int N;
	static List<Integer>[] list;
	static int[] parent;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			list[A].add(B);
			list[B].add(A);
		}
		parent[1] = 1;
		findChild(1);
		
		for(int i=2;i<=N;i++) {
			bw.write(parent[i]+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void findChild(int idx) {

		for(int i=0;i<list[idx].size();i++) {
			if(parent[list[idx].get(i)]==0) {
				parent[list[idx].get(i)] = idx;
				findChild(list[idx].get(i));
			}
		}
		
	}


}