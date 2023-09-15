import java.io.*;
import java.util.*;

public class Main {
    public static int[] parents;
    public static int[] nodes;
    public static StringTokenizer st;
    public static int targetNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());   // 노드 수
            int k = Integer.parseInt(st.nextToken());   // 사촌의 수를 구해야 하는 노드번호 k
            if ((n == 0) && (k == 0))
                break;

            parents = new int[n + 1];
            nodes = new int[n + 1];
            parents[0] = -1;
            nodes[0] = -1;

            st = new StringTokenizer(br.readLine(), " ");
            makeTree(n, k);
            int cousinCount = findCousin(n);

            sb.append(cousinCount).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static void makeTree(int n, int k) {
        int parentIdx = -1;
        for (int i = 1; i <= n; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
            if (nodes[i] == k)  // 타켓 노드의 배열 idx저장
                targetNode = i;
            if (nodes[i-1] + 1 < nodes[i])  // 부모의 노드가 바뀌면 부모idx++
                parentIdx++;
            parents[i] = parentIdx;
        }
    }
    public static int findCousin(int n) {
        int cousinCount = 0;
        int targetGrandParent = parents[parents[targetNode]];
        for (int i = 1; i <= n; i++) {
            if (parents[i] == parents[targetNode])  // 해당 노드의 부모가 target의 부모와 같은 노드는 제외
                continue;
            if (parents[parents[i]] == targetGrandParent)    // 두 노드의 부모의 부모가 같으면 체크
                cousinCount++;
        }
        return cousinCount;
    }
}