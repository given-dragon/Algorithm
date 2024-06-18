import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(M, Comparator.comparingInt(e -> e.w));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(n1, n2, w));
        }

        int cnt = 0;
        int maxWeight = 0;
        int weightSum = 0;
        while (!pq.isEmpty()) {
            Edge target = pq.poll();
            if (!union(target.n1, target.n2)) {
                continue;
            }

            maxWeight = Math.max(maxWeight, target.w);
            weightSum += target.w;
            if (cnt == N - 1) {
                break;
            }
        }

        System.out.println(weightSum - maxWeight);
    }

    public static int find(int n) {
        if (n == parent[n]) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    public static boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        if (p1 == p2) {
            return false;
        }
        parent[p2] = p1;
        return true;
    }

    static class Edge {
        int n1, n2, w;
        public Edge(int n1, int n2, int w) {
            this.n1 = n1;
            this.n2 = n2;
            this.w = w;
        }
    }
}
