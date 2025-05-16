#include <string>
#include <vector>

using namespace std;

long long min(long long a, long long b)
{
    return a < b ? a : b;
}

long long max(long long a, long long b)
{
    return a > b ? a : b;
}

long long solution(int a, int b, vector<int> g, vector<int> s, vector<int> w, vector<int> t)
{
    long long l = 0, r = 1e15, m;
    int n = g.size();

    while (l < r)
    {
        m = (l + r) / 2;
        long long moved_g = 0, moved_s = 0, C = 0;
        for (int i = 0; i < n; i++)
        {
            long long P = (m / t[i] + 1) / 2 * w[i];
            long long P_g = min(P, g[i]);
            moved_g += P_g, P -= P_g;
            long long P_s = min(P, s[i]);
            moved_s += P_s;
            long long now_s=s[i] - P_s;
            C += min(now_s, P_g);
        }
        long long need_s = max(b - moved_s, 0);
        long long add_s = min(C, need_s);
        moved_s += add_s;
        moved_g -= add_s;
        if (moved_g >= a && moved_s >= b)
            r = m;
        else
            l = m + 1;
    }
    return l;
}