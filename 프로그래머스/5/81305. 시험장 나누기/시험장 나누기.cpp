#include <string>
#include <vector>

using namespace std;

constexpr int MAX_N = 10000;

int Num[MAX_N];
int Child[MAX_N][2];
int Parent[MAX_N];
int Root, Cnt, Limit;

int dfs(int node)
{
    if(node == -1) return 0;
    if(Num[node] > Limit)
    {
        Cnt = MAX_N + 1;
        return Num[node];
    }
    
    int numL = dfs(Child[node][0]);
    int numR = dfs(Child[node][1]);
    
    if(numL + numR + Num[node] <= Limit)
        return numL + numR + Num[node];
    
    if(numL == 0 || numR == 0)
    {
        Cnt++;
        return Num[node];
    }
    
    if(numL + Num[node] <= Limit && numR + Num[node] <= Limit)
    {
        Cnt++;
        return numL < numR ? numL + Num[node] :numR + Num[node];
    }
    
    if(numL + Num[node] <= Limit)
    {
        Cnt++;
        return numL + Num[node];
    }
    
    if(numR + Num[node] <= Limit)
    {
        Cnt++;
        return numR + Num[node];
    }
    
    Cnt += 2;
    return Num[node];
}

bool check(int limit, int k)
{
    Cnt = 0;
    Limit = limit;
    dfs(Root);
    if(Cnt < k) return true;
    
    return false;
}

int solution(int k, vector<int> num, vector<vector<int>> links) {
    int sum = 0;
    for(int i=0;i<num.size();i++)
    {
        Parent[i] = -1;
        Num[i] = num[i];
        sum += num[i];
    }
    
    for(int i=0;i<links.size();i++)
    {
        Child[i][0] = links[i][0];
        if(Child[i][0] != -1) Parent[Child[i][0]] = i;        
        Child[i][1] = links[i][1];
        if(Child[i][1] != -1) Parent[Child[i][1]] = i;  
    }
    
    for(int i=0;i<num.size();i++)
    {
        if(Parent[i] == -1)
        {
            Root = i;
            break;
        }
    }
    
    int low = sum / k, high = sum;
    while(low <= high)
    {
        int mid = (low + high) / 2;
        if(check(mid,k))
        {
            high = mid - 1;
        }
        else
        {
            low = mid + 1;
        }
    }
    return low;
}