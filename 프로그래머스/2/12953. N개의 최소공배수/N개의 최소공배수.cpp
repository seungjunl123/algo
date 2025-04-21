#include <string>
#include <vector>
#include <queue>

using namespace std;

// 최대공약수 함수 (유클리드 호제법)
long long gcd(long long a, long long b) {
    while (b != 0) {
        long long temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}

// 최소공배수 함수
long long lcm(long long a, long long b) {
    return a * b / gcd(a, b);
}

int solution(vector<int> arr) {
    long long answer = arr[0];
    
    for (int i = 1; i < arr.size(); i++) {
        answer = lcm(answer, arr[i]);
    }
    
    return answer;
}
