#include <iostream>
#include <vector>
#include <string>
#include <queue>

#define INF 80000000
using namespace std;

// 다익스트라 
// 반드시 지나쳐야 하는 정점 1번과 2번이 있다면
// 둘 중 하나를 먼저 가는 경우 중 빠른 경우를 찾아서 출력

struct Node
{
    int end, cost;
    Node(int e, int c) : end(e), cost(c) {}
};

int N, M;
int dist[3][801];
vector<Node> roads[801];
int num1, num2;

void djickstra(int idx, int startNum)
{
    priority_queue<pair<int, int>> pq;
    for (int i = 1;i <= N;i++)
    {
        dist[idx][i] = INF;
    }
    dist[idx][startNum] = 0;

    pq.push(make_pair(0, startNum));
    while (!pq.empty())
    {
        int start = pq.top().second;
        int startCost = -pq.top().first;
        pq.pop();

        for (Node node : roads[start])
        {
            int end = node.end;
            int endCost = node.cost;

            if (dist[idx][end] > startCost + endCost)
            {
                dist[idx][end] = startCost + endCost;
                pq.push(make_pair(-dist[idx][end], end));
            }
        }
    }

}

int main() {
    cin >> N >> M;

    for (int i = 0;i < M;i++)
    {
        int start, end, cost;
        cin >> start >> end >> cost;
        roads[start].push_back(Node(end, cost));
        roads[end].push_back(Node(start, cost));
    }
    cin >> num1 >> num2;

    djickstra(0, 1);
    djickstra(1, num1);
    djickstra(2, num2);

    long long dist1 = dist[0][num1] + dist[1][num2] + dist[2][N];
    long long dist2 = dist[0][num2] + dist[2][num1] + dist[1][N];
    int answer = dist1 > dist2 ? dist2 : dist1;

    if (answer >= INF )
        cout << -1 << endl;
    else
        cout << answer << endl;
    return 0;
}
