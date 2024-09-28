import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        int pow = N;
        List<Integer>[] lists = new ArrayList[10];
        
        for(int i=1;i<10;i++){
            lists[i] = new ArrayList();
        }
        
        lists[1].add(N);
        if(N == number){
            answer =1 ;
        } else {
            ep:for(int i=2;i<9;i++){
                pow = pow*10 + N;
                if(pow == number){
                    answer = i;
                    break ep;
                } else {
                    lists[i].add(pow);
                }
                for(int j=1;j<i;j++){
                    int a = lists[j].size();
                    int b = lists[i-j].size();
                    
                    for(int x=0;x<a;x++){
                        for(int y=0;y<b;y++){
                            if(lists[j].get(x) + lists[i-j].get(y)==number){
                                answer = i;
                                break ep;
                            } else lists[i].add(lists[j].get(x) + lists[i-j].get(y));
                            if(lists[j].get(x) - lists[i-j].get(y)==number){
                                answer = i;
                                break ep;
                            } else if(lists[j].get(x) - lists[i-j].get(y)>0){
                                lists[i].add(lists[j].get(x) - lists[i-j].get(y));
                            }
                            if(lists[j].get(x) * lists[i-j].get(y)==number){
                                answer = i;
                                break ep;
                            } else if(lists[j].get(x) * lists[i-j].get(y)>0){
                                lists[i].add(lists[j].get(x) * lists[i-j].get(y));
                            }
                            if(lists[j].get(x) / lists[i-j].get(y)==number){
                                answer = i;
                                break ep;
                            } else if(lists[j].get(x) / lists[i-j].get(y)>0){
                                lists[i].add(lists[j].get(x) / lists[i-j].get(y));
                            }
                            if( lists[i-j].get(y)/lists[j].get(x)==number){
                                answer = i;
                                break ep;
                            } else if(lists[i-j].get(y)/lists[j].get(x)>0){
                                lists[i].add(lists[i-j].get(y)/lists[j].get(x));
                            } 
                            if( lists[i-j].get(y)-lists[j].get(x)==number){
                                answer = i;
                                break ep;
                            } else if(lists[i-j].get(y)-lists[j].get(x)>0){
                                lists[i].add(lists[i-j].get(y)-lists[j].get(x));
                            }                                                    
                        }
                    }
                }
            }
        }
        
        return answer;
    }

}