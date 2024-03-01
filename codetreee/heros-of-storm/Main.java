import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m, t;
    static int[][] map;
    static List<Coord> stormList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 방 row
        m = Integer.parseInt(st.nextToken());   // 방 col
        t = Integer.parseInt(st.nextToken());   // time

        map = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] < 0) {
                    stormList.add(new Coord(i, j)); // 0번째 인덱스가 윗 폭풍, 1번째 인덱스가 아랫 폭풍
                }
            }
        }

        while (t-- > 0) {
            splashDust();
            storming(0);
            storming(1);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    ans+=map[i][j];
                }
            }
        }
        System.out.println(ans);
    }

    static int[][] sdr = {{-1, 0, 1, 0}, {1, 0, -1, 0}};
    static int[] sdc = {0, 1, 0, -1};
    public static void storming(int offset) {
        Coord s = stormList.get(offset==0 ? 0 : 1);

        int nr = s.r + (offset==0 ? -2 : 2);
        int nc = s.c;
        int d = 0;
        while (map[nr][nc] >= 0) {
            map[nr-sdr[offset][d]][nc-sdc[d]] = map[nr][nc];

            if (isOutOfBound(nr+sdr[offset][d], nc+sdc[d]) || (d==2 && nr==s.r)) {
                d++;
            }
            nr += sdr[offset][d];
            nc += sdc[d];
        }
        map[s.r][s.c+1] = 0;
    }
    public static void splashDust() {
        int[][] splashMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] < 0) {    // 폭풍일 경우 해당 칸 확산 취소
                    continue;
                }

                int splashOffset = map[i][j] / 5;
                splashMap[i][j] += map[i][j];
                for (int d = 0; d < 4; d++) {
                    int nr = i+dr[d];
                    int nc = j+dc[d];

                    if (isOutOfBound(nr, nc) || map[nr][nc] < 0) {
                        continue;
                    }

                    splashMap[nr][nc] += splashOffset;
                    splashMap[i][j] -= splashOffset;
                }
            }
        }

        splashMap[stormList.get(0).r][stormList.get(0).c] = -1;
        splashMap[stormList.get(1).r][stormList.get(1).c] = -1;
        map = splashMap;
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || n<=r || c<0 || m<=c;
    }

    static class Coord {
        int r, c;
        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}