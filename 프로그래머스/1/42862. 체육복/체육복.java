

class Solution {
    static int answer = 0;
    static int[] check;
    static int[] student;
    public int solution(int n, int[] lost, int[] reserve) {

        student = new int[n+1];
        check = new int[n+1];
        
        for(int i=0;i<lost.length;i++){
            student[lost[i]]--;
        }
        for(int i=0;i<reserve.length;i++){
            student[reserve[i]]++;
        }
        answer=0;
        greedy(n,1, 0);
        
        return answer;
    }
    
    void greedy(int n,int index, int score){
    if(index==n+1){
        if(score>answer) answer = score;
        return;
    }
        if(student[index] == -1){
            if(index>0 && check[index-1] != 1 &&student[index-1]==1){
                check[index-1] = 1;
                greedy(n,index+1,score+1);
                check[index-1] = 0;
            } 
            if(index<n && check[index+1] != 1 &&student[index+1]==1){
                check[index+1] = 1;
                greedy(n,index+1,score+1);
                check[index+1] = 0;
            }
            greedy(n,index+1,score);
        } else {
            greedy(n,index+1,score+1);
        }
    }
}