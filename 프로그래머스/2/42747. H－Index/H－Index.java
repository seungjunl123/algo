import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);  // 인용 횟수를 오름차순으로 정렬
        
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            int h = n - i;  // h는 남은 논문의 수 (h번 이상 인용된 논문의 수)
            if (citations[i] >= h) {  // 현재 논문의 인용 횟수가 h번 이상이면 H-Index 성립
                return h;  // 이 시점에서 H-Index는 h
            }
        }
        return 0;  // H-Index가 없으면 0 반환
    }
}
