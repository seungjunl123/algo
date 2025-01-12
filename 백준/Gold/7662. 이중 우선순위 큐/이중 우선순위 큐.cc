#include <iostream>
#include <queue>
#include <map>
using namespace std;

int T, K;
char Q;
int num, minVal, maxVal;
int dCount = 0, iCount=0;

// 작은 거 큰거 설정
// pq는 계속 넣고
// 작은 거 제거는 작은쪽에서, 큰거 제거는 큰쪽에서
// D보다 I가 많이 나왔으면 Empty

int main(void)
{	
	cin >> T;
	for (int t = 0;t < T;t++)
	{
		priority_queue<long, vector<long>, greater<long>> pq1;
		priority_queue<long> pq2;
		dCount = 0;iCount = 0;minVal = 0;maxVal = 0;
		map<long, long> isLeft;

		cin >> K;
		for (int k = 0;k < K;k++)
		{
			cin >> Q >> num;
			if (Q == 'I')
			{
				pq1.push(num);
				pq2.push(num);
				isLeft[num]++;
				iCount++;
			}
			else
			{
				if (dCount < iCount) {

					if (num == 1)
					{
						if (!pq2.empty()) {
							isLeft[pq2.top()]--;
							pq2.pop();

							while (!pq1.empty() && isLeft[pq1.top()] == 0) {
								pq1.pop();
							}
							while (!pq2.empty() && isLeft[pq2.top()] == 0) {
								pq2.pop();
							}

						}
					}
					else
					{
						// -1일 경우 poll
						if (!pq1.empty())
						{
							isLeft[pq1.top()]--;
							pq1.pop();	

							while (!pq1.empty() && isLeft[pq1.top()] == 0) {
								pq1.pop();
							}
							while (!pq2.empty() && isLeft[pq2.top()] == 0) {
								pq2.pop();
							}
						}
					}
					dCount++;
				}

			}
		}
		if (pq1.empty() || pq2.empty())
			cout << "EMPTY"<<endl;
		else
		{
			while (!pq2.empty() &&isLeft[pq2.top()] == 0)
			{
				pq2.pop();
			}
			while (!pq1.empty() && isLeft[pq1.top()] == 0)
			{
				pq1.pop();
			}
			cout << pq2.top() << " " << pq1.top() << endl;
		}
	}

	return 0;
}