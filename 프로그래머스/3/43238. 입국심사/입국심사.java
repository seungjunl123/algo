class Solution {
    public long solution(int n, int[] times) {
        long N = n;
        long start = 0;
        long end = 1000000000l * 1000000000l * 100000l;
        
        while(start<end){
            long mid = (start+end)/2;
            if(N<=checkWaiter(times,mid)){
                end = mid ;
            } else {
                start = mid + 1;
            }
        }
        
        
        
        return start;
    }
    
    public long checkWaiter(int[] times, long time){
        long sum = 0;
        for(int i=0;i<times.length;i++){
            sum += time/times[i];
        }
        
        return sum;
    }
}