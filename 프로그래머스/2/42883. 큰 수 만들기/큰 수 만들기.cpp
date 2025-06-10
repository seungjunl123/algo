#include <string>
#include <vector>
#include <stack>

using namespace std;

string solution(string number, int k) {
    string answer = "";
    
    stack<char> s;
    int left = k;
    
    for(char c : number)
    {
        while(!s.empty() && s.top() < c && left!=0)
        {
            s.pop();
            left--;
        }
        s.push(c);
    }
    
    while(left>0)
    {
        s.pop();
        left--;
    }
    while(!s.empty())
    {
        answer = s.top()+answer;
        s.pop();
    }   
    
    return answer;
}