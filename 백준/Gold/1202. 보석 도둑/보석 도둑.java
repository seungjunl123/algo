import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	// 무게 적은 가방 순으로 세워두고
	// 보석을 가치가 높은 순서로 정렬(가치가 같다면, 무게가 적은 순서대로)
	// pq를 두개 생성해서 보석과 가방을 정렬하고, 보석 별로 가방을 찾아서 넣음 될듯

	static class Juel{
		int M;
		int V;
		Juel(int M,int V){
			this.M =M;
			this.V = V;
		}

	}
	
	static int[] backPack;
	static Juel[] juels;
	static int N,K;
	static long answer;
	// 현재 보석이 지나간 가방들을 저장 -> 보석 탐색이 끝나면 다시 넣어줘야지
	static PriorityQueue<Integer> inpack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		backPack = new int[K];
		juels = new Juel[N];
		answer = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			juels[i] = new Juel(m,v);
		}
		Arrays.sort(juels, new Comparator<Juel>() {

			@Override
			public int compare(Juel o1, Juel o2) {
				if(o1.M == o2.M) {
					return o2.V - o1.V;
				}
				return o1.M-o2.M;
			}
			
		});

		for(int i=0;i<K;i++) {
			int C = Integer.parseInt(br.readLine());
			backPack[i] = C;
		}
		
		Arrays.sort(backPack);
		inpack = new PriorityQueue<>(Comparator.reverseOrder());
		int juelIdx = 0;
		for(int i=0;i<K;i++) {
			while(juelIdx<N && juels[juelIdx].M<=backPack[i]) {
				inpack.add(juels[juelIdx++].V);
			}
			
			if(!inpack.isEmpty()) {
				answer+=inpack.poll();
			}
		}

		
		System.out.println(answer);

	}


}