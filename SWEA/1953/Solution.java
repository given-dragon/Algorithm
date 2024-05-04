import java.io.*;
import java.util.*;

/**
 * 각 구조물별 dr, dx 설정
 * 시작지점부터 레벨별 BFS
 *
 * [파이프 매칭 조건]
 *  target의 파이프 방향으로 탐색
 *  target의 한 방향에 연결된 child의 파이프가 연결되면 큐에 추가
 *  0 -> 2, 2 -> 0
 *  1 -> 3, 3 -> 1
 *  연결 판단 => abs(targetDir - forEach(childDirArr)) == 2
 *
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, M, R, C, L;
    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int[][] dirArr = {
        {},
        {0, 1, 2, 3},
        {0, 2},
        {1, 3},
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 0}
    };

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // r
            M = Integer.parseInt(st.nextToken());   // c
            R = Integer.parseInt(st.nextToken());   // startR
            C = Integer.parseInt(st.nextToken());   // startC
            L = Integer.parseInt(st.nextToken());   // time

            map = new int[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j << 1)-'0';
                }
            }

            Queue<Point> queue = new ArrayDeque<>();
            queue.add(new Point(R, C));
            visited[R][C] = true;

            int candiPosCnt = 0;
            while(!queue.isEmpty() && (L-- > 0)) {
                int qSize = queue.size();
                while (qSize-- > 0) {
                    Point t = queue.poll();
                    candiPosCnt++;

                    int tPipe = map[t.r][t.c];
                    for (int tDir : dirArr[tPipe]) {
                        int nr = t.r + dr[tDir];
                        int nc = t.c + dc[tDir];
                        if (isOutOfBound(nr, nc) || visited[nr][nc]) {
                            continue;
                        }

                        int cPipe = map[nr][nc];
                        for (int cDir : dirArr[cPipe]) {
                            if (isConnected(tDir, cDir)) {
                                queue.add(new Point(nr, nc));
                                visited[nr][nc] = true;
                                break;
                            }
                        }
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(candiPosCnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    private static boolean isConnected(int tDir, int cDir) {
        return Math.abs(tDir - cDir) == 2;
    }

    public static boolean isOutOfBound(int r, int c) {
        return r < 0 || N <= r || c < 0 || M <= c;
    }

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}