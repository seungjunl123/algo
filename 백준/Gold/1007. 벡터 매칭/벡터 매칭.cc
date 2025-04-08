#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
#include <cmath>
#include <iomanip>
using namespace std;

int T, N;
vector<pair<int, int>> coordinates;
vector<pair<int, int>> vectors;
vector<bool> checked;
double ans;

double Calculate()
{
	pair<long long, long long> temp;
	for (int i = 0; i < N; ++i) {
		if (checked[i]) {
			temp.first -= coordinates[i].first;
			temp.second -= coordinates[i].second;
		}
		else {
			temp.first += coordinates[i].first;
			temp.second += coordinates[i].second;
		}
	}
	return sqrt(temp.first* temp.first + temp.second* temp.second);
}

void Comb(int idx, int count)
{
	if (count == N / 2)
	{
		double cur = Calculate();
		if (ans > cur)
			ans = cur;
		return;
	}

	for (int i = idx;i < N;i++)
	{
		if (checked[i]) continue;
		checked[i] = true;
		Comb(i+1, count + 1);
		checked[i] = false;
	}

}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	while (T--)
	{
		cin >> N;

		 ans = numeric_limits<double>::max();
		coordinates.resize(N);
		checked.resize(N, false);

		for (int i = 0;i < N;i++)
		{
			cin >> coordinates[i].first >> coordinates[i].second;
		}

		checked[0] = true;
		Comb(1, 1);

		cout << fixed << setprecision(12) << ans << '\n';
	}

}
