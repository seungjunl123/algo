import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int M;
	static int[] arr;
	static int[] numbers;
	static boolean[] visited;
	static boolean[][] map;
	static int sum1;
	static int sum2;
	static int sum;
	static int min = Integer.MAX_VALUE;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		map = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n != 0) {
				for (int j = 1; j <= n; j++) {
					int a = Integer.parseInt(st.nextToken());
					map[i][a] = true;
				}
			}
		}

		for (int i = 1; i <= N / 2; i++) {
			// M 설정
			M = i;
			numbers = new int[M];
			selectNum(1, 0);
			// N과M 수열 함수 수행
			// return 시 while로 BFS 진행
			// count 2인지 확인 후 sum 계산 및 min 비교
		}
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}

		bw.write(min + "");
		bw.flush();
		bw.close();
	}

	static void selectNum(int index, int count) {
		if (count == M) {
			// 수열 배열의 경우의 수를 두개 구역으로 나눈다
			List<Integer> list1 = new ArrayList<>();
			List<Integer> list2 = new ArrayList<>();
			// 수열에 존재하는 수는 true로 설정 후
			boolean[] list = new boolean[N + 1];
			for (int i = 0; i < M; i++) {
				list[numbers[i]] = true;
			}
			// N번까지 반복하면서 수열에 들어간 구역은 list1, 아닌 곳은 list2에 넣는다
			for (int i = 1; i <= N; i++) {
				if (list[i] == true) {
					list1.add(i);
				} else {
					list2.add(i);
				}
			}
			// count 초기화 후 각 리스트에 대해 BFS 실행
			// BFS가 실행된 후 카운트 값이 2이면 sum을 구하고 최솟값 비교
			int bfs = 0;
			bfs += BFS(list1);
			bfs += BFS(list2);
			if (bfs == 2) {
				for (int i = 0; i < list1.size(); i++) {
					sum1 += arr[list1.get(i)];
				}
				for (int i = 0; i < list2.size(); i++) {
					sum2 += arr[list2.get(i)];
				}
				if (Math.abs(sum1 - sum2) < min) {
					min = Math.abs(sum1 - sum2);
				}
				sum1 = 0;
				sum2 = 0;
			}
			return;
		}
		for (int i = index; i <= N; i++) {
			if (visited[i] == false) {
				numbers[count] = i;
				visited[i] = true;
				selectNum(i, count + 1);
			}
			visited[i] = false;
		}
	}

	static int BFS(List<Integer> list) {
		Queue<Integer> queue = new LinkedList<>();
		// queue가 지나가면서 연결상태를 확인하기 위한 visit배열 하나 더 생성
		boolean[] quevisit = new boolean[N + 1];
		for (int i = 0; i < list.size(); i++) {
			// BFS가 진행된 이후 quevisite에 하나라도 true가 남아 있다면 연결이 안되어있다는 뜻
			quevisit[list.get(i)] = true;

		}

		// false가 방문했거나 list에 없던 지역
		quevisit[list.get(0)] = false;
		queue.offer(list.get(0));

		// 연결가능한 지역을 이어가면서 quevisit false 처리
		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int next = 1; next <= N; next++) {
				if (quevisit[next] != true || (map[next][node] != true)) {
					continue;
				}
				quevisit[next] = false;
				queue.offer(next);
			}
		}
		// while문이 끝난 후 quevisit 검사
		// true가 남아있으면 도달하지 못한 지역이다
		// q를 1로 설정 후 검사된 q를 리턴
		int q = 1;
		for (int j = 1; j <= N; j++) {
			if (quevisit[j] == true) {
				q = 0;
			}
		}
		return q;

	}
}
// N/2의 수까지 N과 M 수열로 경우의 수를 구한다
// 원소가 나누어지는 경우의 수를 list로 구분
// 각 리스트마다 size만큼 반복문 실행
// 경로 배열을 따라 BFS 실행, 실행할 때마다 count 증가
// BFS 실행 후 visited 순회, 0(안지나간 부분) 있으면 BFS 한번 더 돌림
// list1, 2에 대한 BFS 실행 후 count가 2가 아니면 구역이 두개가 아니므로 패스
// count가 두개면 각 list의 원소의 값을 더한 후 합의 최소값 검증
