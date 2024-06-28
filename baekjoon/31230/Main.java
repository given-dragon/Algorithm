import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, A, B;
    static List<Node>[] adjList;
    static long[] minDistA, minDistB;
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        minDistA = new long[N + 1];
        minDistB = new long[N + 1];
        Arrays.fill(minDistA, Long.MAX_VALUE);
        Arrays.fill(minDistB, Long.MAX_VALUE);

        adjList = new List[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        dijkstra(minDistA, A);
        dijkstra(minDistB, B);

        int size = 0;
        long minDist = minDistA[B];
        int[] marking = new int[N+1];
        marking[A] = 1;
        marking[B] = 1;
        StringBuilder sb = new StringBuilder();
        for (int c = 1; c <= N; c++) {
            if (c == A || c == B || ((minDistA[c] + minDistB[c]) == minDist)) {
                sb.append(c).append(' ');
                size++;
            }
        }

        sb.insert(0, '\n').insert(0, size);
        System.out.println(sb);
    }

    private static void dijkstra(long[] minDist, int startNode) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(n -> n.w));
        minDist[startNode] = 0;
        Node start = new Node(startNode, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node t = queue.poll();

            if (minDist[t.v] < t.w) {
                continue;
            }

            for (Node child : adjList[t.v]) {
                long nw = t.w + child.w;
                if (nw >= minDist[child.v]) {
                    continue;
                }

                minDist[child.v] = nw;
                queue.add(new Node(child.v, nw));
            }
        }
    }

    static class Node {
        int v;
        long w;
        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }
}
