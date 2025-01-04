import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int T,N;
	static String p, str;
	static boolean isReverse, isError;

	// deque로 해결 될 거 같은데
	// R이 없다면 D가 있을 때 정상적으로 빼고
	// R이 있다면 뒤에서 빼고 마지막에도 R대로 빼면 되는거 아닌가
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			isReverse = false;
			isError = false;
			p = br.readLine();
			int plen = p.length();
			N = Integer.parseInt(br.readLine());
			str = br.readLine();
			int strlen =str.length();
			String[] onlyNum = str.substring(1, strlen-1).split(",");
			
			Deque<Integer> dq = new ArrayDeque<>();
			for(int i=0;i<N;i++) {
				dq.addLast(Integer.parseInt(onlyNum[i]));
			}
			
			for(int i=0;i<plen;i++) {
				if(p.charAt(i)=='R') isReverse = !isReverse;
				else {
					if(dq.isEmpty()) {
						isError =true;
						break;
					}
					if(!isReverse) {
						dq.pollFirst();
					} else {
						dq.pollLast();
					}
				}
				
			}
			
			if(isError) {
				System.out.println("error");
			} else {
				System.out.print("[");
				if(isReverse) {
					while(dq.size()>1) {
						System.out.print(dq.pollLast()+",");
					}
					if(dq.size()!=0) System.out.print(dq.pollLast());
				} else {
					while(dq.size()>1) {
						System.out.print(dq.pollFirst()+",");
					}
					if(dq.size()!=0) System.out.print(dq.pollFirst());
				}
				System.out.println("]");
			}
			
		}
	}

}