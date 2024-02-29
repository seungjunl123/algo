import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	// 규영이가 가지고 있는 카드
	static int[] kycard;
	static boolean[] ky;
	static int[] iycard;
	// 게임당 규영이 점수
	static int ksum;
	// 게임당 인영이 점수
	static int isum;
	// 규영이가 이기는 수
	static int[] numbers;
	static boolean[] visited;
	
	// 지는 수는 9!에서 count 빼주면 된다
	static int count;
	static int iycount = 362880;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			kycard = new int[9];
			iycard = new int[9]; 
			ky = new boolean[19];
			// 규영이 카드 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<9;i++) {
				kycard[i]= Integer.parseInt(st.nextToken());
				ky[kycard[i]]= true;
				
			}
			// 인영이 카드 입력
			wp : for(int i=0;i<9;i++) {
				for(int j=1;j<19;j++) {
					if(!ky[j]) {
						iycard[i]= j;
						ky[j]= true;
						continue wp;
					}
				}
			}

			numbers = new int[9];
			visited = new boolean[9];

			count = 0;
			duel(0);

			bw.write("#" + t + " " + count+" "+(iycount-count) + "\n");
		}
		bw.flush();
		bw.close();
	}

	static void duel(int idx) {
		if(idx==9) {
//			System.out.println(Arrays.toString(numbers));
			ksum = 0;
			isum = 0;
			for(int i=0;i<9;i++) {
				if(kycard[i]>iycard[numbers[i]]) {
					ksum += kycard[i]+iycard[numbers[i]];
				} else {
					isum += kycard[i]+iycard[numbers[i]];
				}
			}
			if(ksum>isum) {
				count++;
			}
			return;
		}
		for(int i=0;i<9;i++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[idx] = i;
				duel(idx+1);
				visited[i] = false;
						
			}
		}
	}
}
