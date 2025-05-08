#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

struct Trie {
    Trie* child[26];
    vector<int> wordIndices;

    Trie() {
        fill(child, child + 26, nullptr);
    }

    void insert(const string& word, int index) {
        Trie* node = this;
        for (char c : word) {
            int idx = c - 'a';
            if (node->child[idx] == nullptr)
                node->child[idx] = new Trie();
            node = node->child[idx];
            node->wordIndices.push_back(index);  // 이 노드를 거치는 단어
        }
    }

    void findMaxLCP(const vector<string>& words, int depth,
                    int& maxLen, pair<int, int>& answer) {
        for (int i = 0; i < 26; ++i) {
            if (child[i]) {
                child[i]->findMaxLCP(words, depth + 1, maxLen, answer);
            }
        }

        if (wordIndices.size() >= 2) {
            // 가장 앞서 나온 두 단어 선택
            int a = wordIndices[0], b = wordIndices[1];
            if (a > b) swap(a, b);
            if (depth > maxLen || (depth == maxLen && make_pair(a, b) < answer)) {
                maxLen = depth;
                answer = {a, b};
            }
        }
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<string> words(N);
    Trie root;

    for (int i = 0; i < N; ++i) {
        cin >> words[i];
        root.insert(words[i], i);
    }

    int maxLCP = 0;
    pair<int, int> result = {N, N};

    root.findMaxLCP(words, 0, maxLCP, result);

    cout << words[result.first] << '\n' << words[result.second] << '\n';
    return 0;
}
