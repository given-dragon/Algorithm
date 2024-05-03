import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, W, H, totalCnt, maxCnt;
    static int[][] originMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());   // 열
            H = Integer.parseInt(st.nextToken());   // 행

            totalCnt = 0;
            originMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < W; j++) {
                    originMap[i][j] = Integer.parseInt(st.nextToken());
                    if (originMap[i][j] > 0) {
                        totalCnt++;
                    }
                }
            }

            maxCnt = 0;
            recur(0, originMap, 0);

            sb.append('#').append(tc).append(' ').append(totalCnt - maxCnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static void recur(int nCnt, int[][]map, int breakSum) {
        if (nCnt == N || breakSum == totalCnt) {
            // 남은 개수 파악
            maxCnt = Math.max(maxCnt, breakSum);
            return;
        }

        // 공을 시작할 열 찾기
        for (int j = 0; j < W; j++) {
            if (map[H - 1][j] == 0) {   // 맵의 가장 바닥이 비어있다면 다음 열 선택
                continue;
            }

            // 맵 복사
            int[][] copyMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                copyMap[i] = Arrays.copyOf(map[i], W);
            }

            // 벽돌이 있는 행 찾기
            int i = findPosition(copyMap, j);

            // 부수기
            int breakCnt = breakStone(copyMap, i, j);

            // 중력
            gravity(copyMap);

            // 다음 열 찾기
            recur(nCnt + 1, copyMap, breakSum + breakCnt);
        }
    }

    public static void gravity(int[][] map) {
        for (int j = 0; j < W; j++) {
            int emptyCnt = 0;
            for (int i = H-1; i >= 0; i--) {
                if (map[i][j] == 0) {
                    emptyCnt++;
                    continue;
                }
                if (emptyCnt == 0) {
                    continue;
                }
                map[i+emptyCnt][j] = map[i][j];
                map[i][j] = 0;
            }
        }
    }

    public static int findPosition(int[][] map, int c) {
        int r = 0;
        while (map[r][c] == 0) {
            r++;
        }
        return r;
    }

    public static int breakStone(int[][] map, int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c, map[r][c]});  // [r, c, d]
        map[r][c] = 0;

        int breakCnt = 1;
        while (!queue.isEmpty()) {
            int[] t = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = t[0];
                int nc = t[1];
                for (int m = 1; m < t[2]; m++) {
                     nr += dr[d];
                     nc += dc[d];
                    if (isOutOfBound(nr, nc)) {
                        break;
                    }
                    if (map[nr][nc] == 0) {
                        continue;
                    }
                    queue.add(new int[]{nr, nc, map[nr][nc]});
                    map[nr][nc] = 0;
                    breakCnt++;
                }
            }
        }
        return breakCnt;
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || H<=r || c<0 || W<=c;
    }
}