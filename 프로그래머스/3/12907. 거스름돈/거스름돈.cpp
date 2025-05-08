#include <string>
#include <vector>

using namespace std; 

int solution(int n, vector<int> money) {
    int answer = 0;
    
	vector<int> dp(n+1);
		
	dp[0] = 1;

		
	for(int i=0;i<money.size();i++) {
		for(int j=1;j<=n;j++) {
			if(j>=money[i])	dp[j] += dp[j-money[i]];
		}
	}
    
    return dp[n]%1000000007;
}