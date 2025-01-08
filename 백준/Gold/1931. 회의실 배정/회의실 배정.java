import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Table implements Comparable<Table>{
		long start;
		long end;
		
		Table(long start, long end){
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Table o) {
			if(this.end == o.end) {
				return (int)(this.start - o.start);
			}
			return (int)(this.end - o.end);
		}
		
	}
	// 어케 봐야 할까
	static int max = 0;
	static int N;
	static PriorityQueue<Table> timeTable;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		timeTable = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			timeTable.add(new Table(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		while(!timeTable.isEmpty()) {
			Table node = timeTable.poll();
			max++;
			
			while(!timeTable.isEmpty()&& timeTable.peek().start<node.end) {
				timeTable.poll();
			}
		}
		System.out.println(max);
	}

}