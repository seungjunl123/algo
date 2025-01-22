#include <string>
#include <vector>
#include <unordered_map>
#include <functional>

using namespace std;

int solution(vector<int> info, vector<vector<int>> edges) {
    int answer = 0;

    const int size = info.size();

    std::unordered_map<int, std::vector<int>> graph;
    for (const auto& ele : edges)
    {
        graph[ele[0]].push_back(ele[1]);
    }

    std::vector<bool> candidates(size, false);
    std::function<void(int, int, int)> bfs = [&](int num, int sheep, int wolves) {

        if (0 < sheep && sheep <= wolves)
        {
            return;
        }

        if (info[num])
        {
            wolves += 1;
        }
        else
        {
            sheep += 1;
        }

        answer = std::max(answer, sheep);

        for (const auto& ele : graph[num])
        {
            candidates[ele] = true;
        }

        for (int i = 0; i < size; ++i)
        {
            if (candidates[i])
            {
                candidates[i] = false;
                bfs(i, sheep, wolves);
                candidates[i] = true;
            }
        }

        for (const auto& ele : graph[num])
        {
            candidates[ele] = false;
        }
    };

    bfs(0, 0, 0);

    return answer;
}