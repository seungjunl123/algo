#include <algorithm>
#include <iostream>
#include <string>
#include <vector>
#include <queue>

#define INF 987654321
using namespace std;

int N,M;
int map[201][201];
int dist[201][2]; //  distance, parent
vector<pair<int, int>>  roads[201];


void dijckstra(int start)
{
	priority_queue<pair<int, int>> pq;
	for (int i = 1;i <= N;i++)
	{
		dist[i][0] = INF;
		dist[i][1] = -1;
	}
	dist[start][0] = 0;
	dist[start][1] = start;
	pq.push(make_pair(0, start));

	while (!pq.empty())
	{
		int node = pq.top().second;
		int nodeCost = -pq.top().first;
		pq.pop();

		for (int i = 0;i < roads[node].size();i++)
		{
			int next = roads[node][i].first;
			int nextCost = roads[node][i].second;
			if (dist[next][0] > dist[node][0] + nextCost)
			{
				dist[next][0] = dist[node][0] + nextCost;
				if (dist[node][1] == start)
				{
					dist[next][1] = next;
				}
				else
				{
					dist[next][1] = dist[node][1];  // 부모 노드가 아니라 처음 거쳐야 할 노드를 전파
				}

				pq.push(make_pair(-dist[next][0], next));
			}
			
		}
	}
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	cin >> N >> M;
	
	for (int i = 0;i < M;i++)
	{
		int start;
		int end;
		int cost;
		cin >> start >> end >> cost;

		roads[start].push_back(make_pair(end, cost));
		roads[end].push_back(make_pair(start, cost));
	}

	for (int i = 1;i <= N;i++)
	{
		dijckstra(i);
		for (int j = 1;j <= N;j++)
		{
			if (i != j)
			{
				cout << dist[j][1] << ' ';
			}
			else
			{
				cout << '-' << ' ';
			}
		}
		cout << '\n';
	}
	cout << endl;

}
