import java.io.*;
import java.util.*;

class Main {
    private static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static char[][] map;
    static int[][] fireTime;
    static int[][] jihunTime;
    static Queue<Node> fireQueue = new LinkedList<>();
    static Queue<Node> jihunQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireTime = new int[R][C];
        jihunTime = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(fireTime[i], Integer.MAX_VALUE);
            Arrays.fill(jihunTime[i], Integer.MAX_VALUE);
        }

        int startR = -1, startC = -1;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    startR = i;
                    startC = j;
                    jihunQueue.add(new Node(i, j));
                    jihunTime[i][j] = 0;
                } else if (map[i][j] == 'F') {
                    fireQueue.add(new Node(i, j));
                    fireTime[i][j] = 0;
                }
            }
        }

        spreadFire();
        int result = moveJihun(startR, startC);
        System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    }

    private static void spreadFire() {
        while (!fireQueue.isEmpty()) {
            Node node = fireQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
                    if (map[nr][nc] != '#' && fireTime[nr][nc] == Integer.MAX_VALUE) {
                        fireTime[nr][nc] = fireTime[node.r][node.c] + 1;
                        fireQueue.add(new Node(nr, nc));
                    }
                }
            }
        }
    }

    private static int moveJihun(int startR, int startC) {
        while (!jihunQueue.isEmpty()) {
            Node node = jihunQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    return jihunTime[node.r][node.c] + 1;
                }

                if (map[nr][nc] == '.' && jihunTime[nr][nc] == Integer.MAX_VALUE) {
                    if (jihunTime[node.r][node.c] + 1 < fireTime[nr][nc]) {
                        jihunTime[nr][nc] = jihunTime[node.r][node.c] + 1;
                        jihunQueue.add(new Node(nr, nc));
                    }
                }
            }
        }
        return -1;
    }
}
