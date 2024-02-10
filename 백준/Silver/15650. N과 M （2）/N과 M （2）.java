import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int[] numbers;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		visited = new int[N+1];
		
		selectNum(1,0);
	}
	
	public static void selectNum(int index,int count) {
		if(count==M) {
			for(int i=0;i<M;i++) {
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=index ;i<=N;i++) {
			if(visited[i] == 0) {
				numbers[count] = i;
				visited[i] = 1;
				selectNum(i,count+1);
				visited[i] = 0;
			}
		}
	}
}
