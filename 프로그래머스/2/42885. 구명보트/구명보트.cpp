#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<int> people, int limit) { 
    sort(people.begin(), people.end());
    int answer = 0;
    int left = 0;
    int right = people.size()-1;
    
    while(left<=right)
    {
        // 가장 무거운 사람이 limit보다 크면 그냥 패스
        if(people[right]>limit) 
        {
            right--;
            continue;
        }
        
        // 그렇지 않다면 가장 무거운 사람과 가장 가벼운 사람을 비교
        else if(people[right]+people[left] > limit)
        {
            answer++;
            right--;
        }
        
        else 
        {
            answer++;
            right--;
            left++;
        }
        
        if(left==right)
        {
            answer++;
            right--;
        }
        
    }
    return answer;
}