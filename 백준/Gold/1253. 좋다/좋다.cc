#include <iostream>
#include <algorithm>
using namespace std;

int N;
long long number[2002];
int answer = 0;

int main() {
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> number[i];
    }

    // 배열 정렬
    sort(number, number + N);

    for (int k = 0; k < N; k++) { // 각 수에 대해 확인
        int i = 0, j = N - 1;     // 두 포인터 초기화

        while (i < j) { // 두 포인터가 겹치지 않을 때까지 반복
            if (i == k) { // i가 현재 검사 중인 수(k)와 같으면 건너뜀
                i++;
                continue;
            }
            if (j == k) { // j가 현재 검사 중인 수(k)와 같으면 건너뜀
                j--;
                continue;
            }

            long long sum = number[i] + number[j];

            if (sum == number[k]) { // 두 수의 합이 현재 수와 같다면
                answer++;           // 좋은 수로 카운트
                break;              // 중복 방지를 위해 루프 종료
            }
            if (sum < number[k]) {
                i++; // 합이 작으면 왼쪽 포인터 이동
            } else {
                j--; // 합이 크면 오른쪽 포인터 이동
            }
        }
    }

    cout << answer << endl; // 좋은 수 개수 출력
    return 0;
}
