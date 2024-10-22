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
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
    // Write your code here
    // 두 자리 앞까지만 가능하다는 것을 명심하자
    // 현재 idx의 자리보다 2 이상 높은 수가 있을 경우 Too chaotic 출력
        int count = 0;
        for(int i=0;i<q.size();i++){
            if(q.get(i)>i+3){
                System.out.println("Too chaotic");
                return;
            } 
            // 그렇지 않은 경우.
            // idx에서 원래 자리에 있는 숫자보다 큰 숫자가 있을 경우는
            // 다음 수, 그 다음 수 밖에 없다(2개 이상 뇌물을 먹이면 탈락)
            // 그러니 기존 값에서 두 자리 앞을 확인하고, 현재 그 값이 있는
            // 위치까지 탐색을 한다면 그 값이 얼마나 뇌물을 먹었는지 알 수 있다
            for(int j=Math.max(q.get(i)-2, 0);j<i;j++){
                if(q.get(j)>q.get(i)) count++;
            }
        }
        System.out.println(count);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
