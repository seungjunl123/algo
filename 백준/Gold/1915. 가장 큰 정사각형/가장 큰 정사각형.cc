#include <iostream>
#include <vector>
#include <queue>
#include <string>
#define INF 987654321
using namespace std;


struct Node
{
	int r;
	int c;
	Node(int a, int b) : r(a), c(b) {}
};
vector < vector<int> > map;

vector<vector<Node>> checked;
int n, m;
int length = 0;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;
	map.resize(n+1, vector<int>(m+1, 0));
	checked.resize(n+1, vector<Node>(m+1, Node(0,0)));

	for (int i = 1;i <= n;i++)
	{
		string str;
		cin >> str;
		for (int j = 0;j < m;j++)
		{
			map[i][j+1] = str[j] - 48;
		}
	}

	// 이어지는 1을 저장
	for (int i = 1;i <= n;i++)
	{
		for (int j = 1;j <= m;j++)
		{
			if (map[i][j] == 0) {
				checked[i][j].r = 0;
				checked[i][j].c = 0;
			}
			else
			{
				checked[i][j].r = min(checked[i-1][j-1].r +1, checked[i-1][j].r + 1);
				checked[i][j].c = min(checked[i-1][j-1].c + 1, checked[i][j - 1].c + 1);
			}

			if (length < min(checked[i][j].r, checked[i][j].c))
			{
				length = min(checked[i][j].r, checked[i][j].c);
			}
		}
	}

	 


	cout << length * length << endl;
}
