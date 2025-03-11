#include <string>
#include <vector>
#include <queue>
#include <unordered_map>

using namespace std;

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;

    int BaseTime = fees[0];
    int BaseFee = fees[1];
    int AddedTime = fees[2];
    int AddedFee = fees[3];

    unordered_map<int, bool> IsCarInParkingLot;
    unordered_map<int, int> ParkingFee;

    priority_queue<int, vector<int>, greater<int>> list;

    for (string str : records)
    {
        int InHour = stoi(str.substr(0, 2));
        int InMin = stoi(str.substr(3, 2));
        int CarNumber = stoi(str.substr(6, 4));


        if (IsCarInParkingLot.find(CarNumber) == IsCarInParkingLot.end())
        {
            list.push(CarNumber);
            IsCarInParkingLot.emplace(CarNumber,  true);
            ParkingFee.emplace(CarNumber, 0);
        }

        if (str[11] == 'I')
        { 
            IsCarInParkingLot[CarNumber] = true;
            ParkingFee[CarNumber] -= (InHour * 60) + InMin;
        }
        else
        {
            IsCarInParkingLot[CarNumber] = false;
            ParkingFee[CarNumber] += (InHour * 60) + InMin;
        }
    }

    for (auto& dd : IsCarInParkingLot)
    {
        if (dd.second)
        {
            IsCarInParkingLot[dd.first] = false;
            ParkingFee[dd.first] += (23 * 60) + 59;
        }
    }
    while (!list.empty())
    {
        for (auto& dd : ParkingFee)
        {
            if (dd.first == list.top())
            {
                int result = dd.second;
                if (result - BaseTime > 0)
                {
                    int num = (result - BaseTime) % AddedTime != 0 ? (result - BaseTime) / AddedTime + 1 : (result - BaseTime) / AddedTime;
                    answer.push_back(BaseFee + num * AddedFee);

                }
                else
                {
                    answer.push_back(BaseFee);
                }

                list.pop();
                break;
            }
        }
    }

    return answer;
}