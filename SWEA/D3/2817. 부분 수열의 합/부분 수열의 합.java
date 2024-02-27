import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, K;
	// 버거의 맛과 칼로리를 저장할 배열 생성
	static int[] atom;
	// 칼로리를 만족할 때 맛의 max를 저장할 변수 생성
	static int count;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1;t<=T;t++) {
			// 케이스 별 정보 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			atom= new int[N];
			count = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				// 수열에 대한 정보 입력
				atom[i] = Integer.parseInt(st.nextToken());
			}
			
			selectBur(0,0,0);
			bw.write("#"+t+" "+count+"\n");
		}
		bw.flush();
		bw.close();
	}
	// 재료가 1가지만 있어도 찾을 수 있다
	private static void selectBur(int idx, int sidx, int sum) {
		if(sum == K) {
			count++;
		}
		if(sidx >= N) {
			return;
		}
		for(int i =idx;i<N;i++) {
			selectBur(i+1,sidx+1,sum+atom[i]);
		}
	}
}
