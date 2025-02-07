import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int N, Q;
    List<Node>[] adjList;

    public void solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            adjList[p].add(new Node(q, r));
            adjList[q].add(new Node(p, r));
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());  // 유사도 제한
            int v = Integer.parseInt(st.nextToken());  // 영상 번호

            int cnt = bfs(v, k);
            sb.append(cnt).append('\n');
        }

        bw.append(sb);
        bw.flush();
        bw.close();
    }

    private int bfs(int startNode, int k) {
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;

        Queue<Node> pq = new ArrayDeque<>();
        visited[startNode] = true;
        pq.add(new Node(startNode, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Node target = pq.poll();

            for (Node child : adjList[target.v]) {
                if (visited[child.v]) {
                    continue;
                }

                int nW = Math.min(target.w, child.w);
                if (nW >= k) {
                    cnt++;
                }

                visited[child.v] = true;
                pq.add(new Node(child.v, nW));
            }
        }
        return cnt;
    }
}

class Node {
    int v;
    int w;
    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
