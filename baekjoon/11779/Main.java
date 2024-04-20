import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static List<Node>[] adjList;
    static int[] minWeight, order;
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        minWeight = new int[n + 1];
        order = new int[n + 1];

        Arrays.fill(minWeight, Integer.MAX_VALUE);
        adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Node(e, w, null));
        }

        st = new StringTokenizer(br.readLine());
        int startNo = Integer.parseInt(st.nextToken());
        int endNo = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        pq.add(new Node(startNo, 0, new ArrayList<>()));
        pq.peek().dir.add(startNo);
        minWeight[startNo] = 0;


        while (!pq.isEmpty()) {
            Node target = pq.poll();

            if (target.no == endNo) {
                sb.append(minWeight[endNo]).append('\n');
                sb.append(target.dir.size()).append('\n');
                for (int no : target.dir) {
                    sb.append(no).append(' ');
                }
                break;
            }

            if (target.w > minWeight[target.no]) {
                continue;
            }

            for (Node child : adjList[target.no]) {
                int nw = child.w + minWeight[target.no];
                if (nw >= minWeight[child.no]) {
                    continue;
                }
                minWeight[child.no] = nw;
                ArrayList<Integer> dir = new ArrayList<>(target.dir);
                dir.add(child.no);
                Node next = new Node(child.no, nw, dir);
                pq.add(next);
            }
        }

        bw.append(sb);
        bw.flush();
    }

    static class Node {
        int no, w;
        List<Integer> dir;
        public Node(int no, int w, ArrayList<Integer> dir) {
            this.no = no;
            this.w = w;
            this.dir = dir;
        }
    }
}
