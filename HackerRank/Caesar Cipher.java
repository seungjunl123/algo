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
     * Complete the 'caesarCipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String caesarCipher(String s, int k) {
        String small = "abcdefghijklmnopqrstuvwxyz";
        String big = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        
        StringBuilder sb = new StringBuilder();
    // Write your code here
    for(int i=0;i<s.length();i++){
        if((s.charAt(i)-0>=65&&s.charAt(i)-0<=90)){
            int spell = (s.charAt(i)-65+k)%26;
            sb.append(big.charAt(spell));
        }
        else if((s.charAt(i)-0>=97&&s.charAt(i)-0<=122)){
            // System.out.println(s.charAt(i)-0);
            int spell = (s.charAt(i)-97+k)%26;
            sb.append(small.charAt(spell));
        } else {
            sb.append(s.charAt(i));
        }
    }
    
    String answer = sb.toString();
        return answer;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
