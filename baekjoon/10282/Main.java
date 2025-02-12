import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    static final int MAX = 90_000_001;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int n, d, c;
    List<Node>[] adjList;

    public void solve() throws Exception {
        int testCaseCount = Integer.parseInt(br.readLine());
        while (testCaseCount-- > 0) {
            run();
        }
        bw.append(sb);
        bw.flush();
    }

    private void run() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());  // 컴퓨터 개수
        d = Integer.parseInt(st.nextToken());  // 의존성 개수
        c = Integer.parseInt(st.nextToken());  // 해킹 시작 컴퓨터 번호

        adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            adjList[b].add(new Node(a, s));
        }

        dijkstra(c);
    }

    private void dijkstra(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, MAX);
        dist[startNode] = 0;
        pq.add(new Node(startNode, dist[startNode]));

        while (!pq.isEmpty()) {
            Node t = pq.poll();
            if (t.w > dist[t.v]) {
                continue;
            }

            for (Node child : adjList[t.v]) {
                int nW = dist[t.v] + child.w;
                if (nW >= dist[child.v]) {
                    continue;
                }

                dist[child.v] = nW;
                pq.add(new Node(child.v, dist[child.v]));
            }
        }

        calcInfectedComputerInfo(dist);
    }

    private void calcInfectedComputerInfo(int[] dist) {
        int infectedComputerCount = 0;
        int lastInfectedTime = -1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == MAX) {
                continue;
            }

            infectedComputerCount++;
            lastInfectedTime = Math.max(lastInfectedTime, dist[i]);
        }

        sb.append(infectedComputerCount).append(' ').append(lastInfectedTime).append('\n');
    }
}

class Node {
    int v, w;
    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
