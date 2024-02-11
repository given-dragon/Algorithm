import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static final int INF = 3_000_000;
    static ArrayList<Node>[] nodeInfo;
    static int[] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] split = br.readLine().split(" ");
        int V = Integer.parseInt(split[0]);
        int E = Integer.parseInt(split[1]);
        int K = Integer.parseInt(br.readLine()) - 1;

        // init node info
        nodeInfo = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            nodeInfo[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            split = br.readLine().split(" ");
            int u = Integer.parseInt(split[0]) - 1;
            int v = Integer.parseInt(split[1]) - 1;
            int w = Integer.parseInt(split[2]);

            nodeInfo[u].add(new Node(v, w));
        }

        // init dist
        dist = new int[V];
        Arrays.fill(dist, INF);

        dijkstra(K);

        for (int val : dist) {
            sb.append(val == INF ? "INF" : val).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        dist[start] = 0;
        pq.add(new Node(start, dist[start]));

        while (!pq.isEmpty()) {
            Node next = pq.poll();
            if (dist[next.v] < next.w) {
                continue;
            }

            for (Node n : nodeInfo[next.v]) {
                int nDist = dist[next.v] + n.w;
                if (dist[n.v] > nDist) {
                    dist[n.v] = nDist;
                    pq.add(new Node(n.v, nDist));
                }
            }
        }
    }
    static class Node {
        int v, w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
