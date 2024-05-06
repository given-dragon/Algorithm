import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, targetNo1, targetNo2;
    static int[] parent;
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((n1, n2) -> -1*Integer.compare(n1.th, n2.th));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int th = Integer.parseInt(st.nextToken());
            pq.add(new Edge(node1, node2, th));
        }

        st = new StringTokenizer(br.readLine());
        targetNo1 = Integer.parseInt(st.nextToken());
        targetNo2 = Integer.parseInt(st.nextToken());

        while (M-- > 0) {
            Edge e = pq.poll();

            if (!union(e.no1, e.no2)) {
                continue;
            }
            if (find(targetNo1) == find(targetNo2)) {
                bw.append(String.valueOf(e.th));
                bw.flush();
                break;
            }
        }
    }

    public static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    public static boolean union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);
        if (p1 == p2) {
            return false;
        }
        parent[p2] = p1;
        return true;
    }

    static class Edge {
        int no1, no2, th;
        public Edge(int no1, int no2, int th) {
            this.no1 = no1;
            this.no2 = no2;
            this.th = th;
        }
    }
}
