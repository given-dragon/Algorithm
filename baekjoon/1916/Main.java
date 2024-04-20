import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();


    static int N, M;
    static List<Node>[] adjList;
    static long[] minDist;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());


        minDist = new long[N+1];
        Arrays.fill(minDist, Long.MAX_VALUE);
        adjList = new ArrayList[N+1];
        for (int i=1; i<=N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i=0; i<M ;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        int startNo = Integer.parseInt(st.nextToken());
        int endNo = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.w));
        pq.add(new Node(startNo, 0));
        minDist[startNo] = 0;

        while(!pq.isEmpty()) {
            Node target = pq.poll();

            if (target.no == endNo) {
                sb.append(target.w);
                break;
            }

            if (minDist[target.no] < target.w) {
                continue;
            }

            for (Node child : adjList[target.no]) {
                long nw = minDist[target.no] + child.w;
                if (minDist[child.no] <= nw) {
                    continue;
                }

                minDist[child.no] = nw;
                pq.add(new Node(child.no, nw));
            }
        }

        bw.append(sb);
        bw.flush();
    }

    static class Node {
        int no;
        long w;
        public Node(int no, long w) {
            this.no = no;
            this.w = w;
        }
    }
}
