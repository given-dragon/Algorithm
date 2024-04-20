import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, E;

    static List<Node>[] adjList;
    static int[][] minDist;

    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        minDist = new int[4][N + 1];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
        }

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c, false, false));
            adjList[b].add(new Node(a, c, false, false));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        Node node = new Node(1, 0, 1 == v1, false);
        pq.add(node);
        minDist[node.getIdx()][1] = 0;

        int ans = -1;
        while (!pq.isEmpty()) {
            Node target = pq.poll();

            if (target.isVisited() && target.no==N) {
                ans = target.w;
                break;
            }

            if (minDist[target.getIdx()][target.no] < target.w) {
                continue;
            }

            for (Node child : adjList[target.no]) {
                int nw = target.w + child.w;

                boolean vV1 = target.visitedV1 || (child.no == v1);
                boolean vV2 = target.visitedV2 || (child.no == v2);
                Node next = new Node(child.no, nw, vV1, vV2);

                if (minDist[next.getIdx()][child.no] <= nw) {
                    continue;
                }

                minDist[next.getIdx()][child.no] = nw;
                pq.add(next);
            }
        }
        sb.append(ans);

        bw.append(sb);
        bw.flush();
    }
    static class Node {
        int no, w;
        boolean visitedV1, visitedV2;
        public Node (int no, int w, boolean v1, boolean v2) {
            this.no = no;
            this.w = w;
            this.visitedV1 = v1;
            this.visitedV2 = v2;
        }

        public boolean isVisited() {
            return visitedV1 && visitedV2;
        }

        public int getIdx() {
            if (!visitedV1 && !visitedV2) {
                return 0;
            }
            if (visitedV1 && !visitedV2) {
                return 1;
            }
            if (!visitedV1 && visitedV2) {
                return 2;
            }
            return 3;
        }
    }
}
