using System;

public class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[] {};
        
        int xy = brown + yellow;
        for(int i=1;i<xy;i++)
        {
            if(xy%i != 0 ) continue;
            int j = xy/i;
            if(i*2 + j*2 - 4 == brown)
            {
                 return new int[] {j,i};
            }
        }
        return answer;
    }
}