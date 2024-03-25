import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static char[][] map;
    static int[][] mvCntMap;
    static Point Nam;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        mvCntMap = new int[N][M];
        for (int[] row : mvCntMap) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<Point> queue = new ArrayDeque<>();
        for (int n=0; n<N; n++) {
            String line = br.readLine();
            for (int m=0; m<M; m++) {
                map[n][m] = line.charAt(m);
                if (map[n][m]=='N') {
                    Nam = new Point(n, m, 0);
                    continue;
                }
                if (map[n][m]=='G') {
                    queue.add(new Point(n, m, 0));
                    mvCntMap[n][m] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            Point target = queue.poll();

            if (mvCntMap[target.r][target.c] < target.t) {
                continue;
            }

            target.t++;
            for (int d=0; d<4; d++) {
                int nr = target.r + dr[d];
                int nc = target.c + dc[d];
                if (isOutOfBound(nr, nc)) {
                    continue;
                }
                if (mvCntMap[nr][nc] <= target.t) {
                    continue;
                }

                mvCntMap[nr][nc] = target.t;
                queue.add(new Point(nr, nc, mvCntMap[nr][nc]));
            }
        }

        String answer = "No";
        queue = new ArrayDeque<>();
        queue.add(Nam);
        while (!queue.isEmpty()) {
            Point target = queue.poll();

            if (map[target.r][target.c] == 'D') {
                answer = "Yes";
                break;
            }

            if (mvCntMap[target.r][target.c] < target.t) {
                continue;
            }

            target.t++;
            for (int d=0; d<4; d++) {
                int nr = target.r + dr[d];
                int nc = target.c + dc[d];
                
                if (isOutOfBound(nr, nc) || map[nr][nc]=='#') {
                    continue;
                }
                if (mvCntMap[nr][nc] <= target.t) {
                    continue;
                }

                mvCntMap[nr][nc] = target.t;
                queue.add(new Point(nr, nc, mvCntMap[nr][nc]));
            }
        }
        
        System.out.println(answer);
        return;
    }

    public static boolean isOutOfBound(int r, int c) {
        return r < 0 || N <= r || c < 0 || M <= c;
    }
    static class Point {
        int r, c, t;
        public Point(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
}

