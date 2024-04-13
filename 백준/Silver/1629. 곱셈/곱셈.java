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
	static int A,B,C;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		bw.write(mod(B)+"");
		bw.flush();
		bw.close();
	}
	// 시간 초과...
//	private static int mod(int A, int B, int C) {
//		if(B==1) return A;
//		if(B%2==0) {
//			return mod(A,B/2,C)*mod(A,B/2,C)%C;
//		} else {
//			return mod(A,B/2,C)*mod(A,B/2,C)*A%C;
//		}
//	}

	private static long mod(int num) {
		int halfB = num/2;
		if(num==1) {
			return A%C;
		}
		long tmp = mod(halfB);
		if(num%2==1) {
			return ((tmp*tmp)%C*(A%C))%C;
		} else {
			return (tmp*tmp)%C;
		}
	}
}