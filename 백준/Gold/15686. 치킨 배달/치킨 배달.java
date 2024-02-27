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
	static int N;
	static int M;
	
	// 치킨집의 정보와 집의 위치를 알면 각각의 거리를 구할 수 있다
	static List<int[]> chicken;
	static List<int[]> house;
	
	// 최솟값을 저장할 변수 min 생성
	static int min = Integer.MAX_VALUE;
	
	//치킨집의 번호를 담을 수열을 위한 numbers와 visited 설정
	static int[] numbers;
	static int[] visited;
	 
	
	static BufferedReader br;
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		// 변수 입력
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		// 치킨집과 집의 위치를 저장한다
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int a = Integer.parseInt(st.nextToken());
				// 1번이면 x,y를 house에 2번이면  chicken에 넣는다
				if(a == 1) {
					int[] tmp = new int[2];
					tmp[0] = i;
					tmp[1] = j;
					house.add(tmp);
				} else if (a==2) {
					int[] tmp = new int[2];
					tmp[0] = i;
					tmp[1] = j;
					chicken.add(tmp);
				}
			}
			
		}
		
		// 남길 치킨집의 인덱스를 저장하는 numbers와 visited 생성
		numbers = new int[M];
		visited = new int[chicken.size()];
		
		//치킨집을 고르는 selectNum 함수 실행
		selectNum(0,0);

		bw.write(min+"");
		bw.flush();
		bw.close();
	}
	static void selectNum(int count,int idx) {
		if(count==M) {
			int sum= 0;
			// 주어진 numbers에 대한 치킨 거리 구하기
			// 치킨 거리의 합을 구한 후 비교
			for(int i=0;i<house.size();i++) {
				// 각 집에 대한 치킨 거리를 구하기 위해 최솟값 저장을 위한 변수와 거리를 저장할 변수 생성
				int chicdis = Integer.MAX_VALUE;
				int distance = 0;
				for(int j=0;j<M;j++) {
					// 거리는 한 집에서 각 치킨집까지의 x,y 좌표 차이의 절대값을 더한 값이다
					distance = Math.abs(house.get(i)[0]-chicken.get(numbers[j])[0])+Math.abs(house.get(i)[1]-chicken.get(numbers[j])[1]);
//					System.out.println(Math.abs(house.get(i)[0]-chicken.get(numbers[j])[0])+" "+Math.abs(house.get(i)[1]-chicken.get(numbers[j])[1])+" "+distance);
					if(chicdis>distance) {
						chicdis = distance;
					}
				}
//				System.out.println(chicdis);
				sum+=chicdis;
			}
			// 더해진 치킨 거리의 합의 최솟값보다 작으면 최솟값 갱신
			if(sum<min) {
				min = sum;
			}
			return;
		}
		// 중복 없이 치킨집 인덱스를 M만큼 정렬
		for(int i=idx;i<chicken.size();i++) {
			if(visited[i] ==0) {
				visited[i] = 1;
				numbers[count] = i;
				selectNum(count+1,i);
			}
			visited[i] = 0;
		}
		
	}
	
}
