#include <iostream>
#include <queue>
#define ll long long
using namespace std;


int T, A, B;
ll answer;
vector<ll> aList;
vector<ll> bList;
priority_queue<ll> aSubList;
priority_queue<ll> bSubList;

// n과 m은 1000이하 , A만 사용해도 안됨
// 전체 탐색으로 A와 B에서 나올 수 있는 모든 합 검색
// A는 맨 처음, B는 맨 나중에서부터 투포인터 형식으로 검색
// 두 값의 합이 T보다 크면 B에서 차감, 작으면 A에서 증감
// answer 카운트 후 출력

void sortArray()
{
	for (int i = 0;i < A;i++)
	{
		ll aSum = 0;
		for (int j = i ;j < A;j++)
		{
			aSum += aList[j];
			aSubList.push(-aSum); // 작은 수가 위로 오게
		}
	}

	for (int i = 0;i < B;i++)
	{
		ll bSum = 0;
		for (int j = i;j < B;j++)
		{
			bSum += bList[j];
			bSubList.push(bSum); // 큰 수가 위로 오게
		}
	}
}

void countPossiblity()
{
	while (!aSubList.empty() && !bSubList.empty())
	{
		ll left = -aSubList.top();
		ll right = bSubList.top();

		if (left + right > T)
		{
			bSubList.pop();
		}
		else if (left + right < T)
		{
			aSubList.pop();
		}
		else
		{
			ll leftCnt = 0;
			ll rightCnt = 0;
			while (!aSubList.empty() && -aSubList.top() == left)
			{
				leftCnt++;
				aSubList.pop();
			}
			while (!bSubList.empty() && bSubList.top() == right)
			{
				rightCnt++;
				bSubList.pop();
			}
			//cout << left << "와 " << right << " : " << leftCnt << ' ' << rightCnt<< endl;
			answer += leftCnt * rightCnt;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	cin >> A;
	for (int i = 0;i < A;i++)
	{
		ll x;
		cin >> x;
		aList.push_back(x);
	}
	cin >> B;
	for (int i = 0;i < B;i++)
	{
		ll x;
		cin >> x;
		bList.push_back(x);
	}

	sortArray();
	countPossiblity();

	cout << answer;

}
