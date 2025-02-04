#include <iostream>
#include <vector>


using namespace std;

int map[50][5];
int r1, c1,r2,c2;
int x = 0;
int y = 0;
int cnt = 0;

bool isInMap(int x, int y)
{
	return r1 <= y && r2 >= y && c1 <= x && c2 >= x;
}

void putMap(int x, int y)
{
	cnt++;
	if (isInMap(x, y))
	{
		map[y - r1][x - c1] = cnt;
	}
}

int main(void)
{
	cin >> r1>>c1>>r2>>c2;

	// 각 지점에 대해 값을 정할 수 있을까

	// r1, c1이 (0,0)이 되야 함
	// 그럼 기존 0,0은 -r1,-c1이겠지

	// 1부터 3까지는 하드코딩 해부러
	putMap(0, 0);

	// 0 -1

	while (x <= 5000 && y <= 5000)
	{
		if (x >= 0 && x == y)
		{
			x++;
			putMap(x, y);

			while (y != -x)
			{
				y--;
				putMap(x, y);
			}
		} 
		else if (x >= 0 && x == -y)
		{
			while (x != y)
			{
				x--;
				putMap(x, y);
			}
		}
		else if (x < 0 && x == y)
		{
			while (y != -x)
			{
				y++;
				putMap(x, y);
			}
		}
		else if (x < 0 && x == -y)
		{
			while (x != y)
			{
				x++;
				putMap(x, y);
			}
		}

	}
	
	int maxVal = max(max(map[0][0], map[r2 - r1][0]), max(map[0][c2 - c1], map[r2 - r1][c2 - c1]));
	cnt = 1;
	for (int i = 1; i <= 100000000; i *= 10) {
		if (maxVal % i == maxVal) {
			maxVal = cnt;
			break;
		}
		cnt++;
	}
	for (int i = 0;i <= r2 - r1;i++)
	{
		printf("%*d", cnt - 1, map[i][0]);
		for (int j = 1;j <= c2 - c1;j++)
		{
			printf("%*d", cnt, map[i][j]);
		}
		cout << endl;
	}
}

