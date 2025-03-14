#include <iostream>
#include <vector>
#include <queue>
#include <string>

using namespace std;

int R, C, answer = 0;
vector<vector<int>> Lake;
vector<pair<int, int>> SwanLoc;

queue<pair<int, int>> MeltQueue; // 얼음 녹는 좌표
queue<pair<int, int>> SwanQueue; // 백조 이동 좌표
bool visitedSwan[1500][1500] = { false }; // 백조 방문 여부
bool visitedMelt[1500][1500] = { false }; // 얼음 녹는 좌표 방문 여부

const int dr[] = { 0, 1, 0, -1 };
const int dc[] = { 1, 0, -1, 0 };

void Initialize()
{
    cin >> R >> C;
    Lake.resize(R, vector<int>(C));

    for (int i = 0; i < R; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < C; j++)
        {
            if (str[j] == 'L')
            {
                SwanLoc.push_back({ i, j });
                Lake[i][j] = 1; // 백조 위치도 물로 처리
            }
            else if (str[j] == '.') Lake[i][j] = 1;
            else Lake[i][j] = 0;
        }
    }
}

void BFS()
{
    // 백조의 출발 위치
    SwanQueue.push(SwanLoc[0]);
    visitedSwan[SwanLoc[0].first][SwanLoc[0].second] = true;

    // 초기 얼음 녹는 위치 저장
    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            if (Lake[i][j] == 1) // 물이면
            {
                for (int d = 0; d < 4; d++)
                {
                    int ni = i + dr[d];
                    int nj = j + dc[d];
                    if (ni >= 0 && ni < R && nj >= 0 && nj < C && Lake[ni][nj] == 0)
                    {
                        MeltQueue.push({ ni, nj });
                        visitedMelt[ni][nj] = true;
                    }
                }
            }
        }
    }
}

bool MoveSwan()
{
    queue<pair<int, int>> NextQueue;
    while (!SwanQueue.empty())
    {
        int r = SwanQueue.front().first;
        int c = SwanQueue.front().second;
        SwanQueue.pop();

        for (int i = 0; i < 4; i++)
        {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || visitedSwan[nr][nc]) continue;

            visitedSwan[nr][nc] = true;

            if (Lake[nr][nc] == 0) // 얼음이면 다음 턴에 이동
            {
                NextQueue.push({ nr, nc });
            }
            else if (nr == SwanLoc[1].first && nc == SwanLoc[1].second) // 백조가 만나면 종료
            {
                return true;
            }
            else
            {
                SwanQueue.push({ nr, nc });
            }
        }
    }
    SwanQueue = NextQueue; // 다음 턴에 이동할 백조 위치 갱신
    return false;
}

void MeltIce()
{
    int size = MeltQueue.size();
    while (size--)
    {
        int r = MeltQueue.front().first;
        int c = MeltQueue.front().second;
        MeltQueue.pop();

        Lake[r][c] = 1; // 얼음이 녹음

        for (int i = 0; i < 4; i++)
        {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || visitedMelt[nr][nc]) continue;

            if (Lake[nr][nc] == 0)
            {
                visitedMelt[nr][nc] = true;
                MeltQueue.push({ nr, nc });
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    
    Initialize();
    BFS();

    while (true)
    {
        if (MoveSwan()) break;
        MeltIce();
        answer++;
    }

    cout << answer;
    return 0;
}
