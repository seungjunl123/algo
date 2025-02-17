#include <algorithm>
#include <iostream>
#include <vector>
#include <string>
#include <queue>

using namespace std;

int n;
int k;
priority_queue<int> pq1;
priority_queue<int> pq2;

int main() 
{    
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	cin >> n;
	cin >> k;


	int sum = 0;

	int maxVal = -1;
	int minVal = 1000001;
	for (int i = 0;i < n;i++)
	{
		int a;
		cin >> a;
		if (a > maxVal) maxVal = a;
		if (a < minVal)  minVal = a;
		pq1.push(-a);
	}
	int prev = minVal;
	for (int i = 0;i < n;i++)
	{
		int val = -pq1.top();
		pq1.pop();
		pq2.push(val - prev);
		prev = val;
		
	}

	sum = maxVal - minVal;

	for (int i = 0;i < k - 1;i++)
	{
		sum -= pq2.top();
		pq2.pop();
	}

	cout << sum;

}
