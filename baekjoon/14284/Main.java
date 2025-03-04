import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    int n, m, s, t;
    List<Node>[] adjList;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 정점의 개수
        int m = Integer.parseInt(st.nextToken());  // 간선의 개수

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        int[] minDist = dijkstra(n);
        System.out.println(minDist[t]);
    }

    private int[] dijkstra(int n) {
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, 500_001);
        minDist[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        pq.add(new Node(s, minDist[s]));

        while (!pq.isEmpty()) {
            Node target = pq.poll();

            if (target.w > minDist[target.v]) {
                continue;
            }

            for (Node child : adjList[target.v]) {
                int nW = target.w + child.w;
                if (nW >= minDist[child.v]) {
                    continue;
                }

                minDist[child.v] = nW;
                pq.add(new Node(child.v, minDist[child.v]));
            }
        }
        return minDist;
    }
}

class Node {
    int v, w;
    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
