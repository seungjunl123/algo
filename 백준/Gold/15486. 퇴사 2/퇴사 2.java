import java.io.*;
import java.util.*;

class Main {
	static int N;
	static int[][] map;
	static int[] arr;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	map = new int[N+2][2];
    	for(int i=1;i<=N;i++) {
    		st = new StringTokenizer(br.readLine());
    		map[i][0] = Integer.parseInt(st.nextToken());
    		map[i][1] = Integer.parseInt(st.nextToken());
    	}
    	arr = new int[N+2];
    	int answer = 0;
    	
    	for(int i=1;i<=N+1;i++) 
    	{	
    		int next = i + map[i][0];
    		if(answer<arr[i]) answer = arr[i];
    		
    		if(next<=N+1) {    			
    			arr[next] = Math.max(arr[next],
    					map[i][1]+answer);
    		}
    	}
   
    	
  
    	
    	System.out.println(arr[N+1]);
    }
}
