class Solution {
    public int solution(String arr[]) {
        int answer = -1;
        int sum = 0;
        int min = 0;
        int max = 0;
        
        for(int i=0;i<arr.length-1;i+=2){
            int num = Integer.parseInt(arr[arr.length-1-i]);
            
            String op = arr[arr.length-2-i];
            if(op.equals("+")) sum += num;
            else {
                int temp1 = -(num + sum + min);
                int temp2 = -num + sum + max;
                int temp3 = -(num+ sum + max);
                int temp4 = -(num + sum) + min;
                
                max = Math.max(temp1, temp2);
                min = Math.min(temp3, temp4);
                sum = 0;
            }
            
        }
        
         return max + Integer.parseInt(arr[0]) + sum;
    }
}