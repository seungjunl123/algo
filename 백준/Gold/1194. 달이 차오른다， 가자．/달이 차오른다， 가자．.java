import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Key{
   int r,c,k;
   public Key(int r, int c, int k) {
	   this.r = r;
	   this.c = c;
	   this.k = k;
   }

}

class Main{
    static int N,M;
    static final int[] dr= {-1,0,1,0};
    static final int[] dc= {0,-1,0,1};
    static char[][] map;
    static boolean[][][] checked;
    static int ans;
    static int startR, startC;
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        
    	st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        checked = new boolean[64][N][M];
        
        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j]=='0') {
                    startR = i;
                    startC = j;
                }
            }
        }
        
        findShortestPath();
        if(ans==0) {
            bw.write(-1+" ");
        } else {
            bw.write(ans+"");
        }
        bw.flush();
        bw.close();
    }
    private static void findShortestPath() {
    	int count = 0;
        Queue<Key> q = new LinkedList<>();
        Key start = new Key(startR,startC,0);
        q.offer(start);
        checked[0][startR][startC] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            //System.out.println(count);
            for(int i=0;i<size;i++) {
                Key node = q.poll();
                if(map[node.r][node.c]=='1') {
                    ans=count;
                    return;
                }
                for(int j=0;j<4;j++) {
                    int newR = node.r+dr[j];
                    int newC = node.c+dc[j];
                    if(newR>=0&&newC>=0&&newR<N&&newC<M&&!checked[node.k][newR][newC]) {
                        if(map[newR][newC]=='.'||map[newR][newC]=='1'||map[newR][newC]=='0') {
                            //System.out.println("1 "+ map[newR][newC]);
                            checked[node.k][newR][newC] = true;
                            Key newK1 = new Key(newR,newC,node.k);
                            q.offer(newK1);
                        } else if(map[newR][newC]>='a'&&map[newR][newC]<='f') {
                            //System.out.println("2 "+ map[newR][newC]);
                        	int newK = node.k | (1<<(map[newR][newC]-'a'));
                        	Key newkey = new Key(newR,newC,newK);
                        	checked[newK][newR][newC] = true;
                            q.offer(newkey);
                        } else if(map[newR][newC]>='A'&&map[newR][newC]<='F'){
                            if((node.k & (1<<(map[newR][newC]-'A')))>0)  {
                                checked[node.k][newR][newC]= true;
                                Key newK3 = new Key(newR,newC,node.k);
                                q.offer(newK3);
                            }
                        }
                    }
                }
            }
            count++;
        }
    }
}