import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<Integer> smalls = new PriorityQueue<>((o1, o2) -> o2-o1 );
	static PriorityQueue<Integer> bigs= new PriorityQueue<>((o1, o2) -> o1-o2 );
	public static void main(String[] args)throws Exception {
		// 중간 값을 보여줘야지
		// 두 개의 priorityQueue를 쓴다
		// 중앙 값을 기준으로 작은값을 넣는 pq, 큰 값을 넣는 pq를 사용
		// 작은 값을 내림차순으로 정렬하고, 큰값을 오름차순으로 정렬하면 두 pq에서는 중앙 두 값이 나온다
		// 처음에는 작은 pq에 값을 넣고, 작은 pq와 큰 pq의 size 차이가 발생하면 큰 pq에 넣는다.
		// 그러다가 큰 pq에 가장 작은 값이 작은 pq의 가장 큰 값보다 작다면, 두 값을 바꾼다.
		// 그러면서 정렬이 완료될 때 마다 작은 pq에서 가장 큰 값을 보여주면 끝!
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(smalls.size() <= bigs.size()) {
				smalls.add(num);
			} else {
				bigs.add(num);
			}
			
			if(bigs.size()!=0 && smalls.peek()>bigs.peek()) {
				int tmp = smalls.poll();
				smalls.add(bigs.poll());
				bigs.add(tmp);
			}
			
			System.out.println(smalls.peek());
		}
		
	}
}