import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
    	// 1655 가운데를 말해요
    	// 계속 주어지는 값 중 중앙의 값을 말하기
    	// 중앙을 찾기 위해서 중앙을 기준으로 큰 값과 작은 값을 나누기
    	
    	// PQ를 두개 생성해 하나는 내림차순(작은 값 중 가장 큰 값이 peek)
    	// 하나는 오름차순으로 생성(큰 값중 가장 작은 값이 peek)
    	
    	// 두 PQ의 사이즈가 같으면 작은 값으로 보내기 -> 짝수개를 말했을 땐 작은 수를 말해야 하므로
    	// 작은 값을 담는 PQ의 사이즈가 크면 큰 값을 담는 PQ 넣기
    	// 이러다가 작PQ의 peek이 큰PQ의 peek보다 크다면 두 값을 스왑
    	// -> 새로 들어온 큰 값(혹은 작은 값이) 반대 방향의 가장 바깥 값과 바뀌어진다.
    	// -> 어차피 PQ니까 뒤 바뀐 값은 PQ내에서 정렬된다.
    	
    	// PQ에 계속 값을 넣으며 작은 PQ의 peek값을 출력하면 된다.
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	PriorityQueue<Integer> smaller = new PriorityQueue<>((o1, o2) -> o2-o1);
    	PriorityQueue<Integer> bigger= new PriorityQueue<>((o1, o2) -> o1-o2);
    	int N = Integer.parseInt(br.readLine());
    	
    	for(int i=0;i<N;i++) {
    		int num = Integer.parseInt(br.readLine());
    		
    		if(smaller.size() == bigger.size()) {
    			smaller.add(num);
    		}  else {
    			bigger.add(num);
    		}
    		
    		// 작은 쪽의 peek가 큰 쪽의 peek이 크면 바꿔준다.
    		if((smaller.size()!=0&&bigger.size()!=0) && 
    				smaller.peek() > bigger.peek()) {
    			int sTmp = smaller.poll();
    			int bTmp = bigger.poll();
    			
    			smaller.add(bTmp);
    			bigger.add(sTmp);
    		}
    		
    		System.out.println(smaller.peek());
    	}
    	
    	
    }
}
