#include <algorithm>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

int M, a;
int arr[21];

int main() 
{    
   	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
    
	cin >> M;
	for (int i = 0;i < M;i++)
	{
		string str;
		cin >> str;
		if (str == "add")
		{
			cin >> a;

			arr[a] = 1;
		} 
		else if (str == "remove")
		{
			cin >> a;
			arr[a] = 0;
		}
		else if (str == "check")
		{
			cin >> a;
			cout << arr[a] << "\n";
		}
		else if (str == "toggle")
		{
			cin >> a;
			arr[a] = (arr[a] == 1) ? 0 : 1;
		}
		else  if (str == "all")
		{
			for (int j = 1;j < 21;j++)
			{
				arr[j] = 1;
			}
		}
		else if (str == "empty")
		{
			for (int j = 1;j < 21;j++)
			{
				arr[j] = 0;
			}
		}
	}
    cout<< endl;
}
