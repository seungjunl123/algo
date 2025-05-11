#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;


int N;
vector<vector<int>> workList(10001, vector<int>(101, -1));
vector<int> workTime(10001, -1);
int main() {
    
    cin >> N;
    for(int k=1;k<=N;k++)
    {
        int time;
        int prevWork;
        cin >> time >> prevWork;
        workTime[k] = time;

        if (prevWork)
        {
            for (int i = 0;i < prevWork;i++)
            {
                int next;
                cin >> next;
                workList[k][i] = next;
            }
        }
    }

    for (int i = 1;i <= N;i++)
    {
        int idx = 0;
        int lastestEndWorkTime = 0;
        while (workList[i][idx] > -1 && idx < 10001)
        {
            lastestEndWorkTime = max(workTime[workList[i][idx]], lastestEndWorkTime);
            idx++;
        }
        workTime[i] += lastestEndWorkTime;
        //cout << i << "번 : " << workTime[i] << endl;
    }

    int answer = 0;
    for (int i = 1;i <= N;i++)
    {
        answer = max(workTime[i], answer);
    }

    cout << answer <<endl;
    return 0;
}