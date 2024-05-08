import java.util.Scanner;

public class Main {    
    static int n;
    static int[] dx = {0, 0, 1, -1}; //동 서 남 북 순서
    static int[] dy = {1, -1, 0, 0};
    static double[] percent;
    static boolean[][] visited;
    static double result;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        n = scan.nextInt();
        
        percent = new double[4];
        for(int i = 0; i < 4; i++) {
        	// 방향마다 이동가능 확률을 저장
            percent[i] = scan.nextInt() * 0.01;
        }
        
        visited = new boolean[30][30]; //시작점을 15, 15로 잡기 위함.
        result = 0;
        dfs(15, 15, 0, 1);
        System.out.println(result);
    }    
    
    public static void dfs(int x, int y, int idx, double total) {
        if(idx == n) {
            result += total;
            return;
        }
        
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && ny >= 0 && nx < 30 && ny < 30) {
                if(visited[nx][ny] == false) {
                    visited[nx][ny] = true;
                    // visited가 체크 안된 부분을 이어가면서 그 방향으로 한칸씩 움직일때마다
                    // percent의 값이 total에 곱해지면서 그 방향으로 이동 가능한 확률이 계산된다
                    // (확률이 0인 경로를 이동하면 그대로 그 경로로 가는 확률도 0이다)
                    // N번의 횟수를 이동하면 마지막 경로에 도달할 수 있는 확률이 구해지므로
                    // 모든 경로에 도달할 수 있는 확률을 모두 더하면 정답이 나온다
                    dfs(nx, ny, idx + 1, total * percent[i]);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}
