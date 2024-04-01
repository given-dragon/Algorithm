import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static Point start, end;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            visited = new boolean[N][N];
            map = new char[N][N];
            for (int i=0; i<N; i++) {
                String line = br.readLine();
                for (int j=0; j<N; j++) {
                    map[i][j] = line.charAt(j<<1);
                }
            }
            start = getPoint();
            end = getPoint();

            Queue<Point> pq = new ArrayDeque<>();
            pq.add(start);
            visited[start.r][start.c] = true;

            int ans = -1;
            while (!pq.isEmpty()) {
                Point target = pq.poll();

                if (target.r==end.r && target.c==end.c) {
                    ans = target.t;
                    break;
                }

                boolean isStormOn = (target.t+1) % 3 != 0;

                boolean isAdded = false;
                for (int d=0; d<4; d++) {
                    int nr = target.r + dr[d];
                    int nc = target.c + dc[d];
                    if (nr<0 || N<=nr || nc<0 || N<=nc || map[nr][nc]=='1') {
                        continue;
                    }

                    // 새로 이동할 위치가 폭풍이고, 켜져있다면 현재 위치에서 대기
                    if (map[nr][nc]=='2' && isStormOn) {
                        if (!isAdded) {
                            pq.add(new Point(target.r, target.c, target.t+1));
                            isAdded = true;
                        }
                        continue;
                    }

                    // 새로 이동할 위치가 빈 공간이거나, 꺼져있는 폭풍이라면 추가
                    if (!visited[nr][nc]) {
                        pq.add(new Point(nr, nc, target.t+1));
                        visited[nr][nc] = true;
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    private static Point getPoint() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        return new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
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
