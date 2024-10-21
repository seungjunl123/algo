import java.io.;
import java.math.;
import java.security.;
import java.text.;
import java.util.;
import java.util.concurrent.;
import java.util.function.;
import java.util.regex.;
import java.util.stream.;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    
      Complete the 'plusMinus' function below.
     
      The function accepts INTEGER_ARRAY arr as parameter.
     

    public static void plusMinus(ListInteger arr) {
     Write your code here
        float total = arr.size();
        float pos = 0;
        float neg = 0;
        float zero = 0;
        for (Integer integer  arr) {
            if(integer0) pos++;
            else if(integer0) neg++;
            else zero++;
        }
        
        System.out.printf(%.5f, postotal);
        System.out.println();
        System.out.printf(%.5f, negtotal);
         System.out.println();
        System.out.printf(%.5f, zerototal);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        ListInteger arr = Stream.of(bufferedReader.readLine().replaceAll(s+$, ).split( ))
            .map(IntegerparseInt)
            .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}
