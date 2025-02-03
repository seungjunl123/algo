#include <iostream>
#include <vector>


using namespace std;
int N;
int map[15][15];
int row[15];
int answer = 0;
const int dr[] { -1, -1, 1, 1 };
const int dc[] { 1, -1, 1, -1 };

void LocateQueen(int num);
bool check(int r, int c, int dir);

int main(void)
{
	cin >> N;
	
	for (int i = 0;i < N;i++)
	{
		row[i] = 0;
		for (int j = 0;j < N;j++)
		{
			map[i][j] = 0;
		}
	}
	LocateQueen(0);


	cout << answer;
	
	return 0;
}

void LocateQueen(int idx)
{
	if (idx == N)
	{
		answer++;
		return;
	}
	

	for (int j = 0;j < N;j++)
	{
		if (row[j] == 1) continue;
		bool flag = false;
		for (int k = 0;k < 4;k++)
		{
			if (!check(idx, j, k))
			{
				flag = true;
				break;
			}
		}
		if (flag) continue;
		map[idx][j] = 1;
		row[j] = 1;
		LocateQueen(idx + 1);
		map[idx][j] = 0;
		row[j] = 0;
	}
	return;
}

bool check(int r, int c, int dir)
{
	int nr = r + dr[dir];
	int nc = c + dc[dir];
	bool flag = false;
	while (nr >= 0 && nc >= 0 && nc < N && nr < N)
	{
		if (map[nr][nc] == 1)
		{
			return false;
		}
		nr += dr[dir];
		nc += dc[dir];
	}

	return true;
}