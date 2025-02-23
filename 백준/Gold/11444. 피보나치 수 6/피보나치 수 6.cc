#include <iostream>
#include <vector>
#include <map>
using namespace std;

long long N;
map<long long, long long> list;

long long DivideAndConquer(long long N)
{

	if (list.find(N) != list.end())
		return list[N];
	

	long long value;
	if (N % 2 == 0)
	{
		value = DivideAndConquer(N / 2l + 2) * DivideAndConquer(N / 2) +
			DivideAndConquer(N / 2l + 1) * DivideAndConquer(N / 2l - 1);
	}
	else
	{
		value = DivideAndConquer(N / 2l + 3) * DivideAndConquer(N / 2l) +
			DivideAndConquer(N / 2l + 2) * DivideAndConquer(N / 2l - 1);
	}
	value %= 1000000007l;
	list.emplace(N, value);
	return value;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);


	cin >> N;
	list.emplace(1, 0);
	list.emplace(2, 1);
	list.emplace(3, 1);
	list.emplace(4, 2);
	list.emplace(5, 3);
	
	long long answer = DivideAndConquer(N+1);

	cout << answer;
}
