import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static int m, n;
    static int[] parent;

    public static void main(String[] args) throws Exception {

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());  // 집의 수
            n = Integer.parseInt(st.nextToken());  // 길의 수
            if (m == 0 && n == 0) {
                break;
            }

            parent = new int[m + 1];
            for (int i = 0; i <= m; i++) {
                parent[i] = i;
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.z));

            int totalPrice = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                totalPrice += z;

                pq.add(new Edge(x, y, z));
            }

            int priceSum = 0;
            int edgeCnt = 0;
            int maxEdgeCnt = (m-1);
            while (edgeCnt < maxEdgeCnt) {

                Edge target = pq.poll();
                if (!union(target.x, target.y)) {
                    continue;
                }

                priceSum += target.z;
                edgeCnt++;
            }

            sb.append(totalPrice - priceSum).append('\n');
        }
        bw.append(sb);
        bw.flush();
        bw.close();
    }

    private static boolean union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) {
            return false;
        }
        parent[parent2] = parent1;
        return true;
    }

    private static int find(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }
}

class Edge {
    int x, y, z;
    public Edge(int x, int y, int z) {
        this.x = x;  // v1
        this.y = y;  // v2
        this.z = z;  // dist
    }
}