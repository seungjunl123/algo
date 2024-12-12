import java.io.*;
import java.util.*;


public class Main {
	static int N,M;
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		// linkedList의 n+1 배열?
		// 근데 합치기는 쉬운데 탐색이 많아지면..?
		// 트리
		// 0 1 3 이면 1이 3의 부모가 되고, 1 1 7이면 1의 부모와 7의 부모를 보기
		// 0 1 3 , 0 7 3이면..? 작은 수가 부모가 되는 걸로 해보자
		// => 유니온파인드....왜 생각을 못햇을까
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[N+1];
		for(int i=0;i<=N;i++) {
			tree[i] = i;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			if(command == 0) {
				union(num1, num2);
			} else {
				if(findParent(num1) == findParent(num2)) {
					bw.write("YES\n");
				} else {
					bw.write("NO\n");
				}
			}
		}
		
		bw.flush();
		bw.close();
		

	}

	public static void union(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		if(a!=b)
			tree[b]=a;
		//		if(find(a)!=find(b))
        //		parent[b]=a;
	}

	private static int findParent(int num) {
		if(tree[num]==num) {
			return num;
		} else {
			return findParent(tree[num]);
		}
	}
}