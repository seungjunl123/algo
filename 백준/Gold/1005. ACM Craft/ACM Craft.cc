#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int T,N, K, Target;
int X, Y;
long long Time[1001];
long long TimeTaken[1001];
long long answer;

// 역으로 도착 지점에서 각 지점마다 걸리는 시간 중 최대값을 받으면 되잖아.
vector<int> Reverse[1001];

 
long long CalculateTime(int num)
{
	long long maxValue = 0;
	if (Reverse[num].size() == 0)
	{
		maxValue =Time[num];
	}
	else
	{
		for (int child : Reverse[num])
		{
			if (TimeTaken[child]==-1) CalculateTime(child);
			if (maxValue < TimeTaken[child] + Time[num])
			{
				maxValue = TimeTaken[child] + Time[num];
			}
		}
	}
	return TimeTaken[num]= maxValue;
}

int main() 
{    
	cin >> T;
	for (int t = 0;t < T;t++)
	{
		fill(&TimeTaken[0], &TimeTaken[1000], -1);
		fill(&Time[0], &Time[1000], 0);
		
		cin >> N >> K;
		for (int i = 1;i <= N;i++)
		{
			Reverse[i].clear();
			cin >> Time[i];
		}
		for (int i = 0;i < K;i++)
		{
			cin >> X >> Y;
			Reverse[Y].push_back(X);
		}
		cin >> Target;
		answer = CalculateTime(Target);

		cout << answer << "\n";
	}
}
