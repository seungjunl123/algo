import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	// 변수 N 선언
	static int N;
	// N만큼의 값을 저장할 char 배열 선언
	static char[] arr;
	// 연산값을 저장할 sum, max 변수 선언
	static int max = Integer.MIN_VALUE;
	
	static BufferedReader br;
	static BufferedWriter bw;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 변수 생성
		N = Integer.parseInt(br.readLine());
		arr = new char[N];
		String eq = br.readLine();
		for(int i=0;i<N;i++) {
			arr[i] = eq.charAt(i);
		}
		// BFS 실행
		DFS(1, arr[0]-'0');
		
		// BFS 실행 후 저장된 최대값을 출력
		bw.write(max+"");
		bw.flush();
		bw.close();
	}
	
	static void DFS(int idx, int sum) {
		if(idx==N) {
			// idx가 마지막까지 오면 지금까지의 합과 최댓값과 비교
			if(sum>max) {
				// 크면 max에 저장
				max = sum;
			}
			return;
		}
		
		// 연산자의 괄호가 있는 경우와 없는 경우에 따라 계산
		// 괄호가 없으면 idx부터 i+1번해서 중간 연산자로 계산 후 값 저장
		if(idx+2<=N) {
			DFS(idx+2,solv1(sum, idx));			
		}
		// 괄호가 있으면 idx에서 i+3번째로 나오는 연산자로 먼저 계산 후 앞에 계산 후 저장
		// 저장했으면 BFS 추가 실행
		if(idx+4<=N) {
			DFS(idx+4,solv2(sum, idx));			
		}
	}
	
	static int solv1(int sum, int idx) {
		if(arr[idx] == '+') {
			sum += arr[idx+1]-'0';
			return sum;
		} else if (arr[idx] == '-') {
			sum -= arr[idx+1] -'0';
			return sum;
		} else {
			sum *= arr[idx+1] -'0';
			return sum;
		}
	}
	
	static int solv2(int sum, int idx) {
		if(arr[idx+2] == '+') {
			int a =(arr[idx+1]-'0')+(arr[idx+3]-'0');
			if(arr[idx] == '+') {
				sum += a;
				return sum;
			} else if (arr[idx] == '-') {
				sum -= a;
				return sum;
			} else {
				sum *= a;
				return sum;
			}
		} else if (arr[idx+2] == '-') {
			int a =(arr[idx+1]-'0')-(arr[idx+3]-'0');
			if(arr[idx] == '+') {
				sum += a;
				return sum;
			} else if (arr[idx] == '-') {
				sum -= a;
				return sum;
			} else {
				sum *= a;
				return sum;
			}
		} else {
			int a =(arr[idx+1]-'0')*(arr[idx+3]-'0');
			if(arr[idx] == '+') {
				sum += a;
				return sum;
			} else if (arr[idx] == '-') {
				sum -= a;
				return sum;
			} else {
				sum *= a;
				return sum;
			}
		}
	}
}
