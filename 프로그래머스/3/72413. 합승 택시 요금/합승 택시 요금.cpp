#include <string>
#include <vector>
#include <queue>
#define INF 200 * 100001
using namespace std;

// 택시에서 시작하는 모든 지점의 최소 거리
// 각 지점에서 A,B까지 가는 최소 거리의 합
int dist[201];
vector<pair<int, long long>> roads[201];


void Dijkstra(int start, int n)
{
    priority_queue<pair<long long, int>> pq;
    for(int i=1;i<=n;i++)
        dist[i] = INF;
    pq.push(make_pair(0, start));
    dist[start] = 0;
    
    while(!pq.empty())
    {
        int node = pq.top().second;
        long long nodeCost = pq.top().first;
        pq.pop();
        
        for(int i=0;i<roads[node].size();i++)
        {
            int next = roads[node][i].first;
            long long nextCost = roads[node][i].second;
            
            
            if(dist[next] > dist[node]+ nextCost)
            {
                dist[next] = dist[node]+ nextCost;
                pq.push(make_pair(-dist[next], next));
            }
                
        }
    }
}

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {

    
    for(vector<int> vec : fares)
    {
        for(int i=0;i<3;i++)
        {
            int start = vec[0];
            int end =  vec[1];
            int cost = vec[2];
            
            roads[start].push_back(make_pair(end,cost));
            roads[end].push_back(make_pair(start,cost));
        }
    }
    
    Dijkstra(s, n);
    vector<int> dist_s(dist, dist + n + 1);
    Dijkstra(a, n);
    vector<int> dist_a(dist, dist + n + 1);
    Dijkstra(b, n);
    vector<int> dist_b(dist, dist + n + 1);
    
    
    int answer = dist_s[a] + dist_s[b];
    for (int i = 1; i <= n; ++i) {
        if (i != s && i != a && i != b) {
            int cost = dist_s[i] + dist_a[i] + dist_b[i];
            answer = min(answer, cost);
        }
    }
    answer = min(answer, dist_s[a] + dist_b[a]);
    answer = min(answer, dist_s[b] + dist_a[b]);
    
    return answer;
}