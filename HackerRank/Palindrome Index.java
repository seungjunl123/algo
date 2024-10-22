class Result {
 /*
 * Complete the 'palindromeIndex' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */
public static int palindromeIndex(String s) {
 // Write your code here
 int len = s.length();
 int start = 0;
 int end = len-1;
 while(start < end){
 if(s.charAt(start) != s.charAt(end)){
 break;
 }
 start++; end--;
 }
 if(start >= end) return -1;
 if(checkPalindrome(s, start+1, end)) return start;
 if(checkPalindrome(s, start, end-1)) return end;
 return -1;
}
static public boolean checkPalindrome(String s,int start, int end){
 while(start < end){
 if(s.charAt(start) != s.charAt(end)){
 return false;
 }
 start++; end--;
 }
 return true;
}
}
