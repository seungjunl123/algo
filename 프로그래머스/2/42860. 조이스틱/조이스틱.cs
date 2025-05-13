using System;

public class Solution {
    public int solution(string name) {
        int answer = 0;
        int n = name.Length;
        char[] arr = name.ToCharArray();

        int minMove = n-1;
        for(int i=0;i<name.Length;i++)
        {
            int TopDown = arr[i] - 'A';
            int DownTop = 'Z' - arr[i] + 1 ;
            answer += Math.Min(TopDown, DownTop) ;
            
            int y = i + 1; // x 오른쪽에 있으면서 A가 아닌 문자가 있는 위치를 y라하자
            while( y < n && name[y] == 'A') y++;
            minMove = Math.Min( minMove, Math.Min( i+i+(n-y), i+(n-y)+(n-y) ) );
        }
        answer+= minMove;
        return answer;
    }
}