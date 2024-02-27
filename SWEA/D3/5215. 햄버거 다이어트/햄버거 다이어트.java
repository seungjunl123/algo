import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
    static int T, N, L;
    // 버거의 맛과 칼로리를 저장할 배열 생성
    static int[][] burger;
    // 칼로리를 만족할 때 맛의 max를 저장할 변수 생성
    static int max;
     
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
         
        for(int t = 1;t<=T;t++) {
            // 케이스 별 정보 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            burger= new int[N][2];
            max = 0;
            for(int i=0;i<N;i++) {
                // 버거에 대한 정보 입력
                st = new StringTokenizer(br.readLine());
                burger[i][0] = Integer.parseInt(st.nextToken());
                burger[i][1] = Integer.parseInt(st.nextToken());
            }
             
             
            selectBur(0,0,0,0);
            bw.write("#"+t+" "+max+"\n");
        }
        bw.flush();
        bw.close();
    }
    // 재귀를 진행하면서 추가하는 버거의 칼로리를 계속 더한다
    // 재료가 1가지만 있어도 찾을 수 있다
    private static void selectBur(int idx, int bidx ,int sum, int cal) {
        // 추가한 재료 수가 N을 넘으면 리턴
        if(cal <= L ) {
            if(sum>max) { 
                max = sum;
            }
        } else {
            return;
        }
        if(bidx >= N) {
            return;
        }
        // 칼로리가 L 이하면 맛의 합을 확인하고, max보다 크면 저장
        for(int i= idx;i<N;i++) {
            selectBur(i+1,bidx+1,sum+burger[i][0],cal+burger[i][1]);
        }
         
    }
}