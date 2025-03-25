#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

int N;
vector<vector<long long>> list;

vector<long long> merged1;
vector<long long> merged2;

long long answer = 0;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	list.resize(4, vector<long long>(N, 0));

	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < 4;j++)
		{
			cin >> list[j][i];
		}
	}

	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < N;j++)
		{
			merged1.push_back(list[0][i] + list[1][j]);
			merged2.push_back(list[2][i] + list[3][j]);
		}
	}

	sort(merged1.begin(), merged1.end());
	sort(merged2.begin(), merged2.end());

	for (int i = 0;i < merged1.size();i++)
	{


		if (lower_bound(merged2.begin(), merged2.end(), -merged1[i])
			!= upper_bound(merged2.begin(), merged2.end(), -merged1[i]))
		{
			answer += upper_bound(merged2.begin(), merged2.end(), -merged1[i]) - lower_bound(merged2.begin(), merged2.end(), -merged1[i]);
		}
	}

	cout << answer << endl;
}

