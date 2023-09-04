import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int node1 = Integer.parseInt(st.nextToken())-1;
            int node2 = Integer.parseInt(st.nextToken())-1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        int[] myParent = bfs(N, tree);

        for (int i = 1; i < N; i++) {
            sb.append(myParent[i] + 1).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int[] bfs(int N, ArrayList<Integer>[] tree) {
        boolean[] visited = new boolean[N];
        int[] myParent = new int[N];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Integer parentNode = queue.poll();
            for (int childNode : tree[parentNode]) {
                if (!visited[childNode]) {
                    queue.add(childNode);
                    visited[childNode] = true;

                    myParent[childNode] = parentNode;
                }
            }
        }
        return myParent;
    }
}