#include <iostream>
#include <queue>
#include <vector>

#define INF 987654321
using namespace std;


// 다익스트라 문제
// 두 개의 출발 지점에서 시작해서 지헌쪽이 빠르면서 거리가 가장 짧은 지점 찾기
struct Node
{
    int end;
    int cost;
    Node(int e, int c) :end(e), cost(c) {};
};

vector<Node> roads[1001];
int dist[2][101]; // 0번: 성하 , 1번 : 지헌
int S, J, V, M;
int minDist = INF;
int answer = 0;

void dijkstra(int idx, int freind)
{
    dist[freind][idx] = 0;
    priority_queue<pair<int, int>> pq; //뒤가 지점. 음수로 넣을 것!
    pq.push(make_pair(0, idx));

    while (!pq.empty())
    {
        int node = pq.top().second;
        int nodeCost = -pq.top().first;
        pq.pop();
        for (Node n : roads[node])
        {
            int next = n.end;
            int nextCost = n.cost;

            if (dist[freind][next] > dist[freind][node] + nextCost)
            {
                dist[freind][next] = nodeCost + nextCost;
                pq.push(make_pair(-dist[freind][next], next));
            }
        }


    }
}

int main() {
    cin >> V >> M;
    answer = V+1;
    for (int i = 0;i < M;i++)
    {
        int start;
        int end;
        int cost;
        cin >> start >> end >> cost;
        roads[start].push_back(Node(end, cost));
        roads[end].push_back(Node(start, cost));
    }
    cin >> J >> S;

    for (int j = 0;j < 2;j++)
    {
        for (int i = 1;i <= V;i++)
        {
            dist[j][i] = INF;
        }
    }

    dijkstra(S,0);
    dijkstra(J, 1);
    dist[1][answer] = 987654321;

    for (int i = 1;i <= V;i++)
    {
        if (i == S || i == J) continue;
        minDist = min(minDist, dist[0][i] + dist[1][i]);
    }

    for (int i = V;i > 0;i--)
    {
        if (i == S || i == J) continue;
        if (minDist < dist[0][i] + dist[1][i]) continue;
        minDist = dist[0][i] + dist[1][i];
        if (dist[0][i] < dist[1][i]) continue;
        if (dist[1][i] <= dist[1][answer])
            answer = i;
    }

    cout << (answer == (V+1) ? -1 : answer);

    return 0;
}
