import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int D, W, K, targetCnt, minCnt;
    static char[][] film;
    static char[] filmA = new char[20];
    static char[] filmB = new char[20];

    public static void main(String[] args) throws Exception {
        Arrays.fill(filmA, '0');
        Arrays.fill(filmB, '1');

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());  // 두께=R
            W = Integer.parseInt(st.nextToken());  // 너비=C
            K = Integer.parseInt(st.nextToken());  // 합격기준

            film = new char[D][W];
            for (int i = 0; i < D; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    film[i][j] = line.charAt(j << 1);
                }
            }

            minCnt = K==1 ? 0 : 13;
            recur(0, 0);
            sb.append('#').append(tc).append(' ').append(minCnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static void recur(int depth, int cnt) {
        if (cnt >= minCnt) {
            return;
        }
        if (depth == D) {
            // check film & update min
            if (checkFilm()) {
                minCnt = cnt;
            }
            return;
        }

        // select nothing
        recur(depth + 1, cnt);

        char[] save = film[depth];
        // select A
        film[depth] = filmA;
        recur(depth + 1, cnt+1);

        // select B
        film[depth] = filmB;
        recur(depth + 1, cnt+1);

        film[depth] = save;
    }

    public static boolean checkFilm() {
        int successLineCnt = 0;
        for (int j = 0; j < W; j++) {
            char target = film[0][j];
            int cnt = 1;
            for (int i = 1; i < D; i++) {
                if (target != film[i][j]) {
                    target = film[i][j];
                    cnt = 1;
                    continue;
                }
                if (++cnt >= K) {
                    successLineCnt++;
                    break;
                }
            }
            if (cnt < K) {
                return false;
            }
        }
        return successLineCnt == W;
    }
}