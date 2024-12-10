import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static int[] parents;
    static List<Integer> list;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            parents = new int[N + 1];
            answer = 0;

            // 부모 관계를 설정
            for (int j = 0; j < N - 1; j++) {
                st = new StringTokenizer(br.readLine());
                int parentNode = Integer.parseInt(st.nextToken());
                int childNode = Integer.parseInt(st.nextToken());
                parents[childNode] = parentNode;
            }

            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            // A의 조상 리스트 생성
            list = new ArrayList<>();
            list.add(nodeA);
            findRoutes(nodeA);
            List<Integer> parentsA = list;

            // B의 조상 리스트 생성
            list = new ArrayList<>();
            list.add(nodeB);
            findRoutes(nodeB);
            List<Integer> parentsB = list;

            // 조상 리스트를 비교하여 가장 깊은 공통 조상을 찾는다
            answer = 0;  // 기본값을 0으로 설정 (만약 공통 조상이 없다면)
            for (int j = 0; j < Math.min(parentsA.size(), parentsB.size()); j++) {
                int aAncestor = parentsA.get(parentsA.size() - 1 - j);
                int bAncestor = parentsB.get(parentsB.size() - 1 - j);
                if (aAncestor == bAncestor) {
                    answer = aAncestor;  // 공통 조상을 찾으면 갱신
                } else {
                    break;  // 더 이상 공통 조상이 없으므로 종료
                }
            }

            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void findRoutes(int node) {
        if (parents[node] != 0) {  // 루트가 아닌 노드는 부모가 0이 아니므로
            list.add(parents[node]);
            findRoutes(parents[node]);
        }
    }
}
