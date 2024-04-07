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
	static int N,M,P;
	static boolean[] partyCanSpeakLie, visited;
	static Queue<Integer> truth;
	static List<Integer>[] party,person;
	static int ans;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 진실을 아는 사람들의 리스트와 파티 리스트를 따로 나눔
		// 사람들 queue에서 한명 씩 뽑으며 그 사람이 들어가 있는 파티를 탐색
		// 탐색된 파티는 check배열을 true 표시한다
		// 파티에 있는 모든 인원들을 다시 queue에 넣음(visited?)
		// 모든 탐색이 끝나고 check배열에서 false 표시된 파티의 수를 카운팅
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 초기화
		person = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			person[i] = new ArrayList<>();
		}
		party = new ArrayList[M];
		for(int i=0;i<M;i++) {
			party[i] = new ArrayList<>();
		}
		partyCanSpeakLie = new boolean[M];
		visited = new boolean[N+1];
		truth = new LinkedList<>();
		ans = 0;
		
		// 진실아는 사람들을 Queue에 집어 넣음
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		for(int i=0;i<num;i++) {
			truth.offer(Integer.parseInt(st.nextToken()));
		}
		
		// 리스트(파티, 인원) 입력
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			for(int j=0;j<num;j++) {
				int w = Integer.parseInt(st.nextToken());
				// i번째 파티에 w라는 인원이 있다
				party[i].add(w);
				// w라는 인원이 i번째 파티에 참여한다
				person[w].add(i);
			}
		}
		// Queue를 빼내면서 안전한 파티를 찾는다.
		findSafeParty();
		
		for(int i=0;i<M;i++) {
			if(!partyCanSpeakLie[i]) ans++;
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
		
	}
	
	private static void findSafeParty() {
		while(!truth.isEmpty()) {
			int size = truth.size();
			for(int i=0;i<size;i++) {
				// 진실을 아는 사람리스트에서 한명을 빼서 방문 처리
				int node = truth.poll();
				if(visited[node]) continue;
				visited[node] = true;
				// 그 사람이 있는 파티를 모두 방문 처리할 것이다
				for(int j=0;j<person[node].size();j++) {
					int pt = person[node].get(j);
					if(partyCanSpeakLie[pt]) continue;
					partyCanSpeakLie[pt] = true;
					// 그러고 나서 그 파티에 있는 인원들을 뽑을 거야
					for(int k=0;k<party[pt].size();k++) {
						truth.offer(party[pt].get(k));
					}
				}
			}
		}
		
	}
}