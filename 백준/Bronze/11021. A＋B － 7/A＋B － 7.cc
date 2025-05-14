#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int N;
int main() {
    cin >> N;

    for (int i = 0;i < N;i++)
    {
        int A, B;
        cin >> A >> B;

        cout << "Case #" << i + 1 << ": " << (A + B) << endl;
    }
    return 0;
}