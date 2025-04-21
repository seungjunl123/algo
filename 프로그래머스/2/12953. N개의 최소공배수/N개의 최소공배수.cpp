#include <string>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

long long solution(vector<int> arr) {
    queue<long long> q;
    for (int i = 0; i < arr.size(); i++) {
        q.push(arr[i]);
    }

    while (q.size() > 1) {
        long long first = q.front(); q.pop();
        long long second = q.front(); q.pop();

        long long GCD = 0;
        for (int i = 1; i <= min(first,second); i++) {
            if (first % i == 0 && second % i == 0) GCD = i;
        }
        q.push(first * second / GCD);
    }

    return q.front();  // long long으로!
}

