using System;
using System.Collections.Generic;

public class Solution {
    static int[,] map;
    static bool[,] visited;
    static readonly int[] dr = { 0, 1, 0, -1 };
    static readonly int[] dc = { 1, 0, -1, 0 };

    public int[] solution(string[] maps) {
        int rows = maps.Length;
        int cols = maps[0].Length;

        map = new int[rows, cols];
        visited = new bool[rows, cols];

        // 지도 초기화
        for (int i = 0; i < rows; i++) {
            char[] line = maps[i].ToCharArray();
            for (int j = 0; j < cols; j++) {
                map[i, j] = (line[j] == 'X') ? 0 : int.Parse(line[j].ToString());
            }
        }

        List<int> resultList = new List<int>();

        // 모든 셀 탐색
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i, j] && map[i, j] > 0) {
                    resultList.Add(DFS(i, j, rows, cols));
                }
            }
        }

        if (resultList.Count == 0) return new int[] { -1 };

        resultList.Sort();
        return resultList.ToArray();
    }

    static int DFS(int r, int c, int maxR, int maxC) {
        visited[r, c] = true;
        int sum = map[r, c];

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (IsInBounds(nr, nc, maxR, maxC) && !visited[nr, nc] && map[nr, nc] > 0) {
                sum += DFS(nr, nc, maxR, maxC);
            }
        }

        return sum;
    }

    static bool IsInBounds(int r, int c, int maxR, int maxC) {
        return r >= 0 && r < maxR && c >= 0 && c < maxC;
    }
}
