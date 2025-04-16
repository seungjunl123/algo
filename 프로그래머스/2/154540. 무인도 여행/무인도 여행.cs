using System;
using System.Collections.Generic;

public class Solution {
    static int[,] map;
    static bool[,] check;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public int[] solution(string[] maps) {
        int[] answer = new int[] {};
        List<int> answerList = new List<int>();
        int R = maps.Length;
        int C = maps[0].Length;
        map = new int[R, C];
        check = new bool[R,C];
        
        for(int i=0;i<R;i++)
        {
            char[] arr = maps[i].ToCharArray();
            for(int j=0;j<C;j++)
            {
                check[i,j] = false;
                if(arr[j] == 'X')
                    map[i,j] = 0;
                else
                    map[i,j] = int.Parse(arr[j].ToString());
            }
        }
        
        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                if(check[i,j]) continue;
                
                check[i,j] = true;
                if(map[i,j] == 0) continue;
                answerList.Add(Explore(i,j,R,C));
            }
        }
        if(answerList.Count==0)
        {
            int[] tmp = {-1};
            answer = tmp;
        }
        else
        {
            answerList.Sort();
            int[] tmp = new int[answerList.Count];
            for(int i=0;i<answerList.Count;i++)
            {
               tmp[i] = answerList[i];
            }   
            answer = tmp;
        }
        
        return answer;
    }
    
    static private int Explore(int r, int c,int maxR, int maxC)
    {
        int ret = map[r,c];
        for(int i=0;i<4;i++)
        {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<0 || nr >=maxR || nc<0 || nc>=maxC) continue;
            if(map[nr,nc] == 0 || check[nr,nc]) continue;
            check[nr,nc] = true;
            ret += Explore(nr,nc,maxR,maxC);
        }
        
        return ret;
    }
}