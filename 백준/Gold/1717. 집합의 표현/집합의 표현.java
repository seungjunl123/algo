import java.util.*;
import java.io.*;

public class Main {
	static int parents[], N, M;

	private static void make() {
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if(a!=parents[a]) {
			parents[a] = find(parents[a]);
		}
		return parents[a];
	}

	private static void union(int left, int right) {
		parents[find(right)] = find(left);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int check = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			if (check == 1) {
				if (find(left) == find(right)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			} else {
				union(left, right);
			}
		}
		System.out.println(sb);
	}
}