import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine())); //우선순위에 넣고
		}
		
		//낮은 갯수의 카드부터 더해줘야함
		while(pq.size() != 1) { //마지막 한 숫자만 남을 때 까지
			int hap = pq.poll() + pq.poll(); //두개를 빼고
			//System.out.println(hap);
			sum += hap;
			pq.add(hap); //합한걸 다시 추가
		}
		
		System.out.println(sum);

	}

}