import java.util.*;
import java.io.*;

/**
 * 각 [r, c] 마다 BFS를 진행
 */
class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, M, cityCnt, costThres, maxCoverCnt;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 도시 크기
            M = Integer.parseInt(st.nextToken());   // 하나의 집이 지불 가능한 비용

            maxCoverCnt = 0;


            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j << 1);
                    if (map[i][j] == '1') {
                        cityCnt++;
                    }
                }
            }

            costThres = cityCnt * M;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int candiCnt = findMaxCoverCnt(i, j);
                    maxCoverCnt = Math.max(maxCoverCnt, candiCnt);
                }
            }

            sb.append('#').append(tc).append(' ').append(maxCoverCnt).append('\n');
        }

        bw.append(sb);
        bw.flush();
    }

    private static int findMaxCoverCnt(int startR, int startC) {
        visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startR, startC});
        visited[startR][startC] = true;
        int K = 1;

        int currMaxCityCnt = 0;
        int coverCityCnt = 0;
        while (!queue.isEmpty() && (calcCurrCost(K) <= costThres)) {
            int qSize = queue.size();

            while (qSize-- > 0) {
                int[] target = queue.poll();

                if (map[target[0]][target[1]] == '1') {
                    coverCityCnt++;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = target[0] + dr[d];
                    int nc = target[1] + dc[d];
                    if (isOutOfBound(nr, nc) || visited[nr][nc]) {
                        continue;
                    }

                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }

            if (calcCurrCost(K) <= (coverCityCnt * M)) {
                currMaxCityCnt = Math.max(currMaxCityCnt, coverCityCnt);
            }

            K++;
        }

        return currMaxCityCnt;
    }

    public static boolean isOutOfBound(int r, int c) {
        return r < 0 || N <= r || c < 0 || N <= c;
    }

    public static int calcCurrCost(int K) {
        int kM1 = K-1;
        return (K * K) + (kM1 * kM1);
    }
}