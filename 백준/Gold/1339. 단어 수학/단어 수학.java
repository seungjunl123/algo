import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1339번 - 단어 수학
class Main {
	
	// 실패 알고리즘
	// 반례 , A가 9일 때보다 B가 9일 때 더 큰 값이 된다
	// A : 100
	// B : 110
	/*10
	ABB
	BB
	BB
	BB
	BB
	BB
	BB
	BB
	BB
	BB
	*/
	
    static int N;
    static int ans;
    // 알파벳의 값 저장
    static int[] alp = new int[26];
    static boolean[] check = new boolean[26];
    // 문자열에서 알파벳이 등장하는 길이 저장
    static ArrayList<Integer> list = new ArrayList<>();
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
    	// 풀이과정 
    	// 알파벳 배열 생성
    	// 알파벳이 나올 때마다 배열에 10의 제곱의 자릿수를 곱한 값을 더하고 체크리스트에 넣는다
    	// 모든 단어가 나온 뒤 체크리스트에 있는 알파벳을 알파벳배열의 값에 따라 정렬한다
    	// 가장 큰 수가 있던 값부터 9부터 0까지 값을 대입하고 ans에 추가한다
    	 N = Integer.parseInt(br.readLine());
         ans = 0;

         
         for(int i=0;i<N;i++) {
             String str = br.readLine();
             for(int j=0;j<str.length();j++) {
                 // 알파벳을 숫자로 바꾸고
                 int Alp = str.charAt(j)-'A';
                 
                 // 알파벳이 있던 자리 수를 alp 배열에 저장
                 alp[Alp] += Math.pow(10, str.length()-1-j);
                 //System.out.println(str.charAt(j) +" "+alp[Alp]);
                 if(!check[Alp]) {
                	 list.add(Alp);
                	 check[Alp] = true;
                 }
             }
         }
         
         int[] arr = bubblesort();
         //System.out.println(Arrays.toString(arr));
         
         int Num = 9;
         for(int i=0;i<list.size();i++) {
        	 ans+=alp[arr[i]]*Num--;
         }
         
         bw.write(ans+"");
         bw.flush();
         bw.close();
    }
    
	private static int[] bubblesort() {
		int[] tmpList =  new int[list.size()];
        for(int j=0;j<list.size();j++) {
            tmpList[j] = list.get(j);
        }
        
        for(int i=0;i<tmpList.length-1;i++) {
            for(int j=0;j<tmpList.length-i-1;j++) {
                // 알파벳이 나온 숫자가 더 크면 스왑
                if(alp[tmpList[j]]<alp[tmpList[j+1]]) {
                    int tmp = tmpList[j+1];
                    tmpList[j+1] = tmpList[j];
                    tmpList[j] =tmp;
                }
            }
        }
        return tmpList;
		
	}

}