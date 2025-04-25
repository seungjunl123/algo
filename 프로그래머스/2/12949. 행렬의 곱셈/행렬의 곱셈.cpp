#include <string>
#include <vector>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> arr1, vector<vector<int>> arr2) {
    vector<vector<int>> answer;
    int row = arr1.size();
    int col = arr2[0].size();
    
    answer.resize(row,vector<int>(col, 0));
    for(int i=0;i<row;i++)
    {
        for(int j=0;j<col;j++)
        {
            int num = 0;
            
            for(int k=0;k<arr1[i].size();k++)
            {
                num += arr1[i][k] * arr2[k][j];
            }
            
            answer[i][j] = num;
        }
    }
    return answer;
}