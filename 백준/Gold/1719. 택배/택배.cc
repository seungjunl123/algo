#include <iostream>
#include <vector>
#include <algorithm>
#define INF 1e9
using namespace std;

int N, M;
int dist[201][201];
int path[201][201];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> M;

    // 초기화
    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= N; ++j) {
            if (i == j) dist[i][j] = 0;
            else dist[i][j] = INF;
            path[i][j] = j;  // 기본적으로 j로 간다고 생각
        }
    }

    // 간선 입력
    for (int i = 0; i < M; ++i) {
        int a, b, c;
        cin >> a >> b >> c;
        dist[a][b] = dist[b][a] = c;
        path[a][b] = b;
        path[b][a] = a;
    }

    // 플로이드 워셜 수행
    for (int k = 1; k <= N; ++k) {
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                    path[i][j] = path[i][k];  // i에서 j로 가는 첫 도시 = i에서 k로 가는 첫 도시
                }
            }
        }
    }

    // 결과 출력
    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= N; ++j) {
            if (i == j) cout << "- ";
            else cout << path[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}
