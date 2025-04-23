#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> A, vector<int> B)
{
    int answer = 0;

    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    
    int size = A.size();
    for(int i=0;i<size;i++)
    {
        answer += A[i]*B[size-1-i];
    }

    return answer;
}