import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'gridChallenge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static String gridChallenge(List<String> grid) {
        // Write your code here
        int size = grid.size();
        int length = grid.get(0).length();
        List<String> newGrid = new ArrayList<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for(int i=0;i<size;i++){
            int[] alp = new int[26];
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<length;j++){
                // System.out.println(grid.get(i).charAt(j)-97);
                alp[grid.get(i).charAt(j)-97]++;
            }
            for(int j=0;j<26;j++){
                while(alp[j]!=0){
                    // System.out.println(alphabet.charAt(j));
                    sb.append(alphabet.charAt(j));
                    alp[j]--;
                }
            }
            newGrid.add(sb.toString());
        }
        
        boolean flag = false;
        point:for(int i=0;i<length;i++){
            
            int now = 0;
            for(int j=0;j<size;j++){
                if(newGrid.get(j).charAt(i)-now>=0){
                    now = newGrid.get(j).charAt(i);
                } else {
                    flag = true;
                    break point;
                }
            }
        }
        
        if(flag) return "NO";
        else return "YES";

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                    .collect(toList());

                String result = Result.gridChallenge(grid);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
