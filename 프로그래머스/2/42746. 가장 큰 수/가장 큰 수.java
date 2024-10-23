import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        int len = numbers.length;
        List<String> num = new ArrayList<String>();
        
        for(int i=0;i<len;i++){
            num.add(numbers[i]+"");
        }
        
        Collections.sort(num, new Comparator<String>(){
            @Override
            public int compare(String n1, String n2){
                return (n2+n1).compareTo(n1+n2);
            }
        });
        
        String answer = "";
        
        for(int i=0;i<len;i++){
            answer+=num.get(i);
        }
        return answer.startsWith("0") ? "0" : answer;
    }
}