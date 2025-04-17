#include <string>
#include <vector>

using namespace std;

// 숫자를 주어진 진법으로 변환
string Convert(int num, int base) {
    string nums = "0123456789ABCDEF";
    string result = "";
    if (num == 0) return "0";
    while (num > 0) {
        result = nums[num % base] + result;
        num /= base;
    }
    return result;
}

string solution(int n, int t, int m, int p) {
    string answer = "";
    string game = "";
    
    int num = 0;
    // 필요한 길이만큼 문자열 미리 생성
    while (game.length() < t * m) {
        game += Convert(num++, n);
    }

    // 튜브가 말해야 할 문자들만 골라서 추가
    for (int i = 0; i < t; i++) {
        answer += game[(i * m) + (p - 1)];
    }
    
    return answer;
}
