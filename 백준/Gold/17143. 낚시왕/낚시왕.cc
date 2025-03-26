#include <iostream> 
#include <vector>
using namespace std;

struct Shark {
    int speed, dir, size;
    Shark(int s = 0, int d = 0, int z = 0) : speed(s), dir(d), size(z) {}
};

vector<vector<Shark>> map;
int R, C, M, answer = 0;
const int dr[5] = { 0, -1, 1, 0, 0 };  // 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
const int dc[5] = { 0, 0, 0, 1, -1 };

// 상어 이동 로직 개선
void SharkMove() {
    vector<vector<Shark>> tmp(R + 1, vector<Shark>(C + 1, Shark()));

    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
            if (map[i][j].size > 0) {
                int s = map[i][j].speed;
                int d = map[i][j].dir;
                int z = map[i][j].size;
                int r = i, c = j;

                // 이동 거리 최적화
                if (d == 1 || d == 2) s %= (R - 1) * 2;
                else s %= (C - 1) * 2;

                // 상어 이동
                while (s--) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 1 || nr > R || nc < 1 || nc > C) {
                        d = (d == 1) ? 2 : (d == 2) ? 1 : (d == 3) ? 4 : 3;
                        nr = r + dr[d];
                        nc = c + dc[d];
                    }
                    r = nr;
                    c = nc;
                }

                // 이동한 자리에서 크기 비교 후 갱신
                if (tmp[r][c].size < z) {
                    tmp[r][c] = Shark(map[i][j].speed, d, z);
                }
            }
        }
    }
    map = tmp;
}

// 가장 가까운 상어 찾기 & 낚시왕 이동
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> R >> C >> M;
    map.resize(R + 1, vector<Shark>(C + 1));

    for (int i = 0; i < M; i++) {
        int r, c, s, d, z;
        cin >> r >> c >> s >> d >> z;
        map[r][c] = Shark(s, d, z);
    }

    int fishermanX = 0;
    while (fishermanX < C) {
        fishermanX++;

        // 상어 잡기
        for (int i = 1; i <= R; i++) {
            if (map[i][fishermanX].size > 0) {
                answer += map[i][fishermanX].size;
                map[i][fishermanX] = Shark();
                break;
            }
        }

        // 상어 이동
        SharkMove();
    }

    cout << answer << endl;
}
