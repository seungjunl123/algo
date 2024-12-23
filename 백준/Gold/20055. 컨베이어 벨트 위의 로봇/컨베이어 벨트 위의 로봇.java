import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	// 1번 컨테이너의 벨트 번호를 기반으로 설정
	// 1. 컨테이너가 한칸 이동
	// 2. 로봇 이동(이동 시 내구도 1 감소)
	// 3. 1번 컨테이너에 로봇 올림(내구도 1 감소)
	// 4. 내구도 0인 벨트가 K개 이상이면 증가

	static int N, K;
	static int[] duration;
	static int[] robotPos;
	static int upLoadIdx = 0;
	static int answer = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		duration = new int[2 * N];
		robotPos = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			duration[i] = Integer.parseInt(st.nextToken());
		}

		while (K > 0) {
//			System.out.println(K);
			rotate();
			robotMoving();
			if (K <= 0)
				break;
			robotOnboarding();
			if (K <= 0)
				break;
			answer++;
		}

		System.out.println(answer);
	}

	private static void robotOnboarding() {
		if (duration[upLoadIdx] > 0) {
			robotPos[0] = 1;
			duration[upLoadIdx]--;
			if (duration[upLoadIdx] == 0) {
//				System.out.println("시작지점 감소 "+upLoadIdx);
				K--;
//				System.out.println("K : "+K);
				duration[upLoadIdx] = -1;
			}
		}

	}

	private static void robotMoving() {
//		System.out.println(answer +" 번째 : "+ upLoadIdx);
		for (int i = N - 2; i >= 0; i--) {
			int beltPos = upLoadIdx + i + 1 >= (2 * N) ? (upLoadIdx + i+ 1) % (2 * N) : upLoadIdx + i +1;
			if (robotPos[i] == 1 && robotPos[i + 1] == 0 && duration[beltPos] > 0) {
//				System.out.println("로봇 이동 beltpos : "+beltPos+" "+i);
				robotPos[i + 1] = 1;
				robotPos[i] = 0;
				duration[beltPos]--;
				if (duration[beltPos] == 0) {
//				
					K--;
//					System.out.println("K : "+K);
					duration[beltPos] = -1;
				}
			}
		}

		robotPos[N - 1] = 0;

	}

	private static void rotate() {
		upLoadIdx = upLoadIdx - 1 < 0 ? 2 * N - 1 : upLoadIdx - 1;

		for (int i = N - 2; i >= 1; i--) {
			robotPos[i] = robotPos[i - 1];
		}
		robotPos[N - 1] = 0;
		robotPos[0] = 0;
	}
}