import java.io.*;
import java.util.*;

public class Main {
    public static int maxLength = 0;
    public static int root = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());   // 노드 수
        ArrayList<Node>[] tree = new ArrayList[N + 1];
        boolean[] isVisited = new boolean[tree.length + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            tree[u].add(new Node(v, len));
            tree[v].add(new Node(u, len));
        }

        ArrayList<Integer> leafList = getLeaf(tree);

        for (int node : leafList) {
            root = node;
            isVisited[root] = true;
            dfs(tree, isVisited, node, 0);
            isVisited = new boolean[tree.length + 1];
        }

        sb.append(maxLength);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static class Node {
        int num, vertexCost;
        Node(int num, int vertexCost) {
            this.num = num;
            this.vertexCost = vertexCost;
        }
    }
    public static ArrayList<Integer> getLeaf(ArrayList<Node>[] tree) {
        ArrayList<Integer> leafList = new ArrayList<>();

        if (tree[root].isEmpty()) {
            leafList.add(root);
        }
        // 루트 노드는 제외
        for (int i = 2; i < tree.length; i++) {
            if (tree[i].size() == 1) {
                leafList.add(i);
            }
        }
        return leafList;
    }

    public static void dfs(ArrayList<Node>[] tree, boolean[] isVisited, int startNode, int curSumLength) {
        if ((root!=startNode) && (tree[startNode].size()==1)) {
            maxLength = Math.max(maxLength, curSumLength);
            return;
        }
        for (Node node : tree[startNode]) {
            if (!isVisited[node.num]) {
                isVisited[node.num] = true;
                dfs(tree, isVisited, node.num, node.vertexCost + curSumLength);
            }
        }
    }
}