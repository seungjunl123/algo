import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	static long N, P, Q;
	static long count;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static HashMap<Long,Long> A = new HashMap<Long,Long>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		
		A.put(0l, 1l);
		if(N!=0) {
			func1(N);
		}
		
		bw.write(A.get(N) + "");
		bw.flush();
		bw.close();
	}

	private static void func1(long idx) {
		// An을 구성하는 Ai/p와 A/iq를 구한다
		// memoization하는 hashmap에서 위 두 값이 없다면 재귀 실행
		// i/p를 완료하면 i/q도 똑같이 실행
		if(A.get(idx/P)==null)	func1(idx/P);
		if(A.get(idx/Q)==null) func1(idx/Q);
		
		
		if(A.get(idx/P)!=null&&A.get(idx/Q)!=null) {
			A.put(idx, A.get(idx/P)+A.get(idx/Q));
			return;
		}
		
	}
}