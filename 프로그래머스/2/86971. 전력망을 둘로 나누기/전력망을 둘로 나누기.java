import java.util.*;
class Solution {
    private static List<Integer>[] list;
    public int solution(int n, int[][] wires) {
        // 노드 별 연결 리스트를 작성
        // 각 wire 별로 끊어가면서 끊어진 노드들이 이어진 값을 비교
        // 절대값 중에서 가장 낮은 값을 answer로 설정
        int min = 987654321;
        
        list = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0;i<n-1;i++){
            list[wires[i][0]].add(wires[i][1]);
            list[wires[i][1]].add(wires[i][0]);
        }
        
        for(int i=0;i<n-1;i++){
            int nodes1 = findWires(wires[i][0],wires[i][1]);
            int nodes2 = findWires(wires[i][1],wires[i][0]);
            
            if(nodes1 > nodes2 && min>(nodes1 - nodes2)) min = nodes1 - nodes2;
            else if(nodes1 <= nodes2&& min>(nodes2 - nodes1)) min = nodes2 - nodes1;
        }
        
        return min;
    }
    
    public int findWires(int num1, int num2){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] checked = new boolean[101];
        int count  = 0;
        
        queue.add(num1);
        checked[num1] = true;
        checked[num2] = true;
        
        while(!queue.isEmpty()){
            int qSize = queue.size();
            for(int k=0;k<qSize;k++){
                count++;
                int node = queue.poll();
                int size = list[node].size();
            
                for(int i=0;i<size;i++){
                    int target = list[node].get(i);
                    if(!checked[target]){
                        queue.add(target);
                        checked[target] = true;
                    }
                }
            }

        }
        return count;
    }
}