using System;
using System.Collections.Generic;
using System.Collections;

public class Solution {
    public int solution(int n, int[,] computers) {
        int answer = 0;
        bool[] check = new bool[n];
        List<int>[] nodes = new List<int>[n];
        for(int i=0;i<n;i++)
        {
            nodes[i] = new List<int>();
        }
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=j && computers[i,j] == 1)
                {
                    nodes[i].Add(j);
                }
            }
        }
        
        
        
        for(int i=0;i<n;i++)
        {
            if(check[i]) continue;
            
            Queue<int> q = new Queue<int>();
            answer++;
            check[i] = true;
            q.Enqueue(i);
            while(q.Count !=0)
            {
                int cur = q.Dequeue();
                for(int j=0;j<nodes[cur].Count;j++)
                {
                    int next = nodes[cur][j];
                    if(check[next]) continue;
                    check[next] = true;
                    q.Enqueue(next);
                }
            }
                

        }
        
        return answer;
    }
}