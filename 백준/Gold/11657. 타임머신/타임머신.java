import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	private static class Node {
		int start;
		int end;
		int cost;

		Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

	}

	static boolean flag = false;
	static long[] dist;
	static Node[] map;
	static int INF = 987654321;
	static int N,M;

	public static void main(String[] args) throws IOException {
		// 음의 가중치가 있는 문제
		// 벨만-포드 알고리즘
		// 모든 간선에 대해 시작 노드에서 해당 간선의 시작점까지 현재 거리와 간선의 가중치의 합을 구함
		// 이 합이 간선의 끝점까지의 현재 거리보다 작다면, 간선의 끝점까지의 거리를 업데이트
		// 이 작업을 노드의 수-1 번 반복
		// 모든 간선을 다시 한번 검사해서 위의 업데이트가 또 반복된다면 이는 음의 사이클이 존재함을 의미
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 경로 저장
		dist = new long[N + 1];
		Arrays.fill(dist, INF);
		map = new Node[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			map[i] = new Node(start, end, cost);
		}
		
		if(bellmanford(1)) { // 음수 순환 존재하면 -1 출력 
			System.out.println(-1);
		}
		else {
			// 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단거리 출력 
			for(int i=2; i<N+1; i++) {
				if(dist[i] == INF) {// 도달할 수 없으면 -1 
					System.out.println("-1");
				}
				else { // 최단 거리 출력 
					System.out.println(dist[i]);
				}
			}
		}

	}

	static boolean bellmanford(int start){
		dist[start] = 0;
		for(int i=1;i<N+1;i++) {
			for(int j=0;j<M;j++) {
				int cur = map[j].start;
				int next = map[j].end;
				int cost = map[j].cost;
				
				if(dist[cur]==INF) continue;
				
				if(dist[next] > dist[cur]+cost) {
					dist[next] =dist[cur] + cost;
					if(i==N) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
