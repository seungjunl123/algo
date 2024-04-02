import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
	static int T,N;
	static int[] op;
	static int[] number;

	static int[] select;
	static long min,max;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N= Integer.parseInt(br.readLine());
			op = new int[4];
			number = new int[N];
			select = new int[N-1];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st= new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			perm(0);
			
			bw.write("#"+t+" "+(max-min)+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void perm(int idx) {
		if(idx == N-1) {
			long sum2 = operation(select);
			if(max<sum2) max = sum2;
			if(min>sum2) min = sum2;
		}
		for(int i=0;i<4;i++) {
			if(op[i]!=0) {
				op[i]--;
				select[idx]= i;
				perm(idx+1);
				op[i]++;
			}
		}
		
	}
	
	private static long operation(int[] selects) {
		int sum = number[0];
		for(int i=1;i<N;i++) {
			switch(selects[i-1]) {
			case 0 : sum += number[i];
					continue;
			case 1 : sum -= number[i];
					continue;
			case 2 : sum *= number[i];
					continue;
			case 3 : sum /= number[i];
					continue;
			}
		}
		
		return sum;
	}
	
	
	
}