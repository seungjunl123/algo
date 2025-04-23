#include <string>
#include <vector>

using namespace std;

// [0][0] ~ [N][0] 
// [N][1] ~ [N][N]
// [N-1][N-1] ~ [1][1]
// [2][1] ~ [N-1][1]
vector<vector<int>> map;

vector<int> solution(int n) {
    vector<int> answer;
    map.resize(n, vector<int>(n, -1));
    vector<int> dr = {1, 0,-1};
    vector<int> dc = {0,1,-1};
    int count = 1;
    int dir = 0;
    int CurR = 0;
    int CurC = 0;
    
    
    while(count <= n*(n+1)/2)
    {
        map[CurR][CurC] = count;
        
        int nr = CurR+dr[dir];
        int nc = CurC+dc[dir];
        if(nr <n && nr>=0 && nc <n && nc>=0 && map[nr][nc] == -1)
        {
            CurR = nr;
            CurC = nc;
        }
        else
        {
            dir = (dir+1) % 3;
            CurR += dr[dir];
            CurC += dc[dir];
        }
        count++;
    }
    
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            if(map[i][j] == -1) break;
            answer.push_back(map[i][j]);
        }
    }
    
    return answer;
}