import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static class CallTrie {
        private class Node {
            Map<Character, Node> child = new HashMap<>();
            boolean end = false;
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

        int testCase = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        Loop1 : while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());

            String[] call = new String[n];
            CallTrie trie = new CallTrie();

            for (int i = 0; i < n; i++) {
                call[i] = br.readLine();
                trie.insert(call[i]);
            }

            for (int i = 0; i < n; i++) {
                boolean result = trie.search(call[i]);

                if (!result) {
                    answer.append("NO").append(System.lineSeparator());
                    continue Loop1;
                }
            }

            answer.append("YES").append(System.lineSeparator());
        }

        bw.write(answer.toString());
        bw.close();
        br.close();
    }
}