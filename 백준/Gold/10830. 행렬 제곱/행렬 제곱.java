import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Long, int[][]> maps;
	static int[][] originMap;
	static int N;
	static long B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		originMap = new int[N][N];
		maps = new HashMap<>();
		for(int i=0;i<N;i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++)
			{
				originMap[i][j] = Integer.parseInt(st.nextToken())%1000;
			}
		}
		
		maps.put(1l, originMap);
		int[][] answer =conquer(B);
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				System.out.print(answer[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	private static int[][] conquer(long num) {
		
		if(maps.containsKey(num)) return maps.get(num); 
		else return sub(num/2l, num%2l==1l ? num/2l+1l : num/2);
		
	}

	private static int[][] sub(long l, long m) {
		int[][] tmp1 = conquer(l);
		int[][] tmp2 = conquer(m);
		int[][] res = new int[N][N];
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				int sum = 0;
				for(int k=0;k<N;k++)
				{
					sum += tmp1[i][k] * tmp2[k][j];
				}
				res[i][j] = sum%1000;
			}
		}
		
		maps.put(l+m, res);
		return res;
		
	}

}