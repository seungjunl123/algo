import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        // System.out.println(citations[0]);
        
        for(int i=0;i<citations.length;i++){
            // System.out.println(citations[i]);
            if(citations.length-i<=citations[i] ) {
                return citations.length-i;
            }
        }
        return answer;
    }
}