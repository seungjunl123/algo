import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int F,S,G,U,D;
	static int[] Stairs;
	static int answer =0;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st ;
		while(true)
		{
		st  = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		if(A!=0 ||B!=0) 
			System.out.println(A+B);
		else break;
		}
	}

	private static void BFS(int idx) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<>();
		
		q.add(idx);
		int count = 0;
		Stairs[idx] = 1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i=0;i<size;i++)
			{
				int curNode = q.poll();
				
				if(curNode == G) {
					answer = curNode;
					return ;
				}
				if(curNode+U <=F && Stairs[curNode+U]==-1)
				{
					Stairs[curNode+U] = 1;
					q.add(curNode+U);
				}
				if(curNode-D >00 && Stairs[curNode-D]==-1)
				{
					Stairs[curNode-D] = 1;
					q.add(curNode-D);
				}
			}
		}
		
		return ;
	}



}