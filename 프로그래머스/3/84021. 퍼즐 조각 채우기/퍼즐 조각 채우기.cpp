#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int dx[] = { 0, 1, 0, -1 };
int dy[] = { -1, 0, 1, 0 };

vector<vector<pair<int, int>>> board_block;
vector<vector<pair<int, int>>> table_block;

int board_visited[51][51] = { 0, };
int table_visited[51][51] = { 0, };

// 좌표 배열을 받아서 0, 0 기준으로 정규화 후 정렬
void coordinatenormalize(vector<vector<pair<int, int>>>& blocks);
void coordinatenormalize(vector<pair<int, int>>& blocks);

// 좌표 배열을 받아서 시계방향 90도 회전 후 정규화
void rightrotation90(vector<pair<int, int>>& block) {
    int n = block.size();
    vector<pair<int, int>> tempvector(n, make_pair(0, 0));

    for (int i = 0; i < block.size(); i++) {
        tempvector[i].first = block[i].second;
        tempvector[i].second = n - block[i].first - 1;
    }
    for (int i = 0; i < n; i++) {
        block[i].first = tempvector[i].first;
        block[i].second = tempvector[i].second;
    }

    coordinatenormalize(block);
}

void coordinatenormalize(vector<vector<pair<int, int>>>& blocks) {
    for (auto& vv : blocks) {
        coordinatenormalize(vv);
    }
}

// 좌표 배열을 받아서 0, 0 기준으로 정규화 후 정렬
void coordinatenormalize(vector<pair<int, int>>& blocks) {
    int minx = 51;
    int miny = 51;

    for (auto& v : blocks) {
        if (minx > v.first) minx = v.first;
        if (miny > v.second) miny = v.second;
    }
    for (auto& v : blocks) {
        v.first -= minx;
        v.second -= miny;
    }

    sort(blocks.begin(), blocks.end());
}

// 좌표 배열 2개를 비교하여 완전히 같은지 확인
bool compareblock(vector<pair<int, int>>& board, vector<pair<int, int>>& table) {
    if (board.size() != table.size()) return false;

    int issame = true;
    for (int i = 0; i < board.size(); i++) {
        if (board[i].first != table[i].first || board[i].second != table[i].second) {
            issame = false;
            break;
        }
    }
    return issame;
}

int solution(vector<vector<int>> game_board, vector<vector<int>> table) {
    int answer = 0;
    int bsize = game_board.size();

    // 50 x 50 배열에서 블록 최대 개수 1250
    // 최대 행길이 더 길어지면 다른방식으로 바꿔야함
    board_block.assign(1250, vector<pair<int, int>>(0));
    table_block.assign(1250, vector<pair<int, int>>(0));

    // board_block에 각 블록 좌표 넣음
    int gbcnt = 0;
    for (int i = 0; i < game_board.size(); i++) {
        for (int j = 0; j < game_board[0].size(); j++) {
            if (board_visited[i][j] == 0 && game_board[i][j] == 0) {
                queue<pair<int, int>> board_q;
                board_q.push(make_pair(i, j));
                board_visited[i][j] = 1;

                while (!board_q.empty()) {
                    auto tq = board_q.front();
                    board_q.pop();
                    board_block[gbcnt].push_back(make_pair(tq.first, tq.second));

                    for (int k = 0; k < 4; k++) {
                        int nx = tq.first + dx[k];
                        int ny = tq.second + dy[k];
                        if (nx >= 0 && ny >= 0 && nx < bsize && ny < bsize) {
                            if (board_visited[nx][ny] == 0 && game_board[nx][ny] == 0) {
                                board_visited[nx][ny] = 1;
                                board_q.push(make_pair(nx, ny));
                            }
                        }
                    }
                }
                gbcnt++;
            }
        }
    }

    // table_block에 각 블록 좌표 넣음
    int tbcnt = 0;
    for (int i = 0; i < table.size(); i++) {
        for (int j = 0; j < table[0].size(); j++) {
            if (table_visited[i][j] == 0 && table[i][j] == 1) {
                queue<pair<int, int>> table_q;
                table_q.push(make_pair(i, j));
                table_visited[i][j] = 1;

                while (!table_q.empty()) {
                    auto tq = table_q.front();
                    table_q.pop();
                    table_block[tbcnt].push_back(make_pair(tq.first, tq.second));

                    for (int k = 0; k < 4; k++) {
                        int nx = tq.first + dx[k];
                        int ny = tq.second + dy[k];
                        if (nx >= 0 && ny >= 0 && nx < bsize && ny < bsize) {
                            if (table_visited[nx][ny] == 0 && table[nx][ny] == 1) {
                                table_visited[nx][ny] = 1;
                                table_q.push(make_pair(nx, ny));
                            }
                        }
                    }
                }
                tbcnt++;
            }
        }
    }

    // 좌표 상대화
    coordinatenormalize(board_block);
    coordinatenormalize(table_block);

    // 블록 조합 여부 확인용 vector
    vector<int> board_assembled(gbcnt, 0);
    vector<int> table_assembled(tbcnt, 0);

    // 비교
    for (int i = 0; i < gbcnt; i++) {
        for (int j = 0; j < tbcnt; j++) {   
            for (int k = 0; k < 4; k++) {
                if (board_assembled[i] == 0 && table_assembled[j] == 0 && compareblock(board_block[i], table_block[j])) {
                    answer += table_block[j].size();
                    board_assembled[i] = 1;
                    table_assembled[j] = 1;
                } else {
                    rightrotation90(table_block[j]);
                }
            }
        }
    }
    return answer;
}