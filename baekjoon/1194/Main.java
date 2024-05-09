import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static char[][] map;
    static int[][][] moveMap;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int[] mask = {1, 2, 4, 8, 16, 32};
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        moveMap = new int[64][N][M];    // [keySet][row][col]

        Point startP = null;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0') {
                    startP = new Point(i, j, 0, 0);
                }

                for (int k = 0; k < 64; k++) {
                    moveMap[k][i][j] = Integer.MAX_VALUE;
                }
            }
        }

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(startP);
        moveMap[0][startP.r][startP.c] = 0;

        int ans = -1;
        while (!queue.isEmpty()) {
            Point t = queue.poll();

            if (map[t.r][t.c] == '1') {
                ans = t.cnt;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = t.r + dr[d];
                int nc = t.c + dc[d];
                if (isOutOfBound(nr, nc) || map[nr][nc] == '#') {
                    continue;
                }

                char curr = map[nr][nc];
                int nKeys = t.keys;
                if ('A' <= curr && curr <= 'F') {
                    if (!hasKey(t.keys, curr)) {
                        continue;
                    }
                } else if ('a' <= curr && curr <= 'f') {
                    nKeys = updateKeySet(t.keys, curr);
                }

                int nCnt = t.cnt+1;
                if (moveMap[nKeys][nr][nc] <= nCnt) {
                    continue;
                }

                moveMap[nKeys][nr][nc] = nCnt;
                queue.add(new Point(nr, nc, nCnt, nKeys));
            }
        }

        bw.append(String.valueOf(ans));
        bw.flush();
    }

    public static int updateKeySet(int keySet, char key) {
        return (keySet | mask[key-'a']);
    }

    public static boolean hasKey(int keySet, char door) {
        return (keySet & mask[door - 'A']) > 0;
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || N<=r || c<0 || M<=c;
    }

    static class Point {
        int r, c, cnt, keys;
        public Point(int r, int c, int cnt, int keys) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.keys = keys;
        }
    }
}
