#include <string>
#include <iostream>
#include <vector>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    vector<vector<int>> list(501, vector<int>(0));
    vector<int> check(100001, -1);
    int tmp;
    string tmps = "";
    vector<int> tmpList;
    for(char c : s)
    {
        if(c == '{')
        {
            tmps = "";
            tmpList.clear();
        }
        else if( c == ',')
        {
            if(tmps != "")
            {
                tmp = stoi(tmps);
                tmpList.push_back(tmp);
                tmps = "";
            }
        }
        else if(c == '}')
        {
            if(tmps != "")
            {
                tmp = stoi(tmps);
                tmps = "";
                tmpList.push_back(tmp);   
            }
            list[tmpList.size()] = tmpList;
        
            
        }
        else
        {
            tmps += c;
        }
    }
    
    for(int i=1;i<=500;i++)
    {
        if(list[i].size() == 0) break;
        for(int j=0;j<i;j++)
        {
            if(check[list[i][j]] == -1)
            {
                answer.push_back(list[i][j]);  
                check[list[i][j]] = 0;
            }
        }
    }
    return answer;
}