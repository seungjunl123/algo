import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        
        int last = -1;
        int score = 1;
        Queue<Integer> pq = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        for(int i=0;i<progresses.length;i++){
            pq.add((100-progresses[i])%speeds[i]!=0?
                   (100-progresses[i])/speeds[i] + 1 : (100-progresses[i])/speeds[i] );
        }
        
        last = pq.poll();
        while(!pq.isEmpty()){
            // System.out.println(last+" \n");
            if(pq.peek()>last){
                answer.add(score);
                score = 1;
                last = pq.poll();
            } else {
                score++;
                pq.poll();
            }
        }
        answer.add(score);
        return answer;
    }
}