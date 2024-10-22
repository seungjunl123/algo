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
     * Complete the 'superDigit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING n
     *  2. INTEGER k
     */

    public static int superDigit(String n, int k) {
    // Write your code here
    // 주어지는 값이 10의 10000승
    // 분할 정복으로 풀어야 함
        long nSum = makeSum(n);
        // 맨 처음에 k는 단순히 곱하면 된다.
        nSum = k*nSum;
        while(nSum>10){
            // 이제 sum이 1자리수가 될 때까지 입력해주면 된다.
            // makeSum의 값을 String으로 넣기 위해 ""추가
            nSum = makeSum(nSum+"");
        }
        
        return (int)nSum;

    }

public static int makeSum(String n){
    // 분할 정복
    if(n.length()==1){
        System.out.println("change "+Integer.parseInt(n));
        return Integer.parseInt(n);
    }
    // 중간으로 나눠서 1자리씩 더해준다.

    int start = 0;
    int end = n.length();
    int mid = (start + end)/2;
    // 문자열이 1자리수가 될 때까지 분할해준다.
   return makeSum(n.substring(start, mid))+ makeSum(n.substring(mid, end));
    // return 1;
}
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String n = firstMultipleInput[0];

        int k = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
