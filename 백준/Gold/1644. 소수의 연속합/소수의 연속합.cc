#include <iostream>
#include <string>
#include <vector>
using namespace std;

// 에라스토스의 체로 소수 구하기(총 합이 400만이 될때까지)
// 주어진 N 전까지 소수 값에서 이분탐색 진행
// 출발점이 left, 마지막 소수의 합이 right로 진행하면서 mid의 값에서 출발점의 값이 N과 같은지 확인
// 가능하면 answer++;
bool arr[4000000];
vector<long long>  sum;
int N, answer;

void findPrime()
{
	sum.push_back(0);
	long long num = 0;
	for (int i = 2;i < 4000000;i++)
	{
		arr[i] = true;
	}

	for (int i = 2;i < 4000000;i++)
	{
		if (arr[i])
		{
			for (int j = 2;j * i < 4000000;j++)
			{
				arr[i * j] = false;
			}
			num += i;
			sum.push_back(num);

		}

	}
}

bool binarySearch(int left, int right, int start)
{

	while (left <= right)
	{
		int mid = (left + right) / 2;

		if (sum[mid] - sum[start] == N)
		{
			return true;
		}
		else if (sum[mid] - sum[start] < N)
		{
			left = mid + 1;
		}
		else
		{
			right = mid - 1;
		}
	}
	return false;
}

void findExpression(int idx)
{
	int lastIdx = sum.size() - 1;
	for (int i = 0;i <= lastIdx;i++)
	{
		if (binarySearch(i, lastIdx, i))
			answer++;
	}
}


int main()
{
	findPrime();

	cin >> N;


	findExpression(N);

	cout << answer;
	return  0;
}

