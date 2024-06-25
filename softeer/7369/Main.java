import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] map;
    static int[][][] maxMap;  //[2배 여부][r][c]

    static int[] dr = {0, 1};
    static int[] dc = {1, 0};
    public static void main(String[] args) throws Exception{

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        maxMap = new int[2][n][n];

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> queue = new ArrayDeque<>();
        maxMap[0][0][0] = map[0][0];
        queue.add(new Point(0, 0, map[0][0], 0));

        while(!queue.isEmpty()) {
            Point t = queue.poll();

            if (t.w < maxMap[t.doubled][t.r][t.c]) {
                continue;
            }

            for (int i=0; i<2; i++) {
                int nr = t.r + dr[i];
                int nc = t.c + dc[i];
                if (nr<0 || n<=nr || nc<0 || n<=nc) {
                    continue;
                }

                int nw = t.w+map[nr][nc];
                if (maxMap[t.doubled][nr][nc] < nw) {
                    maxMap[t.doubled][nr][nc] = nw;
                    queue.add(new Point(nr, nc, nw, t.doubled));
                }

                // 두배를 하지 않았다면 두배 한것도 계산
                nw = t.w + (map[nr][nc] << 1);
                if (!(t.doubled>0) && maxMap[1][nr][nc] < nw) {
                    maxMap[1][nr][nc] = nw;
                    queue.add(new Point(nr, nc, nw, 1));
                }
            }
        }

        System.out.println(maxMap[1][n-1][n-1]);
    }

    static class Point {
        int r, c, w, doubled;
        public Point(int r, int c, int w, int doubled) {
            this.r = r;
            this.c = c;
            this.w = w;
            this.doubled = doubled;
        }
    }
}
