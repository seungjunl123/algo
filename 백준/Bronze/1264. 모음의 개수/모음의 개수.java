import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 접점이 선분 안에 있으면 4등분 가능
	// 선분의 기울기와 y 절편을 찾아서 유사 2차 방정식을 세운다
	
	static float[] x;
	static float[] y;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = br.readLine();
			if(str.equals("#")) break;
			
			int score =0 ;
			for(int i=0;i<str.length();i++) {
				if(str.charAt(i)=='a'||
						str.charAt(i)=='e'||
						str.charAt(i)=='i'||
						str.charAt(i)=='o'||
						str.charAt(i)=='u'||
						str.charAt(i)=='u'||
						str.charAt(i)=='A'||
						str.charAt(i)=='E'||
						str.charAt(i)=='I'||
						str.charAt(i)=='O'||
						str.charAt(i)=='U') {
					score++;
				}
			}
			System.out.println(score);
		}
	}
}