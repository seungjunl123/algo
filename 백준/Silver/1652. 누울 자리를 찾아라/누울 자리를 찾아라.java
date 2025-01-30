import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	static int N;
	static char[][] heads;
	static int Ranswer = 0, Canswer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		heads = new char[N][N];
		for(int i=0;i<N;i++)
		{
			String str = br.readLine();
			for(int j=0;j<N;j++)
			{
				heads[i][j]= str.charAt(j);
			}
		}
		
		
		if(N==1) {
			System.out.println("0 0");
		}
		else {
			for(int i=0;i<N;i++) {
				int Ccount = 0;
				for(int j=0;j<N;j++)
				{
					if(heads[i][j]=='.')
					{
						Ccount++;
					}
					else if(heads[i][j]=='X')
					{
						if(Ccount>1) {							
							Canswer++;
						}
						Ccount = 0;
					}
				}
				if(Ccount>1) {
					Canswer++;
				}
			}
			for(int i=0;i<N;i++) {
				int Rcount = 0;
				for(int j=0;j<N;j++)
				{
					if(heads[j][i]=='.')
					{
						Rcount++;
					}
					else if(heads[j][i]=='X')
					{
						if(Rcount>1) {							
							Ranswer++;
						}
						Rcount = 0;
					}
				}
				if(Rcount>1) {
					Ranswer++;
				}
			}
			System.out.println(Canswer +" "+Ranswer);
		}
	}



}