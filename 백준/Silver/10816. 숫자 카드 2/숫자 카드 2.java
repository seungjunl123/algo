import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static List<Integer> Nlist;
    static int[] Mlist;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        
        Nlist = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            Nlist.add(Integer.parseInt(st.nextToken()));
        }
        // 이분 탐색을 위한 리스트 정렬
        Collections.sort(Nlist);
        
        
        M = Integer.parseInt(br.readLine());
        Mlist = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            Mlist[i] = Integer.parseInt(st.nextToken());
        }
        
        // 리스트 탐색
        // Mlist의 값보다 -1 작은 수, 1 큰수의 인덱스를 이분탐색으로 찾는다
        // 찾은 두 값의 차이가 숫자카드의 숫자
        for(int i=0;i<M;i++) {
            int a = sort1(Mlist[i]);
            int b = sort2(Mlist[i]);
            
            bw.write((a-b)+" ");
        }
        
        bw.flush();
        bw.close();
        
    }
    
    // 같은 수 중 가장 뒤의 인덱스를 찾는다
    private static int sort1(int idx) {
        int left = 0;
        int right = Nlist.size();
        int mid = (left+right)/2;
        
        while(left<right) {
            mid = (left+right)/2;
            
            // 현재의 mid가 idx값보다 작거나 같으면 left조정
            // left가 idx가 있다면 idx가 존재하는 배열의 오른쪽 끝까지 이동
            if(Nlist.get(mid)>idx) {
            	right = mid;
            } else {
            	left = mid+1;
            }
            
        }
        return left;
    }
    
    private static int sort2(int idx) {
        int left = 0;
        int right = Nlist.size();
        int mid = (left+right)/2;
        
        while(left<right) {
            mid = (left+right)/2;
            
            //sort1과 반대로 right가 배열의 왼쪽 끝까지 이동한다
            if(Nlist.get(mid)>=idx) {
                right = mid;
            } else {
                left = mid+1;
            }
            
        }
        return left;
    }

}