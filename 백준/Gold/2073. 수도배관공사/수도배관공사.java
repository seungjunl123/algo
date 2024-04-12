import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


class Main {
	static int D, P;
	static int[] L, C;
	static int[] ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		L = new int[P];
		C = new int[P];
		ans = new int[D+1];
		
		
		for(int i=0;i<P;i++) {
			st = new StringTokenizer(br.readLine());
			L[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
		}
		
	
		ans[0]= Integer.MAX_VALUE;
		for(int i=0;i<P;i++) {
			for(int j=D;j>=L[i];j--) {
				if(ans[j]<Math.min(C[i], ans[j-L[i]])) {
					ans[j]=Math.min(C[i], ans[j-L[i]]);
				}
			}
		}
		bw.write(ans[D]+"");
		bw.flush();
		bw.close();
	}
}