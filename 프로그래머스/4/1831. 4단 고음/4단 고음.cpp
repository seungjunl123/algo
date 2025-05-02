// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
    int answer = 0;

int cntThree(int k) {
    int num = k;
    int ret = 0;
    while (num / 3 != 0) {
        num /= 3;
        ret++;
    }

    return ret;
}


void GoTo_1(int n, int MulCount, int AddCount)
{

    if(n == 1)
    {
        answer += 1;
        return;
    }
    
    if(n%3==0 && (MulCount+1)*2 <=AddCount ) {
        GoTo_1(n/3, MulCount+1, AddCount);
    }
    if((MulCount+cntThree(n))*2 >=AddCount+1)
        GoTo_1(n-1, MulCount, AddCount+1);
}

int solution(int n) {
    answer = 0;
    GoTo_1(n, 0, 0);
    return answer;
}
