import java.util.PriorityQueue;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        PriorityQueue<Integer> pq;
        
        for(int i=0;i<commands.length;i++){
            pq = new PriorityQueue<>();
            int N = commands[i][0];
            int M = commands[i][1];
            int L = commands[i][2];
            
            for(int j=N-1;j<M;j++){
                pq.add(array[j]);
            }
            for(int k=0;k<L-1;k++){
                pq.poll();
            }
            
            answer[i] = pq.poll();
        }
        
        return answer;
    }
}