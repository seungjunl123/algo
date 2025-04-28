#include <string>
#include <vector>
#include <iostream>

using namespace std;

bool checked[3];
long long answer = 0;

void findOrder(int count, string exp, vector<string> link)
{
    
    for(int i=0;i< link.size();i++)
    {
        if(link[i] == exp)
        {
            long long tmp1 = stoll(link[i-1]);
            long long tmp2 = stoll(link[i+1]);
            link[i-1] = exp == "+" ? to_string(tmp1 + tmp2) : exp =="-" ? to_string(tmp1 - tmp2) : to_string(tmp1 * tmp2);
            link.erase(link.begin() + i, link.begin() + i + 2);
            i -= 2;
        }
    }

    
    // 3번째 처리 시 모든 값이 처리되었으므로
    if(count == 3)
    {
        if(abs(stoll(link[0])) > answer) answer = abs(stoll(link[0]));
        return;
    }
    
    // string 처리하기
    
    if(!checked[0])
    {
        checked[0] = true;
        findOrder(count + 1, "+", link);
        checked[0] = false;
    }
    if(!checked[1])
    {
        checked[1] = true;
        findOrder(count + 1, "-", link);
        checked[1] = false;
    }    
    if(!checked[2])
    {
        checked[2] = true;
        findOrder(count + 1, "*", link);
        checked[2] = false;
    }
}

long long solution(string expression) {
    // 계속 String을 int로 수정하고, 연산자가 나오면 다음 숫자 찾아서 처리하고 string으로 넣기
    // 연산자가 맞는 값이 아니면 그냥 다음 숫자까지 string으로 넣기
    vector<string> link;
    
    string tmpInput;
    for(int i=0;i<expression.size();i++)
    {
        if(expression[i] == '+'|| expression[i] == '-' || expression[i]=='*')
        {
            link.push_back(tmpInput);
            tmpInput = "";
            string exp(1, expression[i]);
            link.push_back(exp);
        }
        else
        {
            tmpInput += expression[i];
        }
    }
    link.push_back(tmpInput);

    
    // 1. + , 2. - , 3.*
    for(int i=0;i<3;i++)
    {
        checked[i] = false;
    }
    
    findOrder(0, "", link);
    
    return answer;
}