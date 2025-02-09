import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n;
    int[][] map;
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};

    public void solve() throws Exception {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                int weight = line.charAt(j) - '0';
                map[i][j] = weight == 1 ? 0 : 1;
            }
        }

        System.out.println(dijkstra());
    }

    private int dijkstra() {
        int[][] changeCnts = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(changeCnts[i], 101);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        changeCnts[0][0] = 0;
        pq.add(new Node(0, 0, changeCnts[0][0]));

        while (!pq.isEmpty()) {
            Node t = pq.poll();
            if (t.w > changeCnts[t.r][t.c]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = t.r + dr[i];
                int nc = t.c + dc[i];
                if (isOutOfBound(nr, nc)) {
                    continue;
                }

                int nw = t.w + map[nr][nc];
                if (nw >= changeCnts[nr][nc]) {
                    continue;
                }

                changeCnts[nr][nc] = nw;
                pq.add(new Node(nr, nc, changeCnts[nr][nc]));
            }
        }

        return changeCnts[n - 1][n - 1];
    }

    private boolean isOutOfBound(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}

class Node {
    int r, c, w;
    public Node(int r, int c, int w) {
        this.r = r;
        this.c = c;
        this.w = w;
    }
}
