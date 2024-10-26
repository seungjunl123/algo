class Solution {
    public int solution(int[] money) {
        if (money == null || money.length == 0) {
            return 0;
        }
        if (money.length == 1) {
            return money[0];
        }

        if (money.length == 2) {
            return Math.max(money[0], money[1]);
        }

        // 원형 배열을 고려하여 두 번 계산
        // 1. 첫 번째 집을 제외하고 계산
        int case1 = robLinear(money, 1, money.length - 1);
        // 2. 마지막 집을 제외하고 계산
        int case2 = robLinear(money, 0, money.length - 2);

        return Math.max(case1, case2);
    }

    // 특정 범위 내에서 최대 금액을 계산하는 함수
    private int robLinear(int[] money, int start, int end) {
        if (start == end) {
            return money[start];
        }

        int[] dp = new int[money.length];
        dp[start] = money[start];
        dp[start + 1] = Math.max(money[start], money[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }

        return dp[end];
    }
}
