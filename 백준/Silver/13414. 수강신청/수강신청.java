import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	// 수강 가능인원 K와 학생 수 L 선언
	static int K, L;

	// 학번을 저장할 배열 생성
	static int[] studentId = new int[100000000];
	static Queue<String> sugang = new LinkedList<>();
	static int count = 0;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// K와 L 입력
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		// L만큼 반복 진행
		for(int i=0;i<L;i++) {
			// 학번이 0으로 시작하는 학생이 있기 때문에 우선 String으로 받고 배열에 넣을 때 parseInt를 수행
			// 배열 없이 중복을 검증할 수 있다면 훨씬 빠를 것 같다
			String a = br.readLine();
			sugang.offer(a);
			// 학번을 받을 때마다 queue에 넣고, 학번에 해당하는 인덱스에 1을 더해준다
			studentId[Integer.parseInt(a)]++;
		}
		// 모든 학번을 받고 난 후 queue가 다 끝날 때까지 poll 진행
		while(!sugang.isEmpty()) {
			// 학번 인덱스의 인자가 1보다 크면 count를 추가하지 않고, 대신 그 인덱스의 인자를 1빼준다
			String com = sugang.peek();
			if(studentId[Integer.parseInt(com)]>1) {
				sugang.poll();
				studentId[Integer.parseInt(com)]--;
			} else {
				bw.write(com+"\n");
				sugang.poll();
				count++;
				studentId[Integer.parseInt(com)]--;		
			}
			// count가 K에 도달하면 while을 끝내고 bw로 출력
			if(count==K) {
				break;
			}
		}
		bw.flush();
		bw.close();
	}


}
