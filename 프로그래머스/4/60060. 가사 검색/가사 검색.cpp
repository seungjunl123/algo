#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Trie
{
private:
    Trie* child[26];
    int count;
public:
    Trie() : child(),count(0){}
    void insert(string str)
    {
        Trie* now = this;
        for(char c : str)
        {
            now->count++;
            if(now->child[c-'a']==nullptr)
            {
                now->child[c-'a'] = new Trie();
            }
            now = now->child[c-'a'];
        }
    }
    int search(string str)
    {
        Trie* now = this;
        
        for(char c:str)
        {
            if(c == '?')
                return now->count;
            else
                now = now->child[c-'a'];
            
            if(now==nullptr)
                return 0;
        }
        return -1;
    }
};

Trie wordList[100001];
Trie reverse_wordList[100001];

vector<int> solution(vector<string> words, vector<string> queries) {
    vector<int> answer;
    
    for (string word : words)
    {
        int len = word.size()-1;
        wordList[len].insert(word);

        reverse(word.begin(), word.end());
        reverse_wordList[len].insert(word);
    }

    for (string word : queries)
    {
        int len = word.size()-1;
        if (word[0] != '?')
            answer.push_back(wordList[len].search(word));
        else
        {
            reverse(word.begin(), word.end());
            answer.push_back(reverse_wordList[len].search(word));   
        }
    }   
    return answer;
}