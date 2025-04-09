#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
    int N;
    cin >> N;

    vector<long long> list(N), answer(3);
    for (int i = 0; i < N; ++i) {
        cin >> list[i];
    }

    sort(list.begin(), list.end());
    long long minSum = 3e9 + 1;

    for (int i = 0; i < N - 2; ++i) {
        int left = i + 1, right = N - 1;

        while (left < right) {
            long long sum = list[i] + list[left] + list[right];
            if (abs(sum) < minSum) {
                minSum = abs(sum);
                answer = {list[i], list[left], list[right]};
            }

            if (sum < 0) left++;
            else right--;
        }
    }

    sort(answer.begin(), answer.end());
    for (int i = 0; i < 3; i++) {
        cout << answer[i] << ' ';
    }
    cout << '\n';
}
