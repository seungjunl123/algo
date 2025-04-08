#include <iostream>
#include <vector>
#include <cmath>
#include <limits>
#include <iomanip>
using namespace std;

int T, N;
vector<pair<int, int>> coordinates;
vector<bool> checked;
double ans;
pair<long long, long long> total_sum;

double Calculate() {
    long long x = 0, y = 0;
    for (int i = 0; i < N; ++i) {
        if (checked[i]) {
            x += coordinates[i].first;
            y += coordinates[i].second;
        }
    }
    // 전체 합에서 2 * 선택된 합을 빼면 벡터 합
    long long dx = total_sum.first - 2 * x;
    long long dy = total_sum.second - 2 * y;
    return sqrt(dx * dx + dy * dy);
}

void Comb(int idx, int count) {
    if (count == N / 2) {
        ans = min(ans, Calculate());
        return;
    }

    for (int i = idx; i < N; ++i) {
        checked[i] = true;
        Comb(i + 1, count + 1);
        checked[i] = false;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;
    while (T--) {
        cin >> N;

        coordinates.clear();
        coordinates.resize(N);
        checked.assign(N, false);
        total_sum = {0, 0};
        ans = numeric_limits<double>::max();

        for (int i = 0; i < N; ++i) {
            cin >> coordinates[i].first >> coordinates[i].second;
            total_sum.first += coordinates[i].first;
            total_sum.second += coordinates[i].second;
        }

        // 대칭 제거: 첫 번째 점 고정
        checked[0] = true;
        Comb(1, 1);

        cout << fixed << setprecision(12) << ans << '\n';
    }

    return 0;
}
