#include <iostream>
#include <vector>
#include <queue>
#include <string>
#include <algorithm>
using namespace std;

struct Node
{
	int stMonth;
	int stDay;
	int edMonth;
	int edDay;

	Node(int a, int b, int c, int d)
		: stMonth(a), stDay(b), edMonth(c), edDay(d)
	{}
};

struct CompareTotal
{
	bool operator()(const Node& a, const Node& b)
	{
		if (a.stMonth != b.stMonth)
		{
			return a.stMonth > b.stMonth;
		}
		return a.stDay > b.stDay;
	}
};

struct CompareCount
{
	bool operator()(const Node& a, const Node& b)
	{
		if (a.edMonth != b.edMonth)
		{
			return a.edMonth < b.edMonth;
		}
		return a.edDay < b.edDay;
	}
};

// 전체 pq는 출발 값이 낮은 지점부터
// 탐색 pq는 도착 값이 늦은 뒤부터

// 출발 지점(맨 처음은 3/1)보다 작은 날짜를 탐색 pq에 넣는다.
// 그 중 가장 도착값이 늦은 값을 꺼내고, 나머지는 버린다. 카운트를 1추가

// 그 값의 출발점보다 작은 날짜를 탐색 pq에 넣는다.
// 이렇게 도착지점이 11/30보다 같거나 늦는 꽃을 찾으면 탐색 종료, 카운트 출력

int N, Count = 0;
int a, b, c, d;
int sM = 3;
int sD = 1;
int eM = 11;
int eD = 30;

priority_queue<Node, vector<Node>, CompareTotal> Total;
priority_queue<Node, vector<Node>, CompareCount> Select;

bool countFlower()
{
	int lastEdM = 3, lastEdD = 1;

	while (!Total.empty())
	{
		// 현재 시점에서 피어날 수 있는 꽃들을 Select에 추가
		while (!Total.empty() &&
			(Total.top().stMonth < sM ||
				(Total.top().stMonth == sM && Total.top().stDay <= sD)))
		{
			Select.push(Total.top());
			Total.pop();
		}

		if (Select.empty())
			return false;  // 더 이상 선택할 수 있는 꽃이 없는 경우

		Node bestFlower = Select.top();
		Select.pop();

		Count++;
		sM = bestFlower.edMonth;
		sD = bestFlower.edDay;

		if (sM > eM || (sM == eM && sD > eD))
			return true;  // 목표 날짜를 초과하면 종료
	}

	return false;
}


int main() {

	cin >> N;
	
	for (int i = 0;i < N;i++)
	{
		cin >> a >> b >> c >> d;
		Total.push(Node(a, b, c, d));
	}

	if (countFlower())
	{
		cout << Count << endl;
	}
	else
	{
		cout << 0 << endl;
	}

}
