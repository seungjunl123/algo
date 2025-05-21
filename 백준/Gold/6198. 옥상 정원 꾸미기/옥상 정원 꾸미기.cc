#include <iostream>
#include <vector>
#include <stack>
#include <string>
#include <algorithm>
using namespace std;

int N;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    vector<int> list(N, 0);
    stack<int> s;

    for (int i = 0;i < N;i++)
    {
        cin >> list[i];
    }

    long long count = 0;

    for (int i = 0;i < N;i++)
    {
        while (true)
        {
            if (s.empty())
            {
                s.push(list[i]);
                break;
            }
            if (list[i] < s.top())
            {
                count += s.size();
                s.push(list[i]);
                break;
            }
            s.pop();
        }
    }

    cout << count;

    return 0;
}