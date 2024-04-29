import java.io.*;
import java.util.*;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static char[][] processor;
    static int N, maxCoreCnt, minWireLen;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static List<Point> coreList;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            processor = new char[N][N];
            coreList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    processor[i][j] = line.charAt(j<<1);
                    if (processor[i][j] == '0') {
                        continue;
                    }
                    if (i==0||j==0||i==N-1||j==N-1) {
                        continue;
                    }
                    coreList.add(new Point(i, j));
                }
            }

            maxCoreCnt = 0;
            minWireLen = Integer.MAX_VALUE;
            recur(0, 0, 0);
            sb.append('#').append(tc).append(' ').append(minWireLen).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static void recur(int idx, int len, int coreCnt) {
        if (coreCnt + (coreList.size() - idx) < maxCoreCnt) {
            return;
        }
        if (idx == coreList.size()) {
            if (coreCnt > maxCoreCnt) {
                maxCoreCnt = coreCnt;
                minWireLen = len;
            }
            else if (coreCnt == maxCoreCnt) {
                minWireLen = Math.min(minWireLen, len);
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int result = connectCore(idx, d);
            recur(idx + 1, len + result, coreCnt + 1);
            removeWire(idx, d, result);
        }
    }

    public static int connectCore(int idx, int dir) {
        int nr = coreList.get(idx).r + dr[dir];
        int nc = coreList.get(idx).c + dc[dir];
        int len = 0;
        while (!isOutOfBound(nr, nc)) {
            if (processor[nr][nc] != '0') {
                removeWire(idx, dir, len);
                return 0;
            }
            processor[nr][nc] = '2';
            len++;

            nr += dr[dir];
            nc += dc[dir];
        }
        return len;
    }

    public static void removeWire(int idx, int dir, int len) {
        int nr = coreList.get(idx).r + dr[dir];
        int nc = coreList.get(idx).c + dc[dir];

        while (len-- > 0) {
            processor[nr][nc] = '0';
            nr += dr[dir];
            nc += dc[dir];
        }
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || N<=r || c<0 || N<=c;
    }

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}