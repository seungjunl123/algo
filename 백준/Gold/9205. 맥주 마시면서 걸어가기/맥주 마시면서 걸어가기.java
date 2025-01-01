import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	// 다음 x+y 좌표 합이 현재 값보다 1000 차이가 안나야 함
	static int T,n;
	static Node[] convList;
	static int[] checked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t<T;t++) {
			n = Integer.parseInt(br.readLine());
			convList = new Node[n+2];
			checked = new int[n+2];
			
			// 상근이네
			StringTokenizer st = new StringTokenizer(br.readLine());
			convList[0] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			// 편의점
			for(int i=1;i<=n;i++) {
				st = new StringTokenizer(br.readLine());
				convList[i] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			// 펜타포트
			st = new StringTokenizer(br.readLine());
			convList[n+1] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		
			boolean flag = findPathToFentaport();
			
			if(flag) {
				bw.write("happy\n");
			} else {
				bw.write("sad\n");
			}
		}
		
		bw.flush();
		bw.close();
		
		
		
	}
	private static boolean findPathToFentaport() {
		Queue<Node> q = new LinkedList<>();
		
		q.add(convList[0]);
		checked[0] = 1;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.x == convList[n+1].x && node.y == convList[n+1].y) {
				return true;
			}
			for(int i=1;i<=n+1;i++) {
				if(checked[i]==1) continue;
				int dist = Math.abs(node.x-convList[i].x)+Math.abs(node.y-convList[i].y);
				
				if(dist<=1000) {
					q.add(convList[i]);
					checked[i] = 1;
				}
			}
		}
		
		return false;
	}


}