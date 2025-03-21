#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int M, N;
    cin >> M >> N;
    vector<vector<int>> v(M,vector<int>(N,0));
    vector<vector<int>> v2(M,vector<int>(N,0));
    vector<vector<int>> v3(M,vector<int>(N,0));
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < v[i].size(); j++) {
            cin >> v[i][j];
            v2[i][j] = v[i][j];
        }
    }
    for (int i = 0; i < M; i++) {
        sort(v[i].begin(), v[i].end());
        v[i].erase(unique(v[i].begin(), v[i].end()), v[i].end());
    }

    for (int i = 0; i < M; i++) {
        for (int j = 0; j < v2[i].size(); j++) {
            
            v3[i][j] = lower_bound(v[i].begin(), v[i].end(), v2[i][j]) - v[i].begin();
        }
    }

    int cnt = 0;
    for (int i = 0; i < M - 1; i++) {
        for (int j = i + 1; j < M; j++) {
            if (v3[i] == v3[j]) cnt++;
        }
    }
    cout << cnt << "\n";
    return 0;
}