import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	// 20437_문자열 게임2
	
	// 문자열에서 가장 가까운 같은 알파벳 N개와 가장 먼 같은 알파벳 N개
	// 알파벳 별로 정렬하면 쉬울 듯
	// 1. 알파벳의 개수를 가지는 배열 생성하고, 문자열에서 알파벳이 나올때 마다 문자열의 idx 배열에 저장
	// 2. 각 알파벳 별로 크기가 N개 이상 나왔는지 확인
	// 3. N개 이상이라면, 반복문을 활용, i번째 idx와 i+N-1번째 idx의 차이를 저장
	// 4. 그 중 가장 작은 값(어차피 가장 짧은 값은 첫번째와 마지막인 그 알파벳으로 채워지므로)
	//    과 가장 큰 값을 저장
	//		-> 큰 값 계산할 때는 누적합을 해야할 듯? 아니면 더 생각
	// 5. 모든 알파벳이 N개보다 적게 등장했다면 -1 출력
	static int N,K;
	static String str;
	static List<Integer>[] alphabetIdx;
	static int max, min;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			str = br.readLine();
			K = Integer.parseInt(br.readLine());
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			boolean flag = false;
			alphabetIdx = new ArrayList[26];
			for(int j=0;j<26;j++) {
				alphabetIdx[j] = new ArrayList<>();
			}
			// idx 저장
			for(int j=0;j<str.length();j++) {
//				System.out.println((str.charAt(j)-'a')+" "+j );
				alphabetIdx[str.charAt(j)-'a'].add(j);
			}
			
			// alphabet 개수 확인
			for(int j=0;j<26;j++) {
				if(alphabetIdx[j].size()>=K) {
					flag = true;
//					System.out.println(j+"번째 알파벳이 가능");
					// 최소값 찾기
					findMin(j);
					findMax(j);
				}
			}
			
			if(flag) {				
				System.out.println(min+" "+max);
			} else {
				System.out.println(-1);
			}
		}
	}
	
	private static void findMax(int j) {
		for(int k=0;k<=alphabetIdx[j].size()-K;k++) {
			// 만족하는 알파벳의 두 idx 차이와 min값 비교
//			System.out.println(j+": "+(alphabetIdx[j].get(k+K-1)- alphabetIdx[j].get(k)+1));
			max = Math.max(alphabetIdx[j].get(k+K-1)- alphabetIdx[j].get(k)+1, max);
		}
		
	}

	private static void findMin(int j) {
		for(int k=0;k<=alphabetIdx[j].size()-K;k++) {
			// 만족하는 알파벳의 두 idx 차이와 min값 비교
			min = Math.min(alphabetIdx[j].get(k+K-1)- alphabetIdx[j].get(k)+1, min);
		}
		
	}
}