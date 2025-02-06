#include <iostream>
#include <string>
#include <vector>
#include<limits.h>
#define INF 987654321
using namespace std;

int N, M;
long mdist[101];
vector<pair<int, int>> roads[101];
int Before[101];
bool flag = false;

void BellmanFord();

int main()
{
	cin >> N >> M;

	for (int i = 0;i < M;i++)
	{
		int st, ed, cost;
		cin >> st >> ed >> cost;
		// 음의 사이클로 찾기 위해 cost는 음수처리
		roads[st].push_back(make_pair(ed, -cost));
	}

	BellmanFord();

	return  0;
}

void BellmanFord()
{
	for (int i = 1;i <= N;i++)
	{

		mdist[i] = INF;
	}
	mdist[1] = 0;

	for (int i = 0;i < N;i++)
	{
		for (int j = 1;j <= N;j++)
		{
			for (pair<int, int> pair : roads[j])
			{
				int st = j;
				int end = pair.first;
				int val = pair.second;

				if (mdist[st]!=INF && mdist[end] > mdist[st] + val)
				{
					mdist[end] = mdist[st] + val;
					// 경로가 어디서 왔는지 저장
					Before[end] = st;
					if (i == N-1)
					{
						mdist[end] = -INF;
					}
				}
			}
		}
	}

	if (mdist[N] == INF || mdist[N] == -INF)
	{
		cout << -1;
	}
	else
	{
		vector<int> path;
		int s = N;
		while (s != 0) {
			path.push_back(s);
			s = Before[s];
		}
		for (int i = path.size() - 1; i >= 0; i--)
			cout << path[i] << " ";
	}
	return;
 }