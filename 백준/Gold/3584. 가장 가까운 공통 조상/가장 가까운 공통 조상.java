import java.io.*;
import java.util.*;

public class Main {
	static int T,N;
	static int[] parents;
	static List<Integer> list;
	static int answer;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			// parents의 초기값은 자기 자신을 부모로
			// 이후에 주어지는 루트에 따라 부모를 찾는다.
			// 모든 탐색이 끝난 뒤 자기 자신이 부모인 놈이 루트
			// 이후 조상 노드를 찾는 두 노드에서 각자 List로 조상을 저장하고
			// 저장된 노드에서 자기 자신까지 역순으로 탐색하면서 두 노드의 조상이 달라지는 부분을 찾는다
			N = Integer.parseInt(br.readLine());
			parents = new int[N+1];
			answer = 0;
			
			for(int j=0;j<N-1;j++) {
				st = new StringTokenizer(br.readLine());
				int parentNode = Integer.parseInt(st.nextToken());
				int childNode = Integer.parseInt(st.nextToken());
				
				parents[childNode] = parentNode;
//				System.out.println(childNode+"의 부모는 "+parentNode);
			}
			
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			
			// A 넣고
			list = new ArrayList<>();
			list.add(nodeA);
			findRoutes(nodeA);
			List<Integer> parentsA = list;
			
			// B 넣고
			list = new ArrayList<>();
			list.add(nodeB);
			findRoutes(nodeB);
			List<Integer> parentsB = list;

//			System.out.println(parentsA);
//			System.out.println(parentsB);
			
			answer = 0;
			for(int j= 0;j< Math.min(parentsA.size(), parentsB.size());j++) {
				int aAncestor = parentsA.get(parentsA.size()-1-j);
				int bAncestor = parentsB.get(parentsB.size()-1-j);
				if(aAncestor == bAncestor) {
					answer = aAncestor;
				} else {
					break;
				}
			}
			bw.write(answer+"\n");
		}
		bw.flush();
		bw.close();
	}


	private static void findRoutes(int node) {
		if(parents[node] != 0) {
			list.add(parents[node]);
			findRoutes(parents[node]);
		} 
	}
}