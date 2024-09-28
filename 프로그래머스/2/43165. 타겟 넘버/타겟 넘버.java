class Solution {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        DFS(numbers, target, 0, 0);
        
        return answer;
    }
    
    public void DFS(int[] numbers, int target, int index, int score){
        if(index == numbers.length){
            if(score == target) answer++;
            return;
        }
        
        DFS(numbers, target, index+1, score+numbers[index]);
        DFS(numbers, target, index+1, score-numbers[index]);
    }
}