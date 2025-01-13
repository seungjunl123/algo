#include <iostream>
#include <vector>
using namespace std;
#define INF 987654321

// 1번에서 다른 도시로 가는 최소 시간
// 음수 사이클이 있음 -> 벨만 포드
// 최소 시간을 구하고 한번 더 돌려야 한다(시간이 감소하는지 확인하기 위함)


int N, M;
vector<pair<int,pair<int, int>>> roadList;
long long dist[501];
bool flag = false;


bool bellmanFord(int start)
{
	dist[start] = 0;
	// N개의 도시를 다 돌라면? N-1번 순환
	// 즉 음수 사이클이 없다면 N-1번 도는 동안 모든 경로의 최적화가 마무리
	// 그런데 N번째 순환에서 경로 최적화가 발생한다? 음수 사이클이 있어 무한 경로가 발생한다는 뜻
	for (int i = 0;i <= N;i++) 
	{
		for (auto j = 0;j < roadList.size();j++) // 반복자로 처음 주소부터 받아오기
		{
			int cur = roadList[j].first;
			int next = roadList[j].second.first;
			int cost = roadList[j].second.second;

			if (dist[cur] == INF) continue; // 아직 현재 경로가 없는 부분은 지나간다.
			if (dist[next] > dist[cur] + cost)
			{
				dist[next] = dist[cur] + cost;
				if (i == N) // N번째 순환에서 경로의 최적화 발생
					return true;
			}
		}
	}

	return false;
}

int main()
{

	cin >> N >> M;

	for (int i = 1;i <= N;i++)
	{
		dist[i] = INF;
	}
	for (int i = 0;i < M;i++)
	{
		int st, ed, ti;
		cin >> st >> ed >> ti;
		roadList.push_back(make_pair(st,make_pair(ed,ti))); // make_pair 로 pair 만들고 push_back으로 뒤로 추가
	}

	if (bellmanFord(1))
		cout << "-1"; // true 반환 시 음수 사이클이 있다
	else
	{
		for (int i = 2;i <= N;i++)
		{
			if (dist[i] == INF)
				cout << -1 << '\n';
			else
				cout << dist[i] << '\n';
		}
	}
	return 0;
}