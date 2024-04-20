import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static char[][] map;
    static boolean[][][] visited;   //[isBreak][r][c]
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[2][N][M]; // isBread => false 0, true 1
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            Point target = queue.poll();

            if (target.r == N - 1 && target.c == M - 1) {
                answer = target.t;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = target.r + dr[d];
                int nc = target.c + dc[d];
                if (isOutOfBound(nr, nc) || visited[target.isBreak][nr][nc]) {
                    continue;
                }

                if (target.isBreak == 0 && map[nr][nc] == '1') {
                    Point next = new Point(nr, nc, target.t+1, 1);
                    queue.add(next);
                    visited[1][nr][nc] = true;
                    continue;
                }

                if (map[nr][nc] == '0') {
                    Point next = new Point(nr, nc, target.t+1, target.isBreak);
                    queue.add(next);
                    visited[target.isBreak][nr][nc] = true;
                }
            }
        }

        bw.append(String.valueOf(answer));
        bw.flush();
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || N<=r || c<0 || M<=c;
    }

    static class Point {
        int r, c, t, isBreak;
        public Point(int r, int c, int t, int isBreak) {
            this.r=r;
            this.c=c;
            this.t=t;
            this.isBreak = isBreak;
        }
    }
}
