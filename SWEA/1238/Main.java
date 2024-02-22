import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static Set<Integer>[] nodeInfo;
    static boolean[] isVisited;
    public static void main(String[] args) throws Exception {

        for (int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int len = Integer.parseInt(st.nextToken());
            int startNode = Integer.parseInt(st.nextToken());

            nodeInfo = new Set[101];
            isVisited = new boolean[101];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = len>>1; i > 0; i--) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if (nodeInfo[from] == null) {
                    nodeInfo[from] = new HashSet<>();
                }
                if (nodeInfo[to] == null) {
                    nodeInfo[to] = new HashSet<>();
                }

                nodeInfo[from].add(to);
            }

            int lastMaxNode = bfs(startNode);
            sb.append('#').append(t).append(' ').append(lastMaxNode).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static int bfs(int startNode) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startNode);
        isVisited[startNode] = true;

        int lastMaxNode = 0;
        while (!queue.isEmpty()) {
            int maxNode = 0;
            int currSize = queue.size();
            while (currSize-- > 0) {

                int target = queue.poll();
                maxNode = Math.max(maxNode, node);
                for (int node : nodeInfo[target]) {
                    if (isVisited[node]) {
                        continue;
                    }

                    queue.offer(node);
                    isVisited[node] = true;
                }
            }

            lastMaxNode = maxNode;
        }

        return lastMaxNode;
    }
}
