import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, targetNo1, targetNo2;
    static List<Node>[] adjList;
    static int[] maxTh;
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        maxTh = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int th = Integer.parseInt(st.nextToken());
            adjList[node1].add(new Node(node2, th));
            adjList[node2].add(new Node(node1, th));
        }

        st = new StringTokenizer(br.readLine());
        targetNo1 = Integer.parseInt(st.nextToken());
        targetNo2 = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> -1*Integer.compare(n1.th, n2.th));
        maxTh[targetNo1] = Integer.MAX_VALUE;
        pq.add(new Node(targetNo1, maxTh[targetNo1]));

        while (!pq.isEmpty()) {
            Node target = pq.poll();

            if (target.th < maxTh[target.no]) {
                continue;
            }

            if (target.no == targetNo2) {
                bw.append(String.valueOf(target.th));
                bw.flush();
                break;
            }

            for (Node child : adjList[target.no]) {
                int nTh = Math.min(target.th, child.th);
                if (maxTh[child.no] >= nTh) {
                    continue;
                }
                maxTh[child.no] = nTh;
                pq.add(new Node(child.no, nTh));
            }
        }
    }

    static class Node {
        int no, th;
        public Node(int no, int th) {
            this.no = no;
            this.th = th;
        }
    }
}
