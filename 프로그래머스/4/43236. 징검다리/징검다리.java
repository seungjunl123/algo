import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        // 바위를 정렬합니다.
        Arrays.sort(rocks);
        
        int left = 1; // 가능한 최소 거리의 최솟값
        int right = distance; // 가능한 최소 거리의 최댓값
        int answer = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2; // 현재 시도하는 최소 거리
            int removed = 0;
            int prev = 0; // 이전 지점
            
            for (int rock : rocks) {
                if (rock - prev < mid) {
                    removed++; // 현재 바위를 제거
                } else {
                    prev = rock; // 현재 바위를 유지하고 이전 지점을 업데이트
                }
            }
            
            // 마지막 지점과의 거리도 고려
            if (distance - prev < mid) {
                removed++;
            }
            
            if (removed > n) {
                // 제거해야 할 바위가 너무 많으면, 최소 거리를 줄여야 함
                right = mid - 1;
            } else {
                // 제거해야 할 바위가 n 이하이면, 최소 거리를 늘려봄
                answer = mid;
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
