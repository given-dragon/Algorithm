import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean[][] map; // true: 1, false: 0
    static int N, M;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j<<1) == '1';
            }
        }

        int emptyCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    continue;
                }

                bfs(new Point(i, j));
                emptyCnt++;
            }
        }

        System.out.println(emptyCnt);
    }

    private static void bfs(Point startPoint) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(startPoint);
        while (!queue.isEmpty()) {
            Point t = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = calcNewCoord(t.r, dr[d], N);
                int nc = calcNewCoord(t.c, dc[d], M);
                if (map[nr][nc]) {
                    continue;
                }

                queue.add(new Point(nr, nc));
                map[nr][nc] = true;
            }
        }
    }

    private static int calcNewCoord(int rc, int drc, int NM) {
        int nrc = rc + drc;
        if (nrc < 0) {
            nrc = NM-1;
        } else if (nrc == NM) {
            nrc = 0;
        }
        return nrc;
    }

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
