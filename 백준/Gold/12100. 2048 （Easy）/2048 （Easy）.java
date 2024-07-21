import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		ans = map[0][0];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>ans) ans = map[i][j];
			}
		}
		

		find2048(0, map);

		System.out.println(ans);
	}

	private static void find2048(int idx, int[][] arr) {
//		System.out.println(idx);
		if (idx == 5) return;

		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = arr[i][j];
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(copyMap[i][j]+" ");
//			}
//			System.out.println();
//		}

		right(idx, copyMap);

		top(idx, copyMap);
		left(idx, copyMap);
		bottom(idx, copyMap);
	}

	private static void right(int idx, int[][] arr) {
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			int flag = N-1;
			for (int j = N-2; j >= 0; j--) {
				if (copyArr[i][j] != 0) {
					if (copyArr[i][flag] != 0) {
						if (copyArr[i][flag] == copyArr[i][j]) {
							copyArr[i][flag] *= 2;
							if(copyArr[i][flag] > ans ) ans = copyArr[i][flag];
							copyArr[i][j] = 0;
							flag--;
						} else {
							copyArr[i][--flag] = copyArr[i][j];
							if(flag!=j)copyArr[i][j] = 0;
						}
					} else {						
						copyArr[i][flag] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
		
		find2048(idx+1,copyArr);
		
	}

	private static void bottom(int idx, int[][] arr) {
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		
		for (int j = 0; j < N; j++) {
			int flag = N-1;
			for (int i = N-2; i >= 0; i--) {
				if (copyArr[i][j] != 0) {
					if (copyArr[flag][j] != 0) {
						if (copyArr[flag][j] == copyArr[i][j]) {
							copyArr[flag][j] *= 2;
							if(copyArr[flag][j] > ans ) ans = copyArr[flag][j];
							copyArr[i][j] = 0;
							flag--;
						} else {
							copyArr[--flag][j] = copyArr[i][j];
							if(flag!=i)copyArr[i][j] = 0;
							
						}
					} else {						
						copyArr[flag][j] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
		
		find2048(idx+1,copyArr);
		
	}

	private static void left(int idx, int[][] arr) {
		
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			int flag = 0;
			for (int j = 1; j < N; j++) {
				if (copyArr[i][j] != 0) {
					if (copyArr[i][flag] != 0) {
						if (copyArr[i][flag] == copyArr[i][j]) {
							copyArr[i][flag] *= 2;
							if(copyArr[i][flag] > ans ) ans = copyArr[i][flag];
							copyArr[i][j] = 0;
							flag++;
						} else {
							copyArr[i][++flag] = copyArr[i][j];
							if(flag!=j)copyArr[i][j] = 0;
						}
					} else {						
						copyArr[i][flag] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
		
		find2048(idx+1,copyArr);
		
	}

	private static void top(int idx, int[][] arr) {
//		System.out.println("top 들어오기 전 " + idx);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		for (int j = 0; j < N; j++) {
			int flag = 0;
			for (int i = 1; i < N; i++) {
				if (copyArr[i][j] != 0) {
					if (copyArr[flag][j] != 0) {
						if (copyArr[flag][j] == copyArr[i][j]) {
							copyArr[flag][j] *= 2;
							if(copyArr[flag][j] > ans ) ans = copyArr[flag][j];
							copyArr[i][j] = 0;
							flag++;
						} else {
							copyArr[++flag][j] = copyArr[i][j];
							if(flag!=i)copyArr[i][j] = 0;
						}
					} else {						
						copyArr[flag][j] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
		
//		System.out.println("top 들어온 후 " + idx);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(copyArr[i][j]+" ");
//			}
//			System.out.println();
//		}

	
		find2048(idx+1,copyArr);

	}
}
