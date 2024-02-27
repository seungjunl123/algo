import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	// 배열의 크기 N,M와 회전 연산의 개수 K 선언
	static int N;
	// 배열 arr 선언
	static int[][] arr;
	// 도시의 방문 여부를 기록하는 visited 선언
	static int[] visited;
	
	static int min;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 주어지는 값을 입력
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		visited = new int[N];
		min = Integer.MAX_VALUE;
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 경로의 최솟값을 결정하는 메서드 selectNum 실행
		for(int i=0;i<N;i++) {
			visited[i] = 1;
			selectNum(0,0,i,i);
			visited[i] = 0;
		}
		bw.write(min+"");
		bw.flush();
		bw.close();
		
	}
	static void selectNum(int count,int sum,int sidx, int idx) {
		// count는 도시를 들른 횟수
		// count가 N이면 모든 도시를 돌았으니 비용의 합을 min과 비교한다
		if(count==N-1) {
			if(arr[idx][sidx]!=0) {
				sum+=arr[idx][sidx];
				if(sum<min) {
					min = sum;
				}
			}
			return;
		}
		for(int i=0;i<N;i++) {
			// idx에서 출발해서 i로 도착
			// i 도시를 아직 가보지 않았고 비용이 0이 아니라면 i 도시로 이동
			if(visited[i]==0&&arr[idx][i]!=0) {
				visited[i] = 1;
				selectNum(count+1,sum+arr[idx][i],sidx,i);
				visited[i] = 0;
			}
		}

	}
}
