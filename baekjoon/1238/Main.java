import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, M, X;
    static List<Node>[] adjList;

    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adjList[s].add(new Node(e, t));
        }


        int[] minTime = dijkstra(adjList, X, -1);
        int maxDist = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            }
            int[] time = dijkstra(adjList, i, X);
            maxDist = Math.max(maxDist, minTime[i]+time[X]);
        }

        sb.append(maxDist);

        bw.append(sb);
        bw.flush();
    }

    public static int[] dijkstra(List<Node>[] adjList, int startNo, int endNo) {
        int[] minTime = new int[N + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.t));
        pq.add(new Node(startNo, 0));
        minTime[startNo] = 0;

        while (!pq.isEmpty()) {
            Node target = pq.poll();

            if (endNo > 0 && target.no == endNo) {
                return minTime;
            }

            if (minTime[target.no] < target.t) {
                continue;
            }

            for (Node child : adjList[target.no]) {
                int nt = minTime[target.no] + child.t;
                if (minTime[child.no] <= nt) {
                    continue;
                }

                minTime[child.no] = nt;
                pq.add(new Node(child.no, nt));
            }
        }
        return minTime;
    }

    static class Node {
        int no, t;
        public Node (int no, int t) {
            this.no = no;
            this.t = t;
        }
    }
}
