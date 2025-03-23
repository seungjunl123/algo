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
	static int answer = -1;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		Stairs = new int[F+1];
		Arrays.fill(Stairs, -1);
		
		
		BFS(S);
		
		if(answer == -1)
		{
			System.out.println("use the stairs");
		}
		else
		{
			System.out.println(answer);
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
					answer = count;
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
            count++;
		}
		
		return ;
	}



}