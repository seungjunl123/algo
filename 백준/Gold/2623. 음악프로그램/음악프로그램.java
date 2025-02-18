import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static ArrayList<Integer>[] list;
	static int[] indegree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for(int i=1;i<=N;i++)
		{
			list[i] = new ArrayList<>();
		}
		indegree = new int[N+1];
		
		for(int i=0;i<M;i++)
		{
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			if(count == 0) continue;
			int start = Integer.parseInt(st.nextToken());
			for(int j=0;j<count-1;j++)
			{
				int end = Integer.parseInt(st.nextToken());
				indegree[end]++;
				list[start].add(end);
				start = end;
			}
		}
		
		ArrayList<Integer> answer = topologySort();
		if(answer.size() == N)
		{
			for(int i=0;i<answer.size();i++)
			{
				bw.write(answer.get(i)+"\n");
			}
		}
		else 
		{
			bw.write("0");
		}
		bw.flush();
		bw.close();
		
		
		
	}

	private static ArrayList<Integer> topologySort() {
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1;i<=N;i++)
		{
			if(indegree[i]==0) queue.add(i);
		}
		
		ArrayList<Integer> tmp = new ArrayList<>();
		while(!queue.isEmpty())
		{
			int curNode = queue.poll();
			tmp.add(curNode);
			
			for(int i=0;i<list[curNode].size();i++)
			{
				int next = list[curNode].get(i);
				if(--indegree[next]==0) queue.add(next);
			}
		}
		return tmp;
	}

}