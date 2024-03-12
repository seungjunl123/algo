import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] numbers;
	static int[] visited;
	static int[] A;
	static int[] check;
	
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		check = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken()); 
		}
		
		Arrays.sort(A);;
		
		numbers = new int[M];
		visited = new int[N + 1];

		sequence(0);
	}

	public static void sequence( int count) {
		if (count == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(numbers[i]+ " ");
			}
			System.out.println();
			return;
		}
		check[count] = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i] == 0 && check[count] != A[i] ) {
				numbers[count] = A[i];
				check[count] = A[i] ;
				visited[i] = 1;
				sequence(count + 1);
				visited[i] = 0;
			}

		}
	}
}
