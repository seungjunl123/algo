#include<iostream>
#include<algorithm>
#include<string>
#include<cstring>
#include<stack>
#include<queue>
#include<vector>
using namespace std;

int a, b, N;
// a,b 중 큰 값을 찾음
// 사전 순이므로, 그냥 1부터 큰 값을 정렬
// 그럼 반대편은?
	// 남은 건물수가 작은 수 - 1보다 적다 => 불가능
	// 같다 => a, b-1, b-2, ..., 1로 출력
	// 크다 => 뒤에서 1~b-1까지 출력, 나머지는 1
int main(void) 
{
	cin >> N >> a >> b;

	if (a + b > N + 1) {
		cout << -1 << endl;
		return 0;
	}
	if (a >= b)
	{
		for (int i = 1 ;i <= a;i++)
		{
			cout << i << ' ';
			if (i == 1)
			{

		for (int i = 0;i < N - a - b + 1;i++)
		{
			cout << "1 ";
		}
			}
		}
		for (int j = b-1;j >=1;j--)
		{

			cout << j << ' ';
		}

	}
	else
	{
		if (a == 1)
		{
			for (int i = b;i > 0;i--)
			{
				cout << i << ' ';
				if (i == b)
				{
					for (int j = 0;j <= N - a - b;j++)
					{
						cout << "1 ";
					}
				}
			}
		}
		else
		{

		for (int i = 0;i <= N  - a - b;i++)
		{
			cout << "1 ";
		}
		for (int i = 1;i < a;i++)
		{
			cout << i << ' ';
		}
		for (int i = b;i > 0;i--)
		{
			cout << i << ' ';
		}
		}
	}



	
}