import java.util.*;

class Solution {
    static List<Integer>[] map;
    static int[] dist;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        map = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            map[i] = new ArrayList();
        }
        dist = new int[n+1];
    
        for(int i=0;i<edge.length;i++){
            map[edge[i][0]].add(edge[i][1]);
            map[edge[i][1]].add(edge[i][0]);
        }
        
        dist[1] = 0;
        FindNode(n);

        
        int max = 0;
        
        for(int i=2;i<=n;i++){
            if(dist[i]>max) {
                max = dist[i];
                answer =1;
            } else if(dist[i]==max){
                answer++;
            }
        }
        
        return answer;
    }
    
    public void FindNode(int n ){
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] check = new boolean[n+1];
        
        q.add(1);
        check[1] = true;
        
        while(!q.isEmpty()){
            count++;
            int size = q.size();
            for(int i=0;i<size;i++){
                int node = q.poll();
                
                for(int j=0;j<map[node].size();j++){
                    if(map[node].get(j)>0 && !check[map[node].get(j)]){
                        dist[map[node].get(j)] = count;
                        check[map[node].get(j)] = true;
                        q.add(map[node].get(j));
                    }
                }
            }
        }
    }
}