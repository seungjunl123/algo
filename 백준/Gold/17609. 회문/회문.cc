#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

int n;

int isPalindromeRight(string str)
{
	int count = 0;
	int left = 0;
	int right = str.length() - 1;
	bool flag = false;

	while (left <= right)
	{
		if (str[left] != str[right])
		{
			if (++count == 2) break;
			right--;
			continue;
		}
		else
		{
			flag = true;
		}

		left++;
		right--;
	}

	return flag ? count : 2;
}
int isPalindromeLeft(string str)
{
	int count = 0;
	int left = 0;
	int right = str.length() - 1;
	bool flag = false;

	while (left <= right)
	{
		if (str[left] != str[right])
		{
			if (++count == 2) break;
			left++;
			continue;
		}
		else
		{
			flag = true;
		}

		left++;
		right--;
	}

	return flag ? count : 2;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	cin >> n;

	for (int i = 0;i < n;i++)
	{
		string str;
		cin >> str;
		int L = isPalindromeLeft(str);
		int R = isPalindromeRight(str);
		int answer = min(R, L);
		cout << answer << "\n";
	}
	cout << endl;

}
