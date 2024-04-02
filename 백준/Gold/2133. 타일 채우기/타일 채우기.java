import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class Main {
	static int N;
	static int ans;
	static List<Integer> numbers;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		ans = 0;
		numbers = new ArrayList<>();
		
		if(N%2==1) ans = 0;
		else {
			findingN(0);
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	private static void findingN(int sum) {
		if(sum>N/2) return;
		if(sum==N/2) {
			int arr = 1;
//			for(int i=0;i<numbers.size();i++) {
//				System.out.print(numbers.get(i)+" ");	
//			}
			for(int i=0;i<numbers.size();i++) {
				if(numbers.get(i)==1) {
					arr*=3;
				} else arr*=2;
			}
			ans += arr;
//			System.out.println();
			return;
		} 
		for(int i=1;i<=N/2;i++) {
			numbers.add(i);
			findingN(sum+i);
			numbers.remove(numbers.size()-1);
		}
	}
}