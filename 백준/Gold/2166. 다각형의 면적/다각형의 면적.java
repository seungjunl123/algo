import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		long r;
		long c;
		Point(long r, long c){
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static Point[] pointList;
	static long sum = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pointList = new Point[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			pointList[i] = new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
		}
		
		for(int i=1;i<N-1;i++) {
			sum += outerProduct(pointList[0], pointList[i], pointList[i+1]);
		}
		
		sum = Math.abs(sum);
		System.out.printf("%.1f\n", (double)sum/2);
		
		

	}

	private static long outerProduct(Point point, Point point2, Point point3) {
		return ((point.r*point2.c+point2.r*point3.c+point3.r*point.c)
				-(point.r*point3.c+point2.r*point.c+point3.r*point2.c));
	}

}