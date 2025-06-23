#include <string>
#include <vector>
#include <unordered_set>
#include <algorithm>

using namespace std;

// 들어올 때마다 해당 문자열을 set에 저장
// 새로 문자열이 들어올 때마다 이미 저장된 길이들 만큼 문자열을 쪼갬
// 각 길이에 저장된 문자열이 set에 저장되었는지 확인
// 저장되어 있다면 false 처리 후 마무리
// 그렇지 않다면 전체 문자열을 다시 set에 저장. 마지막 문자열 까지 진행하면 true 반환

bool solution(vector<string> phone_book) {
    bool answer = true;
    vector<bool> checks(21,false);
    
    unordered_set<string> prefixs;

    sort(phone_book.begin(), phone_book.end());
    for(string s : phone_book)
    {
        int size = s.size();
        
         for(int i=1;i<21;i++)
         {
             if(checks[i])
             {
                 string splitted = s.substr(0,i);
                 if(prefixs.find(splitted)!=prefixs.end()) return false;   
             }
         }
        prefixs.insert(s);
        checks[size] =true;
    }
    
    return answer;
}