#include <iostream>
#include <vector>
#include <queue>
#include <string>
#include <stack>
#include <algorithm>
using namespace std;

struct Node
{
	int Index;
	int Height;
	Node(int a, int b) : Index(a), Height(b) {}
};
int N;
stack<Node> st;

// 인덱스와 높이를 가지고 넣어.
// 스택의 탑이 자기보다 높은지 확인, 낮으면 스택에서 빼버림
// 자기보다 높은 값이 존재하거나 스택의 크기가 0이면 넣음


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> N;

	for (int i = 1;i <= N;i++)
	{
		int tower;
		cin >> tower;

		while (!st.empty() && st.top().Height < tower)
		{
			st.pop();
		}

		if (st.empty())
		{
			cout << 0 << ' ';
		}
		else
		{
			cout << st.top().Index << " ";
		}

		st.push(Node(i, tower));
	}
}
