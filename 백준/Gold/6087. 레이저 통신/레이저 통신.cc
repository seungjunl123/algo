#include <iostream>
#include <vector>
#include <queue>
#include <string>
#define INF 987654321
using namespace std;

// BFS로 앞, 좌, 우 이동하면서 이동

struct Node
{
	int r;
	int c;
	int dir;
	int count;
	Node(int R, int C, int Dir, int Count)
		: r(R), c(C), dir(Dir), count(Count)
	{}
};
int W, H;
int answer = INF;
vector<vector<int>> map;
vector<pair<int, int>> LocationC;
vector<vector<vector<int>>> checked;

int dr[] = { 1,0,-1,0 };
int dc[] = { 0,-1,0,1 };

void BFS()
{
	queue<Node> q;
	int startR = LocationC[0].first;
	int startC = LocationC[0].second;

	for (int i = 0;i < 4;i++)
	{
		int nr = startR + dr[i];
		int nc = startC + dc[i];

		if (nr >= 0 && nc >= 0 && nr < H && nc < W && map[nr][nc] != 1)
		{
			q.push(Node(nr, nc, i,0));
			checked[nr][nc][i] = 0;
		}
	}

	while (!q.empty())
	{
		int curR = q.front().r;
		int curC = q.front().c;
		int curDir = q.front().dir;
		int curCount = q.front().count;


		q.pop();

		//cout << "cur R : " << curR << " cur C : "<< curC << " cur Count : " << curCount << " cur Dir : " << curDir << " 현재 체크: "<<checked[curR][curC] << endl;
		if (curR == LocationC[1].first && curC == LocationC[1].second)
		{
			if (curCount < answer) answer = curCount;
			continue;
		}

		for (int i = 0;i < 3;i++)
		{
			int nextDir;
			switch (i)
			{
			case 0 : 
				nextDir = curDir;
				break;
			case 1:
				nextDir = (curDir + 1) % 4;
				break;
			case 2:
				nextDir = (curDir + 3) % 4;
				break;
			default:
				break;
			}

			int nr = curR + dr[nextDir];
			int nc = curC + dc[nextDir];
			int nCount = i == 0 ? curCount : curCount + 1;

			if (nr < 0 || nc < 0 || nr >= H || nc >= W || map[nr][nc] == 1) continue;
			if ( checked[nr][nc][nextDir] <= nCount) continue;


			checked[nr][nc][nextDir] = nCount;

			q.push(Node(nr, nc, nextDir, nCount));
		}
	}
}

int main() {
	cin >> W >> H;
	map.resize(H, vector<int>(W, 0));
	checked.resize(H, vector<vector<int>>(W, vector<int>(4, INF)));

	for (int i = 0;i < H;i++)
	{
		string str;
		cin >> str;
		for (int j = 0;j < W;j++)
		{
			if (str[j] == '*')
			{
				map[i][j] = 1;
			}
			else if (str[j] == 'C')
			{
				LocationC.push_back(make_pair(i, j));
			}
		}
	}

	BFS();

	cout << answer << endl;
}
