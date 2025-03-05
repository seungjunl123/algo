#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#define INF 1e9
using namespace std;

int T, n, m, t, s, g, h;
vector<vector<pair<int, int>>> road;
vector<int> candidates;

vector<int> dijkstra(int start) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    vector<int> dist(n + 1, INF);
    dist[start] = 0;
    pq.push({0, start});
    
    while (!pq.empty()) {
        int curDist = pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (curDist > dist[node]) continue;

        for (auto [nex, cost] : road[node]) {
            if (dist[nex] > dist[node] + cost) {
                dist[nex] = dist[node] + cost;
                pq.push({dist[nex], nex});
            }
        }
    }
    return dist;
}

int main() {
    cin >> T;
    while (T--) {
        cin >> n >> m >> t >> s >> g >> h;
        road.assign(n + 1, vector<pair<int, int>>());
        candidates.clear();

        for (int i = 0; i < m; i++) {
            int a, b, d;
            cin >> a >> b >> d;
            road[a].push_back({b, d});
            road[b].push_back({a, d});
        }

        for (int i = 0; i < t; i++) {
            int r;
            cin >> r;
            candidates.push_back(r);
        }

        // 다익스트라 실행
        vector<int> distS = dijkstra(s);
        vector<int> distG = dijkstra(g);
        vector<int> distH = dijkstra(h);

        vector<int> result;
        for (int node : candidates) {
            int direct = distS[node]; 
            int viaG_H = distS[g] + distG[h] + distH[node]; // s -> g -> h -> 목적지
            int viaH_G = distS[h] + distH[g] + distG[node]; // s -> h -> g -> 목적지

            if (direct == viaG_H || direct == viaH_G) {
                result.push_back(node);
            }
        }

        sort(result.begin(), result.end());
        for (int x : result) {
            cout << x << " ";
        }
        cout << "\n";
    }
    return 0;
}
