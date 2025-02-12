#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#define init cin.tie(0)->ios_base::sync_with_stdio(0); 

using namespace std;
const int MIN = -2e5 - 5;

int N, M;

int arr[1002][1002];
int dp1[1002][1002];
int dp2[1002][1002];



int main()
{
    init; 
    cin >> N >> M;
    
    int h;
    for (int i = 1;i <= N;i++)
    {
        for (int j = 1;j<= M;j++)
        {
			cin >> h;           
			arr[i][j] = h;     
        }
    }

    fill(&dp1[0][0], &dp1[N + 1][M + 1], MIN);
    fill(&dp2[0][0], &dp2[N + 1][M + 1], MIN);
    dp1[0][1] = 0;    
    dp1[1][0] = 0;    
    dp2[0][1] = 0;    
    dp2[1][0] = 0;

	for (int j = 1; j < M + 1; j++) 
	{        
		dp1[1][j] = dp1[1][j - 1] + arr[1][j];
		dp2[1][j] = dp2[1][j - 1] + arr[1][j];   
	}     
    for (int i = 2;i <= N;i++)
    {
        for (int j = 1;j <= M;j++)
        {
            dp1[i][j] = max(dp1[i - 1][j], dp1[i][j - 1]) + arr[i][j];
        }
        for (int j = M;j >0;j--)
        {
            dp2[i][j] = max(dp2[i - 1][j], dp2[i][j + 1]) + arr[i][j];
        }

        for (int j = 1;j <= M;j++)
        {
            dp1[i][j] = max(dp1[i][j], dp2[i][j]);
            dp2[i][j] = dp1[i][j];
        }
    }

    cout << dp1[N][M];

    return 0;
}
