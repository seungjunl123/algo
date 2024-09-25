class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int bigMax = 0;
        int smallMax = 0;
        
        for(int i=0;i<sizes.length;i++){
            int bigOne = sizes[i][0] > sizes[i][1] ? sizes[i][0] : sizes[i][1];
            int smallOne = sizes[i][0] <= sizes[i][1] ? sizes[i][0] : sizes[i][1];
            
            bigMax = bigMax > bigOne ?  bigMax : bigOne;
            smallMax = smallMax > smallOne ?  smallMax : smallOne;
        }
        
        answer = bigMax * smallMax;
        
        return answer;
    }
}