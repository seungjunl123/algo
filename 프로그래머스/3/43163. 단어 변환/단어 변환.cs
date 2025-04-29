using System;
using System.Collections.Generic;

public class Solution {
    public int solution(string begin, string target, string[] words) {
        bool[] check = new bool[words.Length]; // Dictionary가 나았겠네
        int answer = 0;
        
        Queue<string> q = new Queue<string>();
        q.Enqueue(begin);
        while(q.Count!=0)
        {
            int size = q.Count;
            for(int i=0;i<size;i++)
            {
                string CurStr = q.Peek();
                q.Dequeue();
                
                if(CurStr.Equals(target)) 
                {
                    return answer;
                }
                
                for(int j=0;j<words.Length;j++)
                {
                    if(check[j]) continue;
                    
                    int count = 0;
                    for(int k=0;k<words[j].Length;k++)
                    {
                        if(words[j][k] != CurStr[k]) count++;
                    }
                    if(count==1)
                    {
                        check[j] = true;
                        q.Enqueue(words[j]);
                    }
                        
                }
            }
            answer++;
        }
        
        
        return 0;
    }
}