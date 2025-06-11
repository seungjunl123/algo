#include <iostream>
#include <vector>
using namespace std;

const int dr[] = { 0, -1, 0, 1 };
const int dc[] = { 1, 0, -1, 0 };

bool board[101][101] = { false };

int main() {
    int N;
    cin >> N;

    for (int i = 0; i < N; i++) {
        int x, y, d, g;
        cin >> x >> y >> d >> g;

        vector<int> dir;
        dir.push_back(d);
        for (int j = 0; j < g; j++) {
            for (int k = dir.size() - 1; k >= 0; k--) {
                dir.push_back((dir[k] + 1) % 4);
            }
        }

        board[y][x] = true;
        for (int j = 0; j < dir.size(); j++) {
            y += dr[dir[j]];
            x += dc[dir[j]];
            if (y >= 0 && y <= 100 && x >= 0 && x <= 100)
                board[y][x] = true;
        }
    }

    int answer = 0;
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1]) {
                answer++;
            }
        }
    }

    cout << answer << '\n';
    return 0;
}
