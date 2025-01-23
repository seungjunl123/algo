#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int N;
string str[20001];
int maxC = 0;
int idxS = 0, idxT = 1;


int main(void) 
{
    cin >> N;
    for (int i = 0;i < N;i++)
    {
        string s;
        cin >> s;
        str[i] = s;
    }

    for (int i = 0;i < N - 1;i++)
    {
        for (int j = i + 1;j < N;j++)
        {
            int length = min(str[i].size(), str[j].size());
            int count = 0;
            for (int k = 0;k < length;k++)
            {
                if (str[i][k] != str[j][k]) break;
                count++;
            }

            if (count > maxC)
            {
                maxC = count;
                idxS = i;
                idxT = j;
            }
        }
    }

    cout << str[idxS] << endl;
    cout << str[idxT] << endl;

    return 0;
}