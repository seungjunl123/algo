import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        Stack<Integer> stack = new Stack<Integer>();
        
        stack.add(arr[0]);        
        for(int i=1;i<arr.length;i++){
            if(stack.peek()!=arr[i]){
                stack.add(arr[i]);
            }
        }
        int[] stackToArray = new int[stack.size()]; 
        for(int i=stackToArray.length-1;i>=0;i--){
            stackToArray[i] = stack.pop();
        }
        answer = stackToArray;
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println(stack);

        return answer;
    }
}