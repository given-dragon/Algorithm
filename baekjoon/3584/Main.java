import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());   // 노드 수
            ArrayList<Node>[] tree = new ArrayList[N + 1];
            boolean[] isVisited = new boolean[tree.length];

            for (int i = 1; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                tree[A].add(new Node(B, false));
                tree[B].add(new Node(A, true));
            }

            st = new StringTokenizer(br.readLine(), " ");
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            dfs(tree, isVisited, node1);
            int NCA =dfs(tree, isVisited, node2);

            sb.append(NCA).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static class Node {
        int num;
        boolean isParent;
        Node(int num, boolean isParent) {
            this.num = num;
            this.isParent = isParent;
        }
    }
    public static int dfs(ArrayList<Node>[] tree, boolean[] isVisited, int nextNode) {
        int result = nextNode;

        if (isVisited[nextNode]) {  // 만약 방문했던 부모이면 공통 조상
            return nextNode;
        }

        isVisited[nextNode] = true;

        for (Node node : tree[nextNode]) {
            if (node.isParent) {    // 부모인 경우만 탐색 진행
                result = dfs(tree, isVisited, node.num);
            }
        }
        return result;
    }
}