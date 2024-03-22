import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[] arr;
	static int[] sortedArr;
	static long ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		sortedArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		mergesort(0, arr.length-1);
		
//		System.out.print(Arrays.toString(arr));
		
		bw.write((ans/2)+"");
		bw.flush();
		bw.close();
	}

	private static void mergesort(int left, int right) {
		if(left<right) {
			int mid = (left+right)/2;
			mergesort(left,mid);
			mergesort(mid+1,right);
			
			merge(left,mid,right);
		}
		
	}

	private static void merge(int left, int mid, int right) {
		int L = left;
		int R = mid+1;
		int idx = left;
		
		while(L<=mid&&R<=right) {
			if(arr[L]<=arr[R]) {
				ans += Math.abs(L-idx);
				sortedArr[idx++] = arr[L++];
			}
			else {
				ans += Math.abs(R-idx);
				sortedArr[idx++] = arr[R++];
			}
		}
		if(L<=mid) {
			for(int i=L;i<=mid;i++) {				
				ans += Math.abs(i-idx);
				sortedArr[idx++] = arr[i];
			}
		} else {
			for(int i=R;i<=right;i++) {
				ans += Math.abs(i-idx);
				sortedArr[idx++] = arr[i];
			}
		}
		
		for(int i=left;i<=right;i++) {
			arr[i]=sortedArr[i];
		}
		
	}



}