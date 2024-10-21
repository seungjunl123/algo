import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
	private static class Trie{
		private class Node{
			HashMap<Character,Node> child;
			boolean end;
			
			Node(){
				this.child = new HashMap<>();
				this.end = false;
			}
		}
		
		private Node root = new Node();
		
        private void insert(String str) {
            Node parent = root;

            for (int i = 0; i < str.length(); i++) {
                parent = parent.child.computeIfAbsent(str.charAt(i), e -> new Node());
            }
            parent.end = true;
        }
        private boolean search(String str) {
            Node parent = root;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < str.length(); i++) {
                if (parent == null) return true;

                parent = parent.child.getOrDefault(str.charAt(i), null);
                sb.append(str.charAt(i));
                if (parent != null && parent.end) {
                    if (str.equals(sb.toString())) return true; // 검색된 문자와 같은 단어일 때
                    else return false;
                }
            }
            return true;
        }
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		point : for(int t=0;t<T;t++) {
			int n = Integer.parseInt(br.readLine());
			
			String[] call = new String[n];
			Trie trie = new Trie();
			
			for(int i=0;i<n;i++) {
				call[i] = br.readLine();
				trie.insert(call[i]);
			}
			
			for(int i=0;i<n;i++) {
				if(!trie.search(call[i])) {
					 answer.append("NO").append(System.lineSeparator());
					continue point;
				}
			}
			answer.append("YES").append(System.lineSeparator());
		}
		 bw.write(answer.toString());
		bw.flush();
		bw.close();
	}
}