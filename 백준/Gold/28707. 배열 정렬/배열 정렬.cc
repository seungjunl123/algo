#include<bits/stdc++.h>
using namespace std;
 
int main()
{
    int n; cin >> n;
 
    vector <int> A(n);
    for (int& i : A) cin >> i;
 
    int m; cin >> m;
 
    vector <tuple <int, int, int>> M(m);
    for (auto& [l, r, c] : M)
    {
        cin >> l >> r >> c;
        l--, r--;
    }
 
    priority_queue <pair<int, vector <int>>> pq;
    map <vector <int>, int> dist;
 
    for (pq.push({ dist[A] = 0, A }); pq.size();)
    {
        auto [cost, now](pq.top()); pq.pop();
        if (dist[now] >= -cost)
            for (auto& [l, r, c] : M)
            {
                swap(now[l], now[r]);
                if (!dist.count(now) || dist[now] > c - cost)
                    pq.push({ -(dist[now] = c - cost), now });
                swap(now[l], now[r]);
            }
    }
 
    sort(A.begin(), A.end());
    cout << (dist.count(A) ? dist[A] : -1);
}