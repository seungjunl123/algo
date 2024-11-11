import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


class Main {


    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	Stack<Integer> stack = new Stack<>();
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(br.readLine());
    	int[] arr = new int[N];
    	int[] dp = new int[N];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i=0;i<N;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	
    	// 이중 포문
    	// 맨 처음 인자를 dp 1로 지정한 다음
    	dp[0] = 1;
    	int LIS = 1;
    	for(int i=1;i<N;i++) {
    		dp[i] =1;
    		for(int j=0;j<i;j++) {
    			// 0부터 기준점 i 까지 현재점 j를 이동하며 값을 비교
    			// 기준점의 값이 j보다 크면 증가하는 부분 수열에 해당할 수 있다.
    			if(arr[i]>arr[j]) {
    				// 현재점과 기준점으로 이어지는 부분수열이(dp[j]+1)
    				// 기준점이 가지고 있는 최장수열 길이(dp[i])보다 크다면 교체
    				dp[i] = Math.max(dp[j]+1, dp[i]);
    				LIS = Math.max(LIS, dp[i]);
    			}
    		}
    	}
    	
    	// Stack을 출력
    	// 뒤에서 부터 내려오면서, dp값과 일치한다면 거기서 LIS가 이어진 부분이다
    	// 따라서 number값을 dp[N-1]로 설정한 다음 dp[i]가 number와 일치한다면
    	// Stack에 넣은 다음 number를 1 빼준다.
    	int num = LIS;
    	System.out.println(num);
    	for(int i=N-1;i>=0;i--) {
    		if(LIS==dp[i]) {
    			stack.push(arr[i]);
    			LIS--;
    		}
    	}
    	
    	while(!stack.isEmpty()) {
    		sb.append(stack.pop()+" ");
    	}
    	System.out.println(sb.toString());
    }
}
