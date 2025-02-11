#include <string>
#include <vector>
#include <map>
#include <set>

using namespace std;

struct Node
{
    bool endOfWord = false;
    map<char, Node> next;
};

Node trie[9];
set<int> unique_masks; // 중복 방지를 위한 비트마스크 저장
int answer = 0;

void insertTrie(string str)
{
    int size = str.length();
    Node* node = &trie[size];

    for (char ch : str)
    {
        if (node->next.find(ch) == node->next.end())
        {
            node->next[ch] = Node(); // 새로운 노드 삽입
        }
        node = &node->next[ch]; // 참조 이동
    }

    node->endOfWord = true;
}

bool match(string bStr, string uStr)
{
    if (bStr.length() != uStr.length()) return false;

    for (int i = 0; i < bStr.length(); i++)
    {
        if (bStr[i] != '*' && bStr[i] != uStr[i])
        {
            return false;
        }
    }

    return true;
}

// 현재 선택된 ID 조합을 비트마스크로 변환 후 중복 체크
void check(int idx, int mask, vector<string> user_id, vector<string> banned_id)
{
    if (idx == banned_id.size())
    {
        if (unique_masks.find(mask) == unique_masks.end())
        {
            unique_masks.insert(mask);
            answer++;
        }
        return;
    }

    for (int i = 0; i < user_id.size(); i++)
    {
        if (mask & (1 << i)) continue; // 이미 선택된 ID는 스킵

        if (match(banned_id[idx], user_id[i]))
        {
            check(idx + 1, mask | (1 << i), user_id, banned_id); // 해당 ID를 선택하고 재귀 호출
        }
    }
}


int solution(vector<string> user_id, vector<string> banned_id) {
    // trie 문제
    // 문자열의 길이별로 배열을 생성, 8개까지만 있음
    // 각 자리수에서 별이 있다면 뭐든 가능, 특정 글자가 있다면 해당 글자만 확인
    // banned_id의 배열 대로 반복문을 돌려 가능한 글자를 찾으면 해당 글자를 채용했다는 체크(DFS)
    // 마지막 아이디까지 탐색이 완료되면 카운트를 1 증가
    
    check(0, 0, user_id, banned_id);
    return answer;
}