#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
    vector<int> answer;
    if(s<n) answer.push_back(-1);
    else
    {
        int val = s/n;
        int left = s%n;
        
        for(int i=0;i<n-left;i++)
        {
            answer.push_back(val);
        }
        for(int i=n-left;i<n;i++)
        {
            answer.push_back(val+1);
        }
    }
    
    return answer;
}