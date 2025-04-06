#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
using namespace std;

int L, C;
vector<char> Alphabet;
vector<bool> checked;

void MutationPassword(int idx, int count)
{
	if (count == L)
	{
		int vowel = 0;
		int consonant = 0;
		for (int i = 0;i < C;i++)
		{
			if (checked[i])
			{
				if (Alphabet[i] == 'a' || Alphabet[i] == 'e' || Alphabet[i] == 'i' || Alphabet[i] == 'o' || Alphabet[i] == 'u')
				{
					vowel++;
				}
				else
				{
					consonant++;
				}
			}
		}
		if (vowel > 0 && consonant > 1)
		{
			for (int i = 0;i < C;i++)
			{
				if (checked[i])
				{
					cout << Alphabet[i];
				}
			}
			cout << endl;
		}
		return;
	}
	if (idx == C) return;

	checked[idx] = true;
	MutationPassword(idx + 1, count + 1);
	checked[idx] = false;
	MutationPassword(idx + 1, count);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> L >> C;

	Alphabet.resize(C);
	checked.resize(C, false);
	for (int i = 0;i < C;i++)
	{
		cin >> Alphabet[i];
	}
	sort(Alphabet.begin(), Alphabet.end());

	MutationPassword(0, 0);

}
