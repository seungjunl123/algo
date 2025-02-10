#include <bits/stdc++.h>
using namespace std;

int D[2001][2001];
int a[2001];

int main() {
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

    int N, st, ed;
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> a[i];
    
    for (int i = 2; i < N; i++) { //홀수  
    	for (int j = 1; i-j>0 && i+j<=N; j++) {
    		if (a[i-j] == a[i+j]) D[i-j][i+j] = 1;
    		else break;
		}
	}
    
    for (int i = 2; i < N; i++) { //짝수  
    	if (a[i] != a[i+1]) continue;
    	for (int j = 1; i-j>0 && i+1+j<=N; j++) {
    		if (a[i-j] == a[i+1+j]) D[i-j][i+1+j] = 1;
    		else break;
		}
	}

    int M;   
    cin >> M;
    for (int i = 1; i <= M; i++) {
    	cin >> st >> ed;
    	if (ed-st == 1) {
    		if (a[st] == a[ed]) cout << 1 << '\n';
    		else cout << 0 << '\n';
		}
		else if (ed-st == 0) cout << 1 << '\n';
		else cout << D[st][ed] << '\n';
		
	}
}