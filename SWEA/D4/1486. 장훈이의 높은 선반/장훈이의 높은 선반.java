import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	static int T,N,B;
	static int[] arr;
	static int ans;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 재귀를 통한 반복 찾기
			makeTower(0, 0);
			
			bw.write("#"+t+" "+(ans-B)+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static void makeTower(int idx, int sum) {
		if(sum>=B) {
			if(sum<ans) ans = sum;
			return;
		}
		// 반복의 시작은 idx부터
		// 이미 앞에 애부터 탐색을 하므로 볼 필요 없음
		for(int i=idx;i<N;i++) {
			makeTower(i+1,sum+arr[i]);
		}
		
	}
	
	
}