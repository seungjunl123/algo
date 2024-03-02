import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	// 규칙에 따라 확인하는 변수 필요
	// 색, 숫자에 대한 배열 생성
	static int[] col = new int[4];
	static int[] num = new int[10];
	// 같은 숫자가 몇개 나왔는지 확인하는 카운트 생성?
	static int[] con = new int[5];
	static int max = Integer.MIN_VALUE;
	
	// 숫자가 연속으로 나오는지 확인하는 카운트 변수 생성
	static int count = 1;
	static int ans = 0;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i = 0; i<5;i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			char n = st.nextToken().charAt(0);
			
			// 색깔과 숫자에 대한 배열 생성
			// R,B,Y,W 순으로 입력
			if(c=='R') {
				col[0]++;
			} else if(c=='B') {
				col[1]++;
			} else if(c=='Y') {
				col[2]++;
			} else {
				col[3]++;
			}
			// 숫자 입력
			num[n-'0']++;
		}
		for(int i=1;i<=9;i++) {
			if(num[i]>0) {
				max = i;
				// 숫자에 대한 순회를 하며 num에 0 이상인 인덱스가 연속으로 나오면 count
				count++;
				if(num[i-1]==0) {
					count--;  
				}
			}
		}
		//System.out.print(count);
		// 색에 대한 규칙 먼저, 그 다음 연속에 대한 규칙
		for(int i=0;i<4;i++) {
			// 색은 같은 색이 5개 인지만 확인
			if(col[i]==5) {
				// 순서가 연속이면
				if(count==5) {
					ans = 900 + max;
					break;
				} else {
					ans = 600 + max;
				}
			} else if(count == 5) {
				ans = 500 + max;
			}
		}
		
		// 마지막으로 같은 숫자에 대한 규칙을 적용한다
		cl : for(int i=1;i<=9;i++) {
			if(num[i]==4) {
				ans = 800 + i;
				break cl;
			} else if(num[i]==3){
				for(int j=1;j<=9;j++) {
					if(num[j] == 2) {
						ans = 700+j+(10*i);
						break cl;
					}
				}
				ans = 400+i;
				break;
			} else if(num[i]==2) {
				for(int j=i+1;j<=9;j++) {
					if(num[j]==2) {
						ans = 300 + i + (10*j);
						break cl;
					}
				}
				ans =  200 + i;
			}
		}
		// 마지막에 아무런 규칙이 적용되지 않았을 경우 ans는 초기값 0
		if(ans == 0) {
			ans = 100 + max;
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}

}