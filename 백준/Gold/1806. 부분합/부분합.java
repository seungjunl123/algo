import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N,S;
	static int[] nums;
	static int min = Integer.MAX_VALUE;
	static int exitedSum;
	static int[] prefixSum;
	// S가 10억이 아니므로 int로 시작 가능
	
	// 각 항목에서 시작해서 가장 짧은 길이의 부분합 찾기
	// 모든 항목을 입력받으며 prefixSum에 해당 idx에서의 누적합 저장
	// 반복문을 통해 각 항목에서 조건에 맞는 부분합을 이분탐색으로 탐색
	// 그 idx의 누적합에서 0부터 현재 탐색을 시작한 항목까지의 합(exitedSum) 제외 후 S보다 큰지 비교
	// 맞으면 min과 비교
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N+1];
		prefixSum = new int[N+1];
		nums[0] = 0;
		prefixSum[0] = 0;
		exitedSum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			// 누적합 저장
			prefixSum[i] = prefixSum[i-1]+nums[i];
		}
		
		for(int i=1;i<=N;i++) {
			exitedSum += nums[i-1];
			int idx = findS(i, N);
//			System.out.println(i+"번째: "+idx );
			if(idx>0) {				
				min = Math.min(min, idx-i+1);
			}
		}
		
		
		if(min == Integer.MAX_VALUE) {
			bw.write("0");
		} else {			
			bw.write(min+"");
		}
		bw.flush();
		bw.close();

	}
	private static int findS(int left, int right) {
//		System.out.println(left+" "+right);
		if(left>=right) {
			if(prefixSum[left]-exitedSum>=S) {				
				return left; 
			} else {
				return -1;
			}
		}
		
		int mid = (left+right)/2;
		int tmpS = prefixSum[mid] - exitedSum;
		if(tmpS<S) {
			return findS(mid+1,right);
		} else if(tmpS>S) {
			return findS(left,mid);
		} else {
			return mid;
		}
		
	}


}