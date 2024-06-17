import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int V;
    static List<Node>[] adjList;
    static long[] minDist;
    public static void main(String[] args) throws Exception {
        V = Integer.parseInt(br.readLine());
        adjList = new List[V+1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        int cnt = V;
        while (cnt-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            while (true) {
                int child = Integer.parseInt(st.nextToken());
                if (child < 0) {
                    break;
                }
                int dist = Integer.parseInt(st.nextToken());
                adjList[no].add(new Node(child, dist));
            }
        }

        minDist = new long[V + 1];
        Node maxNode = findMaxNode(1);
        maxNode = findMaxNode(maxNode.v);

        System.out.println(maxNode.w);
    }

    private static Node findMaxNode(int startVtx) {
        Arrays.fill(minDist, Long.MAX_VALUE);

        Queue<Node> queue = new ArrayDeque<>();
        minDist[startVtx] = 0;
        queue.add(new Node(startVtx, minDist[startVtx]));

        while (!queue.isEmpty()) {
            Node target = queue.poll();

            if (target.w > minDist[target.v]) {
                continue;
            }

            for (Node child : adjList[target.v]) {
                if (child.w + target.w >= minDist[child.v]) {
                    continue;
                }

                minDist[child.v] = child.w + target.w;
                queue.add(new Node(child.v, minDist[child.v]));
            }
        }

        int maxVtx = 0;
        long maxDist = 0;
        for (int i = 1; i <= V; i++) {
            if (minDist[i] != Long.MAX_VALUE && maxDist < minDist[i]) {
                maxVtx = i;
                maxDist = minDist[i];
            }
        }

        return new Node(maxVtx, maxDist);
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
