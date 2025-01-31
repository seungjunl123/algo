#include <iostream>

using namespace std;


int N;
int houses[1001][3];
int memo[1001][3];
int Min[3]{987654321, 987654321 , 987654321 };

int main(void)
{
	cin >> N;
	for (int i = 0;i < N;i++)
	{
		int a, b, c;
		cin >> a >> b >> c;
		houses[i][0] = a;
		houses[i][1] = b;
		houses[i][2] = c;
	}

	// 처음 선택한 값에 따라 memmoization이 이루어져야 한다.
	// 반복문을 처음 값 3개에 따라 돌리고, 처음 값에서 해당하는 인덱스가 아닌 값의 memo[0][i] = 9999로 설정
	// 마지막 값까지 값을 구한 뒤 i가 아닌 2개의 값 중 최소값을 찾는다.
	// 3개의 최소값 중 최소값을 찾음 된다.
	for (int i = 0;i < 3;i++)
	{
		for (int j = 0;j < 3;j++)
		{
			if (j != i)
			{
				memo[0][j] = 9999;
			}
			else
			{
				memo[0][j] = houses[0][j];
			}
		}

		for (int j = 1;j < N;j++)
		{
			memo[j][0] = houses[j][0] + min(memo[j - 1][1], memo[j - 1][2]);
			memo[j][1] = houses[j][1] + min(memo[j - 1][0], memo[j - 1][2]);
			memo[j][2] = houses[j][2] + min(memo[j - 1][1], memo[j - 1][0]);
		}

		for (int j = 0;j < 3;j++)
		{
			if (j == i) continue;
			else if (Min[i] > memo[N - 1][j]) Min[i] = memo[N - 1][j];
		}


	}

	int answer = Min[0];
	for (int i = 0;i < 3;i++)
	{
		if (answer > Min[i]) answer = Min[i];
	}

	cout << answer;
	return 0;
}