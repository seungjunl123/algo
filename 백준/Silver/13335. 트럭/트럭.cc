#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, W, L;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> W >> L;
    
    queue<int> trucks;
    for (int i = 0; i < N; i++) {
        int weight;
        cin >> weight;
        trucks.push(weight);
    }

    queue<int> bridge;  // 다리 위의 트럭 상태를 저장 (길이 W 유지)
    for (int i = 0; i < W; i++) bridge.push(0);  // 처음은 모두 빈 공간

    int time = 0;
    int curLoad = 0;

    while (!trucks.empty() || curLoad > 0) {
        // 1. 한 칸씩 이동 (시간 증가, 트럭 한 칸 나감)
        time++;

        // 2. 맨 앞 트럭(또는 0) 내리기
        curLoad -= bridge.front();
        bridge.pop();

        // 3. 다음 트럭을 올릴 수 있으면 올리기
        if (!trucks.empty() && curLoad + trucks.front() <= L) {
            int nextTruck = trucks.front(); trucks.pop();
            bridge.push(nextTruck);
            curLoad += nextTruck;
        }
        else {
            // 못 올라오면 0으로 채움
            bridge.push(0);
        }
    }

    cout << time << "\n";
    return 0;
}
