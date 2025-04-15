#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    
    queue<pair<int, int>> list; // val, idx
    int index = 0;
    for(int i=0;i<priorities.size();i++)
    {
        list.push(make_pair(priorities[i],i));
    }
    sort(priorities.begin(), priorities.end(), greater<>());
    // for(int N : priorities) cout << N << ' ' << endl;
    
    // val이 priorities의 idx보다 작으면 queue의 맨 뒤로 이동
    while(true)
    {
        auto& curNode = list.front();
        list.pop();
        // cout << curNode.first << '!' << endl;
        if(curNode.first == priorities[index])
        {
            // cout << curNode.first << "!!!!" << endl;
            if(curNode.second == location)
            {
                answer = index + 1;
                break;
            }
            index++;
        } 
        else
        {
            list.push(curNode);
        }

    }
    
    return answer;
}