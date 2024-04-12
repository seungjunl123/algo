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
	static int N,S;
	static int[] arr;
	static int ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		ans = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			int sum = 0;
			int count = 0;
			for(int j = i;j<N;j++) {
				sum += arr[j];
				count++;
				if(count>ans) break;
				if(sum>=S) {
					ans = count;
					break;
				}
			}
			
		}
        
        if(ans==Integer.MAX_VALUE) ans = 0;
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
}