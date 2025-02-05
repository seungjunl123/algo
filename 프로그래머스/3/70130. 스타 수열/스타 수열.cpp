#include <string>
#include <vector>
#include <iostream>

using namespace std;

int map[5000001];

int solution(vector<int> a) {
    int size = a.size();
    int answer = 0;
    for (int i = 0;i < size;i++)
	{
		map[a[i]]++;
	}
    
	for (int i = 0;i < size;i++)
	{
		// 현재 answer/2보다 작거나 같으면 패스
		if (map[i] <= answer / 2) continue;
		// 전체 탐색을 시작해 현재 탐색하는 값 찾기

		int tmpAnswer = 0;
		int curIdx = 0;
		int lastIdx = -1;
		// 짝을 될 인덱스를 찾는다.
		while (curIdx != size)
		{
			// 마지막으로 짝을 만든 인덱스까지 왼쪽으로 이동
			if (a[curIdx] == i)
			{
				bool flag = false;
				for (int j = lastIdx == -1 ? 0 : lastIdx+1;j < curIdx;j++)
				{
					// 자신과 같은 값이 아니면 짝으로 선언. 두 값 중 뒤의 값을 마지막으로 짝이된 인덱스로 설정
					if (a[j] != i)
					{
						// 두개 들어가니까 answer+2
						tmpAnswer += 2;
						lastIdx = curIdx;
						flag = true;
						break;
					}
				}
				// 왼쪽 탐색 동안 값이 없었다면 오른쪽 탐색
				if (!flag) 
				{
					for (int j = curIdx + 1;j < size;j++)
					{
						if (a[j] != i)
						{
							// 두개 들어가니까 answer+2
							tmpAnswer += 2;
							// 마지막 탐색구간이 curIdx보다 크므로 같이 업데이트
							lastIdx = curIdx = j;
							flag = true;
							break;
						}
					}
				}
			}

			curIdx++;
		}
		if (tmpAnswer > answer) answer = tmpAnswer;

	}
    return answer;
}