int answer;

int cntThree(int k){
    int num = k;
    int ret = 0;
    while(num/3 != 0){
        num/=3;
        ret++;
    }

    return ret;
}

void dfs(int n, int cntMultiple, int cntPlus){

    if(n==1){
        answer++;
        return;
    }

    if(n%3==0){
        if ((cntMultiple+1) *2 <=  cntPlus){
            dfs(n/3, cntMultiple+1, cntPlus);
        }
    }

    if( (cntMultiple+cntThree(n))*2 >= cntPlus+1){
        dfs(n-1, cntMultiple, cntPlus+1);
    }

    return;
}

int solution(int n) {
    answer =0;
    dfs(n, 0, 0);
    return answer;
}