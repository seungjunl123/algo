import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// dfs
	
	static int TC,n;
	static int[] pickedStudent;
	static boolean[] isDone;
	static boolean[] isChecked;
	static int answer;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		for(int t=0;t<TC;t++) {
			n = Integer.parseInt(br.readLine());
			answer = n;
			pickedStudent = new int[n+1];
			isDone = new boolean[n+1];
			isChecked = new boolean[n+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=n;i++) {
				pickedStudent[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1;i<=n;i++) {
				if(isDone[i]) continue;
				findTeam(i);
			}
			
			bw.write(answer+"\n");
		}
		bw.flush();
		bw.close();
		
	}
	private static void findTeam(int idx) {
		if(isDone[idx]) {
			return;
		}
		
		if(isChecked[idx]) {
			isDone[idx] = true;
			answer--;
		}
		
		isChecked[idx] = true;
		findTeam(pickedStudent[idx]);
		isDone[idx] = true;
		isChecked[idx] = false;
		
	}
}