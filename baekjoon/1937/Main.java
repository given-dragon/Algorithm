import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] map, dp;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dp = new int[n][n];

        List<Bamboo> bambooList = new ArrayList<>(n*n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                bambooList.add(new Bamboo(i, j, map[i][j]));
            }
        }

        bambooList.sort(Comparator.comparingInt(b -> b.cnt));
        for (Bamboo b : bambooList) {
            int r = b.r;
            int c = b.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || n <= nr || nc < 0 || n <= nc) {
                    continue;
                }

                if (map[r][c] >= map[nr][nc]) {
                    continue;
                }

                dp[nr][nc] = Math.max(dp[r][c] + 1, dp[nr][nc]);
            }
        }

        long max = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                max = Math.max(max, dp[r][c]);
            }
        }

        System.out.println(max + 1);
    }

    static class Bamboo {
        int r, c, cnt;

        public Bamboo(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
