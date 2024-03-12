import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[] A;
	static int[] num;
	
	static int max;
	static int ans;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		num = new int[1001];
		max = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 풀이과정
		// 반복문으로 A배열 전체 탐색
		// A배열의 i 값이 있다면 그 값부터 0까지 다 돌려
		// 0번 인덱스까지 돌리면서 값이 0이 아니면 그 값+1을 저장
		// 0번 인덱스까지 0이면 1 저장
		// 쭉 다돌고 나서 A 배열 다 돌고 최댓값을 ans로 저장하면 끝
		for(int i=0;i<N;i++) {
			max = 0;

			for(int j=A[i]-1;j>=0;j--) {
				if(num[j]>max) {
					max = num[j];
				}
			}
			num[A[i]] = max+1;
		}
		
//		for(int i=0;i<55;i++) {
//			System.out.print(num[i]+" ");
//		}
		for(int i=1;i<1001;i++) {
			if(num[i]>max) {
				max = num[i];
			}
		}
		bw.write(max+"");
		bw.flush();
		bw.close();
	}

}