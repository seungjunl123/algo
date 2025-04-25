using System;

public class Solution {
    public bool solution(string s) {
        bool answer = true;
        int tmp = 0;
        
        foreach(char c in s)
        {
            if(c == '(') 
            {
                tmp++;
            }
            else
            {
                if(tmp <=0)
                {
                    answer = false;
                    break;
                }
                tmp--;
            }
        }
        
        if(answer)
        {
            answer = tmp ==0 ? true : false;
        }
        
        return answer;
    }
}