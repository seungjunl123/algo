#include <iostream>
#include <vector>
#include <queue>
#include <string>
#include <algorithm>
using namespace std;

int N, K;
string str; // 50만자리 수;
int Count = 0;
int MaxIdx = 0;

int MaxVal = -1;
int IsExcuted[500001];

// 시작할 때부터 카운트가 1씩 증가
// Max값이 같거나 클때마다 카운트와 K를 비교
// 카운트가 K보다 작거나 같으면 업데이트된 Max값 이전 값을 모두 없앰

// 카운트가 K보다 크다면, 마지막 Max부터 현재 Max-1까지의 값을 부름
// 그 수 중 작고, 왼쪽(큰 자리수)에 있는 수부터 삭제

// 만약 마지막 1의 자리수까지 탐색 후에도 K가 0보다 크다면 Max가 유지되고 있다는 뜻이므로
// N-1부터 N-K까지 삭제


int main() {

cout<< "198" << endl;
cout<< "osj1644"<< endl;
}
