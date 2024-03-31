import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main{
	static int N,T,P;
	static int[][] arr;
	static int ans;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		ans = 0;
		findRes(0,0);
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	private static void findRes(int day, int count) {
		if(day>N) return;
		if(count>ans) {
			//System.out.println(day+" "+count);
			ans = count;
		}
		for(int i=day;i<N;i++) {
			findRes(day+(i-day)+arr[i][0],count+arr[i][1]);
		}
		
	}
}