#include <iostream>
#include <map>
#include <vector>


using namespace std;
int N;
long long B;
vector<vector<int>> originMap;
map<long long, vector<vector<int>>> maps;
vector<vector<int>> conquer(long long Num);
vector<vector<int>> sub(long long A, long long B);

vector<vector<int>> conquer(long long Num)
{
	if (maps.find(Num) != maps.end())
	{
		return maps[Num];
	}
	return sub(Num / 2l, Num % 2l == 1l ? Num / 2l + 1l : Num / 2);
}

vector<vector<int>> sub(long long A, long long B)
{
	vector<vector<int>> tmp1 = conquer(A);
	vector<vector<int>> tmp2 = conquer(B);
	vector<vector<int>> response;

	for (int i = 0;i < N;i++)
	{
		response.push_back({});
		for (int j = 0;j < N;j++)
		{
			int sum = 0;
			for (int k = 0;k < N;k++)
			{
				sum += tmp1[i][k] * tmp2[k][j];
			}
			response[i].push_back(sum % 1000);
		}
	}
	maps.insert({ A+B, response });
	return response;
}

int main(void)
{
	cin >> N >> B;
	

	for (int i = 0;i < N;i++)
	{
		originMap.push_back({});
		int num;
		for (int j = 0;j < N;j++)
		{
			cin >> num;
			originMap[i].push_back(num%1000);
		}
	}

	maps.insert({1, originMap});
	vector<vector<int>> answer = conquer(B);
		
	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < N;j++)
		{
			cout << answer[i][j] << " ";
		}
		cout << endl;
	}

	return 0;
}