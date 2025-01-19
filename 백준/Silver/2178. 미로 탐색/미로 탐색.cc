#include <iostream>
#include <string>
#include <queue>

#define INF 987654321
using namespace std;

int N, M;
int map[101][101];
int answer = 0;

int dr[]{ 0,1,0,-1 };
int dc[]{ 1,0,-1,0 };

struct Node
{
    int r;
    int c;
    Node(int row, int col) :r(row),c(col)
    { }
};

void bfs()
{
    bool check[101][101]{false};
    queue<Node> q;
    q.push(Node(0, 0));
    check[0][0] = true;

    while (!q.empty())
    {
        int size = q.size();
        answer++;
        for (int i = 0;i < size;i++)
        {
            Node node = q.front();
            q.pop();

            if (node.r == (N - 1) && node.c == (M - 1)) return;

            for (int j = 0;j < 4;j++)
            {
                int nr = node.r + dr[j];
                int nc = node.c + dc[j];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || check[nr][nc]) continue;
           
                
                check[nr][nc] = true;
                if(map[nr][nc] == 1)
                    q.push(Node(nr, nc));
            }
        }
    }
}

int main() {
    cin >> N >> M;
    for (int i = 0;i < N;i++)
    {
        string str;
        cin >> str;
        int m = 0;
        for (char c : str)
        {
            map[i][m++] = c -48;
        }
    }

    bfs();

    cout << answer << endl;

    return 0;
}
