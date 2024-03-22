import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
	// 병합 정렬

	// 분할 정복 알고리즘 활용
	// 자료를 최소 단위까지 나눈 후 차례로 정렬하여 최종 결과를 얻는다
	// 왼쪽 집합의 가장 작은 값을 i, 오른쪽 집합의 가장 작은 값을 j로 설정
	// i와 j를 비교해서 작은 값을 정렬하는 배열에 저장하고 idx를 1 더한다
	// 다시 i와 j를 비교하며 위의 순서를 반복

	// 문제 풀이
	static int N;
	// 입력되는 값을 저장하는 arr과 정렬되는 값을 저장하는 sortedArr
	static int[] arr;
	static int[] sortedArr;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sortedArr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 최초의 mergesort 실행
		mergesort(0, arr.length - 1);
		
		for(int i=0;i<N;i++) {
			bw.write(arr[i]+"\n");
		}
		bw.flush();
		bw.close();
	}

	static void mergesort(int left, int right) {
		// 최소 단위로 쪼개지지 않은 경우(아직 left와 right 사이에 인덱스가 남아있는 경우)
		if (left < right) {
			int mid = (left + right) / 2;
			// 반으로 나눠서 정렬한다
			mergesort(left, mid);
			mergesort(mid + 1, right);

			merge(left, mid, right);
		}

	}

	private static void merge(int left, int mid, int right) {
		int L = left; // 왼쪽 구간 시작포인트
		int R = mid + 1; // 오른쪽 구간 시작포인트
		int idx = left; // 정렬된 원소를 저장할 위치(sortedArr의 idx)

		// L과 R은 나뉜 배열의 오른쪽으로 가며 비교를 진행
		// 아직 L과 R이 구간의 마지막까지 도달하지 않았을 경우
		while (L <= mid && R <= right) {
			// 왼쪽 구간과 오른쪽 구간의 시작구간 부터
			// L과 R 중 작은 값을 sortedArr의 idx값에 넣어준다
			// 그리고 작았던 구간은 오른쪽으로 한칸 이동, 다시 비교해준다
			if (arr[L] <= arr[R])
				sortedArr[idx++] = arr[L++];
			else
				sortedArr[idx++] = arr[R++];
		}

		// 오른쪽 구간의 정렬이 끝나고 왼쪽 구간만 남았을 경우
		if (L <= mid) {
			// 왼쪽의 남은 모든 값이 오른쪽 구간의 값보다 크므로
			// 남은 sortedArr 배열에 왼쪽 구간의 값을 차례로 넣어준다
			for (int i = L; i <= mid; i++) {
				sortedArr[idx++] = arr[i];
			}
		}

		// 왼쪽 구간의 정렬이 끝나고 오른쪽 구간만 남았을 경우
		else {
			// 오른쪽 남은 모든 값이 왼쪽 구간의 값보다 크므로
			// 남은 sortedArr 배열에 오른쪽 구간의 값을 차례로 넣어준다
			for (int i = R; i <= right; i++) {
				sortedArr[idx++] = arr[i];
			}
		}
		
		// 마지막으로, 정렬된 배열의 값을 원본 배열에 저장한다
		for(int i=left;i<=right;i++) {
			arr [i] = sortedArr[i];
		}
	}
}