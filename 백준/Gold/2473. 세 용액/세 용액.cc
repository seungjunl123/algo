#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int N;
vector<long long> list;
vector<long long> answer(3);
long long minSum = 3e9 + 1;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    list.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> list[i];
    }

    sort(list.begin(), list.end());

    for (int i = 0; i < N - 2; i++) {
        for (int j = i + 1; j < N - 1; j++) {
            long long twoSum = list[i] + list[j];
            long long target = -twoSum;

            // 이분 탐색 범위는 j+1 부터 N-1까지 (중복 방지)
            int left = j + 1, right = N - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                long long total = list[i] + list[j] + list[mid];
                if (abs(total) < minSum) {
                    minSum = abs(total);
                    answer = {list[i], list[j], list[mid]};
                }

                if (total < 0)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
    }

    sort(answer.begin(), answer.end());
    for (int i = 0; i < 3; i++) {
        cout << answer[i] << ' ';
    }
    cout << '\n';
}
