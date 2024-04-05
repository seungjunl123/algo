import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 배열에 방향이랑 숫자를 넣어줘야 하니까
class micro {
	int idx;
	int num;
	int dir;
	int r;
	int c;

	public micro(int idx, int r, int c, int num, int dir) {
		this.idx = idx;
		this.r = r;
		this.c = c;
		this.num = num;
		this.dir = dir;
	}

}

class Solution {
	static int T, N, M, K;
	static int[][] map;// 상 하 좌 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<micro> list;

	static int ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			list = new ArrayList<>();
			ans = 0;
			// 1번부터 보기 편하게 ㅎ
			list.add(new micro(0, 0, 0, 0, 0));

			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				// 미생물의 초기 위치 및 정보 저장
				list.add(new micro(i, r, c, n, d - 1));
			}

			takeTime();

			// idx가 0이 아닌(사라지거나 먹히지 않은) 미생물들의 합을 구한다
			for (int i = 1; i <= K; i++) {
				// System.out.println(list.get(i).num);
				if (list.get(i).idx != 0) {
					ans += list.get(i).num;
				}
			}

			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void takeTime() {

		for (int m = 1; m <= M; m++) {
			boolean flag = true;
			// 우선 클래스를 변경하고
			for (int i = 1; i <= K; i++) {
				// 먹힌 경우 없어진다
				if (list.get(i).idx == 0)
					continue;
				// 맵 위치 초기화
				map[list.get(i).r][list.get(i).c] = 0;

				list.get(i).r += dr[list.get(i).dir];
				list.get(i).c += dc[list.get(i).dir];
			}
			// 클래스 정보를 토대로 map을 수정한다
			for (int i = 1; i <= K; i++) {
				if (list.get(i).idx == 0)
					continue;

				int R = list.get(i).r;
				int C = list.get(i).c;
				// 새 위치 지정
				if (R >= 0 && C >= 0 && R < N && C < N) {
					// 하는데 이미 뭐가 들어있으면?
					if (map[R][C] != 0) {
						if (list.get(map[R][C]).num < list.get(i).num) {
							map[R][C] = list.get(i).idx;
						}
						flag = false;
					} else map[R][C] = list.get(i).idx;

					// 좌표가 끝점(0,N-1)을 포함할 때 방향은 반대, 미생물은 절반
					if (R == 0 || C == 0 || R == N - 1 || C == N - 1) {
						list.get(i).num /= 2;
						// 미생물이 1이되어 2로 나누었을 때 0이되면 더이상 인식하지 않게 idx를 0으로 수정
						if (list.get(i).num == 0) {
							list.get(i).idx = 0;
						}
						switch (list.get(i).dir) {
						case 0:
							list.get(i).dir = 1;
							break;
						case 1:
							list.get(i).dir = 0;
							break;
						case 2:
							list.get(i).dir = 3;
							break;
						case 3:
							list.get(i).dir = 2;
							break;
						}
					}
				}
			}
			// flag가 false면 중복이 발생했다
			if (!flag) {
				for (int i = 1; i <= K; i++) {
					if (list.get(i).idx == 0)
						continue;
					// map의 r,c에서 해당 인덱스가 아닌 경우
					if (map[list.get(i).r][list.get(i).c] != list.get(i).idx) {
						// map에 저장된 인덱스의 미생물 수에 현재 미생물 값을 더해주고
						list.get(map[list.get(i).r][list.get(i).c]).num += list.get(i).num;
						// 현재 인덱스는 잡아 먹혔으므로 0처리
						list.get(i).idx = 0;
					}
				}
			}

		}

	}
}