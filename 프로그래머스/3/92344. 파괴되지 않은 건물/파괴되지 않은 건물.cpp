#include <string>
#include <iostream>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
        
    int N = board.size();
    int M = board[0].size();
    int size = skill.size();
    
    int map[N][M];
    int resultSum[N+1][M+1];
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<M;j++)
        {
            resultSum[i][j] = 0;
        }
    }
    
    for(int i=0;i<size;i++)
    {
        int r1 = skill[i][1];
        int r2 = skill[i][3];
        int c1 = skill[i][2];
        int c2 = skill[i][4];
        int val = skill[i][0] == 2 ? skill[i][5] : -skill[i][5];        
    
        resultSum[r1][c1] += val;
        resultSum[r1][c2+1] -= val;
        resultSum[r2+1][c1] -= val;
        resultSum[r2+1][c2+1] += val;
        
    }

        for(int i = 0 ; i < N ; i++){
            for(int j = 1 ; j < M ; j++){
                resultSum[i][j] += resultSum[i][j-1];
            }
        }
        
        //상 -> 하
        for(int i = 0 ; i < M ; i++){
            for(int j = 1 ; j < N ; j++){
                resultSum[j][i] += resultSum[j-1][i];
            }
        }
    
    
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<M;j++)
        {
            if(board[i][j] + resultSum[i][j] > 0) answer++;
        }
    }
    
    return answer;
}