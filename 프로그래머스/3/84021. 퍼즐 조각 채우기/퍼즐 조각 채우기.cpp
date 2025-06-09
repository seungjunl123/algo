#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int dr[4] = {0,1,0,-1};
int dc[4] = {1,0,-1,0};

vector<vector<pair<int,int>>> board_block(1250, vector<pair<int, int>>(0));
vector<vector<pair<int,int>>> table_block(1250, vector<pair<int, int>>(0));

void coordinatenormalize(vector<vector<pair<int, int>>>& blocks);
void coordinatenormalize(vector<pair<int, int>>& block);


void rotateBlock(vector<pair<int, int>>& OutTableBlock)
{
    int n = OutTableBlock.size();
    
    vector<pair<int, int>> tmp(n);
    for(int i=0;i<n;i++)
    {
        tmp[i].first = OutTableBlock[i].second;
        tmp[i].second = n-OutTableBlock[i].first-1;
    }
    for(int i=0;i<n;i++)
    {
        OutTableBlock[i].first = tmp[i].first;
        OutTableBlock[i].second = tmp[i].second;
    }
    coordinatenormalize(OutTableBlock);
}

void coordinatenormalize(vector<vector<pair<int, int>>>& blocks)
{
    for(auto& block : blocks)
    {
        coordinatenormalize(block);
    }
}

void coordinatenormalize(vector<pair<int, int>>& block)
{
    int minR = 51;
    int minC = 51;
        
    for(auto& b : block)
    {
        if(minR > b.first) minR = b.first;
        if(minC > b.second) minC = b.second;
    }
    for(auto& b : block)
    {
        b.first -= minR;
        b.second -= minC;
    }
    sort(block.begin(), block.end());
}

bool compare_block(const vector<pair<int,int>>& board, const vector<pair<int,int>>& table)
{
    if(board.size() != table.size()) return false;
    
    for(int i=0;i<board.size();i++)
    {
        if(board[i].first != table[i].first || board[i].second != table[i].second) return false;
    }
    return true;
}

void BFS(const vector<vector<int>>& board, vector<vector<pair<int,int>>>& OutBlockBoard, int flag)
{
    int visited[51][51] = {0,};
    int count = 0;
    
    for(int i=0;i< board.size();i++)
    {
        for(int j=0;j<board[0].size();j++)
        {
            if(visited[i][j]==0 && board[i][j] == flag)
            {
                queue<pair<int,int>> block_queue;
                block_queue.push(make_pair(i,j));
                visited[i][j] = 1;
                
                while(!block_queue.empty())
                {
                    auto tq = block_queue.front();
                    block_queue.pop();
                    OutBlockBoard[count].push_back(make_pair(tq.first, tq.second));
                    
                    for(int k=0;k<4;k++)
                    {
                        int nr = tq.first + dr[k];
                        int nc = tq.second + dc[k];
                        
                        if(nr>=0 && nc>=0 && nr<board.size() && nc< board.size() &&visited[nr][nc]==0 && board[nr][nc] == flag)
                        {
                            visited[nr][nc] = 1;
                            block_queue.push(make_pair(nr, nc));
}
}
                }
                count++;
            }
        }
    }
    
}

int solution(vector<vector<int>> game_board, vector<vector<int>> table) {
    int answer = 0;
    
    
    
    BFS(game_board, board_block,0);
    BFS(table, table_block,1);
    
    coordinatenormalize(board_block);
    coordinatenormalize(table_block);
    
    vector<int> board_assembled(board_block.size(),0);
    vector<int> table_assembled(table_block.size(),0);
    
    for(int i=0;i<board_block.size();i++)
    {
        for(int j=0;j<table_block.size();j++)
        {
            for(int k=0;k<4;k++)
            {
                if(board_assembled[i]==0 &&table_assembled[j]==0 && compare_block(board_block[i], table_block[j]))
                {
                    answer += table_block[j].size();
                    board_assembled[i] = 1;
                    table_assembled[j] = 1;
                }
                else
                {
                    rotateBlock(table_block[j]);
                }
            }
        }
    }

    return answer;
}