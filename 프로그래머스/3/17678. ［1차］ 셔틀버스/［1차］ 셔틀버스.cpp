#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

string makeTimeTable(int t)
{
    int hour = t/100;
    int min = t%100;
    
    string h = hour < 10 ? "0"+to_string(hour) : to_string(hour);
    string m = min < 10 ? "0"+to_string(min) : to_string(min);
    
    return h+":"+m;
}

int adjustTime(int t)
{
    int h = t/100;
    int m = t%100;
    if(m >=60)
    {
        h++;
        m %= 60;
    }
    return h*100 + m;
}


int DownTime(int t)
{
    int h = t/100;
    int m = t%100;
    
    if(m - 1 < 0)
    {
        h--;
        m = 59;
        return h*100 + m;
    }
    
    else return h*100 + m-1;
}
string solution(int n, int t, int m, vector<string> timetable) {
    string answer = "";
    int startTime = 900;
    int lastTime = 0;
    
    priority_queue<int, vector<int>, greater<int>> crewQueue;
    for(string s : timetable)
    {
        int hour = stoi(s.substr(0,2));
        int min =  stoi(s.substr(3,2));
        
        crewQueue.push(hour*100+min);
        
        // 시간을 60으로 만들기 추가하면 정답일듯ㄱ
    }
    
    int leftShuttle = n;
    while(leftShuttle != 0)
    {
        int seatLeft = m;
        while(!crewQueue.empty() && seatLeft != 0)
        {
            if(crewQueue.top() > startTime) break;
            
            // n == 1이고, seatLeft == 1이어야 살 수 있다.
            if(seatLeft == 1 && seatLeft-1 == 0)
            {
                cout << crewQueue.top() << endl;
                answer = makeTimeTable(DownTime(crewQueue.top()));
                
            }
            crewQueue.pop();
            seatLeft--;
            
        }
        // crewQueue가 비었으면 마지막 셔틀의 출발 시간을 정하면 된다.
        if(seatLeft > 0)
        {
            answer = makeTimeTable(startTime);
        }
        
        
        startTime = adjustTime(startTime+t);
        leftShuttle--;
    }
    
    
    
    return answer;
} 