#include <iostream>
#include <vector>
#include <queue>
#define INF 987654321
using namespace std;

int N, M, answer = 0;
int dist[1001];
int parent[1001]; // 부모 노드 기록
bool check[1001];
vector<pair<int, int>> road[1001];

int main() {
    cin >> N >> M;

    for (int i = 1; i <= N; i++) {
        dist[i] = INF;
        parent[i] = -1; // 초기화
    }

    for (int i = 0; i < M; i++) {
        int start, end, cost;
        cin >> start >> end >> cost;
        road[start].push_back({end, cost});
        road[end].push_back({start, cost});
    }

    priority_queue<pair<int, int>> q;
    q.push({0, 1});
    dist[1] = 0;

    while (!q.empty()) {
        int node = q.top().second;
        int nodeCost = -q.top().first;
        q.pop();

        if (check[node]) continue; // 이미 방문한 노드
        check[node] = true;

        for (auto& p : road[node]) {
            int end = p.first;
            int cost = p.second;

            if (dist[end] > nodeCost + cost) {
                dist[end] = nodeCost + cost;
                parent[end] = node; // 부모 기록
                q.push({-dist[end], end});
            }
        }
    }

    for (int i = 2; i <= N; i++) { // 부모 배열을 통해 간선 수 계산
        if (parent[i] != -1) answer++;
    }

    cout << answer << endl;
    for (int i = 2; i <= N; i++) {
        if (parent[i] != -1)
            cout << parent[i] << ' ' << i << "\n";
    }

    return 0;
}
