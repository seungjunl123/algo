import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int X = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int answer = 0;
		int left = 0;
		int right = N-1;
		
		while(left<right) {
			int sum = arr[left]+arr[right];
			if(sum == X) {
//				System.out.println("조하 left"+left+" right "+right);
				answer++;
				left++;
			} else if(sum>X) {
				right--;
			} else {
				left++;
			}
			
		}
		System.out.println(answer);
	}
}