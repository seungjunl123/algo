class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int size = triangle.length;
        int[][] dp = new int[size][size];
        
        dp[0][0] = triangle[0][0];
        
        for(int i=1;i<size;i++){
            for(int j=0;j<i+1;j++){
                dp[i][j] = triangle[i][j] + Math.max(
                    dp[i-1][(j-1>=0? j-1: 0)], dp[i-1][j]
                );
            }
        }
        
        for(int i=0;i<size;i++){
            if(dp[size-1][i]>answer) answer = dp[size-1][i];
        }
        
        return answer;
    }
}