#include <algorithm>
#include <iostream>
#include <string>
#include <set>
#include <queue>
using namespace std;

int N, M;
set<string> Map;
int cnt = 0;
priority_queue<string, vector<string>, greater<string>> list;
int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	
	cin >> N >> M;

	for (int i = 0;i < N + M;i++)
	{
		string str;
		cin >> str;

		if (Map.find(str) != Map.end())
		{
			cnt++;
			list.push(str);
		}
		else
		{
			Map.insert(str);
		}
	}

	cout << cnt << "\n";
	while(!list.empty())
	{
		cout << list.top() << "\n";
		list.pop();
	}

	cout << endl;
}
