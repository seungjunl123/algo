#include <string>
#include <vector>
#include <list>
#include <stdlib.h>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer;

    int min, max;
    list<int> v;

    for (int i = 0; i < operations.size(); i++){
        if (operations[i][0] == 'I')
            v.push_back(atoi(operations[i].substr(2, operations.size()).c_str()));

        if (operations[i][0] == 'D'){
            if (operations[i][2] == '1'){
                max = -10000000;
                for (auto l : v)
                    if (max < l)
                        max = l;

                v.remove(max);
            }
            else{
                min = 10000000;
                for (auto l : v)
                    if (min > l)
                        min = l;

                v.remove(min);
            }
        }
    }

    if (v.size() == 0){
        answer.push_back(0);
        answer.push_back(0);
    }
    else{
        max = -1;
        for (auto l : v)
            if (max < l)
                max = l;

        min = 10000000;
        for (auto l : v)
            if (min > l)
                min = l;

        answer.push_back(max);
        answer.push_back(min);
    }

    return answer;
}