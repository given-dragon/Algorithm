import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    static int[][] destArr;
    static boolean[][] visited;
    static int N, M, cnt;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N+1][N+1];
        destArr = new int[M+1][2];  // [order, [row, col]]
        visited = new boolean[N+1][N+1];
        
        for (int i=1; i<=N; i++) {
            String line = br.readLine();
            for (int j=1; j<=N; j++) {
                map[i][j] = line.charAt((j-1)<<1);
            }
        }

        for (int i=1; i<=M; i++) {
            String line = br.readLine();
            destArr[i][0] = line.charAt(0)-'0';
            destArr[i][1] = line.charAt(2)-'0';
        }

        cnt = 0;
        visited[destArr[1][0]][destArr[1][1]] = true;
        recur(2, destArr[1][0], destArr[1][1]);

        System.out.println(cnt);   
    }

    public static void recur(int order, int r, int c) {
        if (order == M+1) {
            cnt++;
            return;
        }
        if (map[r][c] == '1') {
            return;
        }
        
        for (int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isOutOfBound(nr, nc) || visited[nr][nc]) {
                continue;
            }

            visited[nr][nc] = true;
            if (destArr[order][0] == nr && destArr[order][1] == nc) {
                recur(order+1, nr, nc);
            }
            else {
                recur(order, nr, nc);
            }
            visited[nr][nc] = false;
        }
        
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<1||N<r||c<1||N<c;
    }
}
