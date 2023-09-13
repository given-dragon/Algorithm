import java.io.*;
import java.util.*;

public class Main {
    public static int pillarLength = 0;
    public static int maxBranchLength = 0;
    public static int root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());   // 노드 수
        root = Integer.parseInt(st.nextToken());   // 루트 노드

        ArrayList<Node>[] tree = new ArrayList[N + 1];
        boolean[] isVisited = new boolean[tree.length + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, d));
            tree[b].add(new Node(a, d));
        }

        int gigaNode = getPillarLength(tree, isVisited, root, 0);
        getMaxBranchLength(tree, isVisited, gigaNode, 0);

        sb.append(pillarLength).append(' ').append(maxBranchLength);
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
    public static void getMaxBranchLength(ArrayList<Node>[] tree, boolean[] isVisited, int startNode, int curSumLength) {
        if (tree[startNode].size() == 1)
            maxBranchLength = Math.max(maxBranchLength, curSumLength);

        for (Node nextNode : tree[startNode]) {
            if (!isVisited[nextNode.num]) {
                isVisited[nextNode.num] = true;
                getMaxBranchLength(tree, isVisited, nextNode.num, nextNode.vertexCost + curSumLength);
            }
        }
    }
    public static int getPillarLength(ArrayList<Node>[] tree, boolean[] isVisited, int startNode, int curSumLength) {
        int gigaNode = root;
        isVisited[startNode] = true;

        int childCount = (startNode != root) ? 2 : 1;
        if (tree[startNode].size() != childCount) {
            pillarLength = curSumLength;
            return startNode;
        }

        for (Node nextNode : tree[startNode]) {
            if (!isVisited[nextNode.num]) {
                gigaNode = getPillarLength(tree, isVisited, nextNode.num, curSumLength + nextNode.vertexCost);
            }
        }
        return gigaNode;
    }
}