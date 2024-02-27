import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	static int[] numbers;
	static List<Integer> arr;
	
	static BufferedReader br;
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		// 변수 입력
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
		numbers = new int[N];
		
//		visited[arr.get(0)] = 1;
		// 맨처음 드가는 인자에 대한 함수 실행
		func1(0);
		
		bw.flush();
		bw.close();
	}
	
	public static void func1(int count) throws IOException {
		// numbers의 인자가 M 번까지 차면 각 인수를 반환한다
		if(count == M) {
			for(int i =0; i<M;i++) {
				// DFS를 통해 저장된 각 인덱스별 항목 출력
				bw.write(numbers[i]+" ");
			}
			bw.write("\n");
			return;
		}
		// 중복이 발생하지 않게 하기 위해 시작값을 idx로 설정
		for(int i=0;i<N;i++) {
				// 반복 탐색이 안되도록 visitied 1설정 후
				// numbers에 해당 경로에 대한 저장
				numbers[count] = arr.get(i);
				// 다음 경로 탐색을 위해 count+1로 재귀함수 진행
				// i+1 로 설정해서 arr의 이전 인자가 포함되지 않게 한다
				func1(count+1);
				
		
		}
	}
	
}
