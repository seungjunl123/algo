#include <string>
#include <vector>
#define ll long long
using namespace std;

long long min(long long a, long long b)
{
    return a < b ? a : b;
}

long long max(long long a, long long b)
{
    return a > b ? a : b;
}

ll solution(int a, int b, vector<int> g, vector<int> s, vector<int> w, vector<int> t)
{
    ll left = 0, right = 1000000000000000LL;
    int n = g.size();

    while (left < right)
    {
        ll mid = (left + right) / 2;
        ll movedGold = 0, movedSilver = 0, sum = 0;
        for (int i = 0; i < n; i++)
        {
            ll movedResource = (mid / t[i] + 1) / 2 * w[i];
            ll movingGold = min(movedResource, g[i]);
            
            movedGold += movingGold;
            movedResource -= movingGold;
            
            ll movingSilver = min(movedResource, s[i]);
            movedSilver += movingSilver;
            ll curSilver=s[i] - movingSilver;
            sum += min(curSilver, movingGold);
        }
        ll leftSilver = max(b - movedSilver, 0);
        ll addSilver = min(sum, leftSilver);
        movedSilver += addSilver;
        movedGold -= addSilver;
        if (movedGold >= a && movedSilver >= b)
            right = mid;
        else
            left = mid + 1;
    }
    return left;
}