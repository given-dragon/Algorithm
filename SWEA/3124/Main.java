import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static Edge[] edgeInfo;
    static int[] parent;
    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            edgeInfo = new Edge[E];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeInfo[i] = new Edge(from, to, weight);
            }
            Arrays.sort(edgeInfo, Comparator.comparingInt(e -> e.weight));

            make(V);

            int cnt = 0;
            long totalWeight = 0;
            for (Edge edge : edgeInfo) {
                if (!union(edge.from, edge.to)) {
                    continue;
                }

                cnt++;
                totalWeight += edge.weight;

                if (cnt == V - 1) { // 선택된 간선의 수
                    break;
                }
            }
            sb.append('#').append(t).append(' ').append(totalWeight).append('\n');
        }

        bw.append(sb);
        bw.flush();
    }

    private static boolean union(int n1, int n2) {
        int root1 = find(n1);
        int root2 = find(n2);
        if (root1 == root2) {
            return false;
        }

        parent[root2] = root1;
        return true;
    }

    private static int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    private static void make(int V) {
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
    }

    static class Edge {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
