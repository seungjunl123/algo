import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


class Main {
	// 에라토스테네스의 체 : 배수를 찾아서 모두 지우면 소수만 남길 수 있다
	static int N;
	static int[] score,player;
	static int[] cardInHand;
	static int max = 0;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		score = new int[N+1];
		player = new int[N+1];
		cardInHand = new int[1000001];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int card = Integer.parseInt(st.nextToken());
			cardInHand[card] = i;
			player[i] = card;
			max = Math.max(max, card);
		}

		for(int i=1;i<=N;i++) {
			int start = player[i];
			for(int j=start*2;j<=max;j+=start) {
//				System.out.println(start+" "+j+" "+cardInHand[j]);
				if(cardInHand[j]>0) {
					score[i]++;
					score[cardInHand[j]]--;
				}
			}
		}

		for(int i=1;i<=N;i++) {
			bw.write(score[i]+" ");
		}
		

		bw.flush();
		bw.close();
	}
}