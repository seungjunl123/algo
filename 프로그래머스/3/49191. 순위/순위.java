class Solution {
    public int solution(int n, int[][] results) {
        // 3명을 지정
        // A,B,C 중에 A가 B를 이기고, C가 A를 이겼다면 무조건 C가 B를 이겼다.
        // 이런 식으로 승리 여부를 결정 지은후 패배수와 승리수의 합이 n-1이라면 순위가 결정된 사람이다.
        int answer = 0;
        int[][] games = new int[n+1][n+1];
        for(int i=0;i<results.length;i++){
            games[results[i][0]][results[i][1]] = 1;
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    if(games[k][j]==0 && games[i][j] == 1 && games[k][i] == 1){
                        games[k][j] = 1;
                    }
                }
            }
        }
        
        // 승리 수와 패배 수 세기
        for(int i=1;i<=n;i++){
            int score = 0;
            for(int j=1;j<=n;j++){
                if(games[i][j]==1 || games[j][i]==1){
                    score++;
                }
            }
            if(score == n-1){
                answer++;
            }
        }
        
        return answer;
    }
}