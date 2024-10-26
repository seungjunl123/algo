import java.util.Arrays;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Main {

    static int[] start, end;
    static char[][][] map;
    static boolean[][][] check;
    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int[] dr = {0, 0, 1, -1, 0, 0};
    static int[] dc = {1, -1, 0, 0, 0, 0};
    static int L, R, C;
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            check = new boolean[L][R][C];

            for(int i=0;i<L;i++){
               for(int j=0;j<R;j++){
            	   String str = br.readLine();
                    for(int k=0;k<C;k++){
//                    	System.out.println(i+" "+j+" "+k);
                        map[i][j][k] = str.charAt(k);
                        if(map[i][j][k] == 'S'){
                            start = new int[]{i, j, k, 0};
                        }
                    }
                }
               String pass = br.readLine();
            }

            BFS();
        }

        bw.flush();
        bw.close();
    }

    public static void BFS() throws IOException{
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        check[start[0]][start[1]][start[2]] = true;
        int time = 0;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            if(map[now[0]][now[1]][now[2]] == 'E'){
//            	System.out.println("찾았음 "+now[0]+" "+now[1]+" "+now[2]);
                bw.write("Escaped in " + now[3] + " minute(s).\n");
                return;
            }

            for(int i=0;i<6;i++){
                int nl = now[0] + dl[i];
                int nr = now[1] + dr[i];
                int nc = now[2] + dc[i];

                if(nl < 0 || nr < 0 || nc < 0 || nl >= L || nr >= R || nc >= C) continue;
                if(check[nl][nr][nc] || map[nl][nr][nc] == '#') continue;
                
                check[nl][nr][nc] = true;
//                System.out.println(nl+" "+nr +" "+nc);
                queue.add(new int[]{nl, nr, nc, now[3]+1});
            }   

            time++;
        }

        bw.write("Trapped!\n"); 
    }
}
