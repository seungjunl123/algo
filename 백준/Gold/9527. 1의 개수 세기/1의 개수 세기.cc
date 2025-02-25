#include <iostream>
#include <queue>
#define ll long long
using namespace std;

ll arr[55];
ll A, B;

ll count1(ll num)
{
	ll ans = num & (1ll << 0); // 0번 인덱스 먼저 처리

	for (int i = 54;i > 0;i--)
	{
		if (num & (1ll << i))
		{
			ans += arr[i - 1] + (num - (1ll << i) + 1);
			num -= 1ll << i;
		}
	}

	return ans;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	arr[0] = 1;
	for (int i = 1;i < 55;i++)
	{
		arr[i] = (arr[i - 1] * 2) + (1ll << i);
	}

	cin >> A >> B;

	cout << count1(B) - count1(A-1) << endl;

}
