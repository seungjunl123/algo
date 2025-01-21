#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int answer;
int players[4];		// player 위치 넣는다
int playerMAP[33];	// 해당 칸에 플레이어가 있는지 여부
int dice[10];
vector <int> MAP[33] = {
	{1}, {2}, {3}, {4}, {5},				// 0 ~ 4
	{6, 21}, {7}, {8}, {9}, {10},			// 5 ~ 9
	{11,25}, {12}, {13}, {14}, {15},		// 10 ~ 14
	{16, 27}, {17}, {18}, {19}, {20},		// 15 ~ 19
	{32}, {22}, {23}, {24}, {30},			// 20 ~ 24
	{26}, {24}, {28}, {29}, {24},			// 25 ~ 29
	{31}, {20}, {32}						// 30 ~ 32
};

int scoreMAP[33]{
	0, 2, 4, 6, 8,				// 0~4
	10, 12, 14, 16, 18,			// 5~9
	20, 22, 24, 26, 28,			// 10 ~ 14
	30, 32, 34, 36, 38,			// 15 ~ 19
	40, 13, 16, 19, 25,			// 20 ~ 24
	22, 24, 28, 27, 26,			// 25 ~ 29
	30, 35, 0					// 30 ~ 32
};

void func(int level, int curScore) {

	// 주사위를 다 돌렸으면 최대점수 업데이트
	if (level == 10) {
		answer = max(answer, curScore);
		return;
	}

	for (int i = 0; i < 4; i++) {

		int curPosition = players[i];	// 현재 말의 위치
		int target;						// 다음에 갈 수 있는 위치

		// 한 칸씩만 이동해본다
		if (MAP[players[i]].size() == 2)
			target = MAP[curPosition][1];
		else
			target = MAP[curPosition][0];

		// 이미 한 칸 이동했으니 1부터 시작해서 for문 돌리기
		for (int j = 1; j < dice[level]; j++)
			target = MAP[target][0];

		// 도착했거나, 도착한 지점에 플레이어가 아무도 없을때 재귀타기
		if (target == 32 || (target != 32 && playerMAP[target] == 0)) {

			// 해당 말 위치 업데이트
			playerMAP[curPosition] = 0;
			playerMAP[target] = 1;
			players[i] = target;

			// 재귀타기
			func(level + 1, curScore + scoreMAP[target]);

			// 해당 말 위치 원복
			players[i] = curPosition;
			playerMAP[target] = 0;
			playerMAP[curPosition] = 1;			// 원래 있던 위치로 말 되돌리기!
		}
	}
}

int main() {
	//freopen("sample_input.txt", "r", stdin);
	for (int i = 0; i < 10; i++) {
		cin >> dice[i];
	}

	func(0, 0);

	cout << answer;

	return 0;
}