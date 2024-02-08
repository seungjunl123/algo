import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// N, M Static 선언(메서드 사용할거라서)
	// visited 선언
	// numbers 선언
	// 인자 받을 List하나 선언 -
	static int N;
	static int M;
	static int[] visited;
	static int[] numbers;
	static List<Integer> arr;
	
	public static void main(String[] args) throws IOException {
		// 변수 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// arr생성 후 인자 받기
		arr = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(arr);
		
		//visited 배열 생성
		visited = new int[arr.get(N-1)+1];
		numbers = new int[N];
		
//		visited[arr.get(0)] = 1;
		// 맨처음 드가는 인자에 대한 함수 실행
		func1(0);
	}
	
	public static void func1(int count) {
		// numbers의 인자가 M 번까지 차면 각 인수를 반환한다
		if(count == M) {
			for(int i =0; i<M;i++) {
				// DFS를 통해 저장된 각 인덱스별 항목 출력
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=0;i<N;i++) {
			// visited가 0인(아직 들리지 않은) 부분 도착 시 
			if(visited[arr.get(i)]==0) {
				// 반복 탐색이 안되도록 visitied 1설정 후
				visited[arr.get(i)] =1;
				// numbers에 해당 경로에 대한 저장
				numbers[count] = arr.get(i);
				// 다음 경로 탐색을 위해 count+1로 재귀함수 진행
				func1(count+1);
				// 한 경로에 대한 탐색이 완료되면 visitied 배열 초기화
				visited[arr.get(i)] =0;
				
			}
		}
	}
	
}
