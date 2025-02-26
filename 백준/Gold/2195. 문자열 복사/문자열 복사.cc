#include <iostream>
#include <queue>
#include <string>
#define ll long long
using namespace std;

string S, P;
vector<int> alpha[123];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> S >> P;

	int Ssize = S.length();
	int Psize = P.length();
	int answer = 0;

	for (int i = 0;i < Ssize;i++)
	{
		alpha[S[i] - 0].push_back(i);
	}

	int idx = 0;
	while (idx != Psize)
	{
		char c = P[idx];

		int count = 0;
		for (int point : alpha[c - 0])
		{
			int tmp = 0;
			for (int i = point;i < Ssize;i++)
			{
				if (S[i] == P[idx + i - point])
				{
					tmp++;
				}
				else
				{
					break;
				}
			}
			if (tmp > count) count = tmp;
		}
		idx += count;
		answer++;
	}

	//cout << 'z' - 0 << endl; // 97 ~ 122
	//cout << '9' - 0 << endl; // 48 ~ 57

	cout << answer << endl;
}
