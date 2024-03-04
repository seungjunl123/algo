
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int i = Integer.parseInt(st.nextToken());
		int j = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine());
		
		int resultX = 0;
		int resultY = 0;
		int x_i = (i+t) / x;
		int y_j = (j+t) / y;
		
		if(x_i  % 2 == 0) {
			resultX = (i+t) % x;
		} else if (x_i % 2 == 1) {
			resultX = x - ((i+t) % x);
		}
		if(y_j  % 2 == 0) {
			resultY = (j+t) % y;
		} else if (y_j % 2 == 1) {
			resultY = y - ((j+t) % y);
		}
		
		System.out.printf("%d %d",  resultX,  resultY);
	}

}
