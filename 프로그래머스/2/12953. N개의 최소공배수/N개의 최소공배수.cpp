#include <string>
#include <vector>
#include <queue>

using namespace std;

long long getGCD(long long a, long long b) {
    while (b != 0) {
        long long temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}

int solution(vector<int> arr) {
    queue<long long> q;
    for(int i = 0; i < arr.size(); i++) {
        q.push(arr[i]); 
    }

    while(q.size() > 1) {
        long long first = q.front(); q.pop();
        long long second = q.front(); q.pop();

        long long GCD = getGCD(first, second);
        q.push(first * second / GCD);  // LCM
    }

    return q.front();
}
