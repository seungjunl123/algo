import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static int[] heads;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		heads = new int[N];
		for(int i=0;i<N;i++)
		{
			heads[i] = i;
		}
		
		for(int i=0;i<M;i++)
		{
			st = new StringTokenizer(br.readLine());
			int intA = Integer.parseInt(st.nextToken());
			int intB = Integer.parseInt(st.nextToken());
			
			int parentA = head(intA);
			int parentB = head(intB);
			
			
			if(parentA == parentB)
			{
				answer = i+1;
				break;
			}
			else 
			{
				heads[parentA] = heads[parentB];
			}
		}
		
		System.out.println(answer);
	}
	private static int head(int min) {
		if(heads[min] == min)
			return min;
		else
			return heads[min] = head(heads[min]);
	}


}