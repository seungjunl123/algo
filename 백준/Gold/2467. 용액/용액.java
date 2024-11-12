import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		// 정렬된 상태
		// 가장 왼쪽(최저값)과 가장 오른쪽(최고값)에서 시작
		// 두 값을 합쳐서 0이면 그 값을 출력
		// 그렇지 않고 0보다 작으면 값을 키우기 위해 최저값에서 한칸 이동
		// 하거나 0보다 크면 값을 줄이기 위해 최고점에서 한칸 이동
		// 이러면서 두 값의 합의 절대값이 가장 작은 값을 저장해놨다가 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		int left = 0;
		int right = N-1;
		
		int answerP = -1;
		int answerN = -1;
		
		while(arr[left]<arr[right]) {
			if(arr[left]+arr[right]!=0) {
				if(Math.abs(arr[left]+arr[right])<min) {
					min = Math.abs(arr[left]+arr[right]);
					answerN = arr[left];
					answerP = arr[right];
				}
				
				if(arr[left]+arr[right]<0) {
					left++;
				} else {
					right--;
				}
			} else {				
				answerN = arr[left];
				answerP = arr[right];
				break;
			}
		}
		
		System.out.println(answerN+" "+answerP);
		
	}
}
