#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

int T, n, d, c;
vector<pair<int, int>> list[10001];
bool checked[10001];
int dist[10001]; // 감염 시간을 저장하는 배열

void countInfectionTime()
{
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, c});
    dist[c] = 0;

    while (!pq.empty())
    {
        int Time = pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (checked[node]) continue; // 이미 처리한 노드는 패스
        checked[node] = true;

        for (auto& p : list[node])
        {
            int nextNode = p.first;
            int nextTime = p.second;

            if (dist[nextNode] > Time + nextTime)
            {
                dist[nextNode] = Time + nextTime;
                pq.push({dist[nextNode], nextNode});
            }
        }
    }
}

int main()
{
    cin >> T;
    while (T--)
    {
        cin >> n >> d >> c;

        for (int i = 1; i <= n; i++)
        {
            list[i].clear();
        }
        
        memset(checked, false, sizeof(checked));
        fill_n(dist, 10001, 1e9); // 초기값을 충분히 큰 값으로 설정

        for (int i = 0; i < d; i++)
        {
            int a, b, s;
            cin >> a >> b >> s;
            list[b].push_back({a, s});
        }

        countInfectionTime();

        int countComp = 0, answer = 0;
        for (int i = 1; i <= n; i++)
        {
            if (dist[i] != 1e9) 
            {
                countComp++;
                answer = max(answer, dist[i]);
            }
        }

        cout << countComp << ' ' << answer << '\n';
    }
}
