using System;
using System.Collections.Generic;

public class Solution {
    class Node{
        public int r;
        public  int c;
        public Node(int r, int c)
        {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int startX, startY;
    static int maxR, maxC;
    static bool[,] check;
    static int[,] map;
    
    public int solution(string[] board) {
        int answer = 0;
        maxR = board.Length;
        maxC = board[0].Length;
        map = new int[maxR, maxC];
        check = new bool[maxR, maxC];
        
        for(int i=0;i<maxR;i++)
        {
            string str = board[i];
            for(int j=0;j<maxC;j++)
            {
                check[i,j] = false;
                if(str[j] == 'R')
                {
                    startX = i;
                    startY = j;
                    map[i,j] = 0;
                }
                else
                {
                    map[i,j] = str[j] == 'G' ? 2 : str[j] == 'D' ? 1 : 0;
                }
            }
        }
        
        answer = BFS();        
        return answer;
    }
    
    static private int BFS()
    {
        Queue<Node> q = new Queue<Node>();
        q.Enqueue(new Node(startX,startY));
        check[startX,startY] = true;
        int cnt = 0;
        
        while(q.Count > 0)
        {
            int size = q.Count;
            for(int i=0;i<size;i++)
            {
                Node curNode = q.Dequeue();
                if(map[curNode.r, curNode.c] == 2)
                {
                    return cnt;
                }
                for(int j=0;j<4;j++)
                {
                    Node nextNode = MakeNode(curNode.r, curNode.c , j);
                    if(check[nextNode.r, nextNode.c]) continue;
                    check[nextNode.r, nextNode.c] = true;
                    q.Enqueue(nextNode);
                }
            }
            cnt++;
        }
        
        return -1;
    }
    static private Node MakeNode(int r, int c, int dir)
    {
        int nr = r;
        int nc = c;
        
        int tmpR = nr+dr[dir];
        int tmpC =nc+dc[dir];
        while(tmpR >=0 && tmpR<maxR && tmpC>=0 && tmpC<maxC && map[tmpR,tmpC]!=1)
        {
            nr = tmpR;
            nc = tmpC;
            tmpR = nr+dr[dir];
            tmpC =nc+dc[dir];
        }
        return new Node(nr, nc);
    }
}