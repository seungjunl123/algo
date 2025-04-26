#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <unordered_map>
using namespace std;

int N,M;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	unordered_map<string, string> list;

	for (int i = 0;i < N;i++)
	{
		string site;
		string pw;
		cin >> site >> pw;
		list.emplace(site, pw);
	}

	for (int i = 0;i < M;i++)
	{
		string site; 
		cin >> site;
		cout << list[site] << endl;
	}
}
