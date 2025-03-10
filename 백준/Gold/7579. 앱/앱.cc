#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int N, M;
	cin >> N >> M;
	vector<pair<int, int>> arr(N);
	vector<int> sum(N);

	for (int i = 0; i < N; i++) {
		cin >> arr[i].first;
	}

	for (int i = 0; i < N; i++) {
		cin >> arr[i].second;
		sum[i] = arr[i].second;
		if (i > 0)
			sum[i] += sum[i - 1];
	}

	// 테이블 정의
	// dp[n][c] : n번 앱을 마지막으로 끌지 말지 결정 했을 때, c 이하의 비용을 사용해서 확보한 메모리의 최댓값
	// 	//
	// 점화식
	// dp[n][c] = dp[n-1][c - app[n].second] + app[n].first <- n번째 앱을 끄는 경우
	// dp[n][c] = dp[n-1][c] <- n번째 앱을 끄지 않는 경우

	vector<vector<int>> dp(N, vector<int>(sum[N - 1] + 1, 0));

	for (int c = arr[0].second; c <= sum[N - 1]; c++)
		dp[0][c] = arr[0].first;

	int answer = 0;

	for (int n = 1; n < N; n++) {
		for (int c = 0; c <= sum[N - 1]; c++) {
			if (c < arr[n].second) // c가 app.second보다 작으면 n번째 앱은 못 끈다.
			{
				dp[n][c] = dp[n - 1][c];
			}
			else
			{
				dp[n][c] = max(dp[n - 1][c - arr[n].second] + arr[n].first, dp[n - 1][c]);
			}

		}
	}

	for (int c = 0; c <= sum[N - 1]; c++) {
		if (dp[N - 1][c] >= M)
		{
			answer = c;
			break;
		}
	}

	cout << answer;
}