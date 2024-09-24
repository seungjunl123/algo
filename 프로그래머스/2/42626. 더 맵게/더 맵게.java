import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        int size = scoville.length;
        for(int scv : scoville){
            pq.add(scv);
        }
        
        while( size >1 && pq.peek() < K ){
            int smallOne = pq.poll();
            int smallTwo = pq.poll();
            pq.add(smallOne+smallTwo*2);
            
            answer++;
            size--;
        }
        
        if( pq.peek()<K){
            answer = -1;
        }
        
        
        return answer;
    }
}