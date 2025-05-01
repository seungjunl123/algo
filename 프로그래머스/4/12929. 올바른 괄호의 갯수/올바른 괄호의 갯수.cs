public class Solution {
    int answer = 0;
    
    public void permutation(int openCnt, int closeCnt, int sum)
    {
        if(sum < 0 || openCnt < 0 || closeCnt<0 ) return;
        if(openCnt == 0 && closeCnt == 0 && sum == 0) answer++;
         
        permutation(openCnt-1, closeCnt, sum+1);
        permutation(openCnt, closeCnt-1, sum-1);
        return;
    }
    
    public int solution(int n) {
        
        
        // 재귀
        permutation(n, n, 0);
        
        
        return answer;
    }
}