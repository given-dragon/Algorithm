import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] map, breakCntMap;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] agrs) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        breakCntMap = new int[N+1][M+1];
        for (int i=1; i<=N; i++) {
            String line = br.readLine();
            for (int j=1; j<=M; j++) {
                map[i][j] = line.charAt(j-1) - '0';
                breakCntMap[i][j] = 10_000;
            }
        }

        PriorityQueue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.bc));
        pq.add(new Position(1, 1, 0));
        breakCntMap[1][1] = 0;

        while(!pq.isEmpty()) {
            Position target = pq.poll();

            if (target.r==N && target.c==M) {
                sb.append(target.bc);
                break;
            }
            if (breakCntMap[target.r][target.c] < target.bc) {
                continue;
            }

            for (int d=0; d<4; d++) {
                int nr = target.r+dr[d];
                int nc = target.c+dc[d];
                if (nr<1 || nr>N || nc<1 || nc>M) {
                    continue;
                }

                int nbc = target.bc + map[nr][nc];
                if (breakCntMap[nr][nc] <= nbc) {
                    continue;
                }

                breakCntMap[nr][nc] = nbc;
                pq.add(new Position(nr, nc, nbc));
            }

        }
        bw.append(sb);
        bw.flush();
    }

    static class Position {
        int r, c, bc;	// bc: break count
        public Position(int r, int c, int bc) {
            this.r = r;
            this.c = c;
            this.bc = bc;
        }
    }
}
