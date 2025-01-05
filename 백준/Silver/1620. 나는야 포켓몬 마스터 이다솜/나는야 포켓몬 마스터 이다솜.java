import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	// 숫자일 때 찾을 거
	static String[] pokemon;
	// 알파벳일 때 찾을 거
	static HashMap<String, Integer> pkm;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
			
		pokemon = new String[N+1];
		pkm = new HashMap<>();
		for(int i=1;i<=N;i++) {
			pokemon[i] = br.readLine();
			pkm.put(pokemon[i],i);
		}
		

		for(int i=0;i<M;i++) {
			String str = br.readLine();
			if((str.charAt(0)-0)>60) {
				//영어
				sb.append(pkm.get(str)+"\n");
			} else {
				int idx = Integer.parseInt(str);
				sb.append(pokemon[idx]+"\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}