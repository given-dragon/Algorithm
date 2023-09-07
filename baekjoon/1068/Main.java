import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String vertexInfo = br.readLine();
        int targetNode = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] tree = new ArrayList[N];

        int rootNode = 0;
        StringTokenizer st = new StringTokenizer(vertexInfo, " ");

        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) {
                rootNode = i;
                continue;
            }

            if (tree[parent]==null)
                tree[parent] = new ArrayList<>();

            if (i==targetNode)
                continue;

            tree[parent].add(i);
        }

        tree[targetNode] = null;

        sb.append(getLeafNodes(tree, rootNode, targetNode));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int getLeafNodes(ArrayList<Integer>[] tree, int rootNode, int targetNode) {
        if (rootNode == targetNode)
            return 0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[tree.length];
        int leafCount = 0;

        queue.add(rootNode);
        visited[rootNode] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            if (tree[node] == null || tree[node].isEmpty()) {
                leafCount++;
                continue;
            }

            for (Integer child : tree[node]) {
                if (!visited[child]) {
                    queue.add(child);
                    visited[child] = true;
                }
            }
        }
        return leafCount;
    }
}