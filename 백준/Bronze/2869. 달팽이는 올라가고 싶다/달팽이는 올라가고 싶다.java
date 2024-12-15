import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int A,B,V;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		if(A>=V) {
			System.out.println('1');
		} else {
			if ((A-B) >= (V-A)) {
				System.out.println('2');
			}
			else {
				
				if ((V-A)%(A-B)==0) {
					System.out.println(((V-A)/(A-B))+1);
				}
				
				else {
					
					System.out.println(((V-A)/(A-B))+2);
				}
			}
		}
	}
}