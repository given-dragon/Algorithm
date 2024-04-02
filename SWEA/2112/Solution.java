import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int D, W, K, minInjectionCnt;
    static char[][] film;
    static char[] aTypeLine, bTypeLine;
    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());   // 보호필름 두께
            W = Integer.parseInt(st.nextToken());   // 가로 크기
            K = Integer.parseInt(st.nextToken());   // 합격 기준

            film = new char[D][W];
            for (int i = 0; i < D; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    film[i][j] = line.charAt(j << 1);
                }
            }

            aTypeLine = new char[20];
            bTypeLine = new char[20];
            Arrays.fill(aTypeLine, '0');
            Arrays.fill(bTypeLine, '1');

            minInjectionCnt = 13;
            recur(0, 0);

            sb.append('#').append(tc).append(' ').append(minInjectionCnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static void recur(int layer, int injectionCnt) {
        if (minInjectionCnt <= injectionCnt) {
            return;
        }
        if (D <= layer) {
            if (checkFilm()) {
                minInjectionCnt = injectionCnt;
            }
            return;
        }

        recur(layer+1, injectionCnt);  // 선택 X

        char[] save = film[layer];

        film[layer] = aTypeLine;  // 약품투입 - A
        recur(layer+1, injectionCnt+1);

        film[layer] = bTypeLine;  // 약품투입 - B
        recur(layer+1, injectionCnt+1);

        film[layer] = save;
    }

    public static boolean checkFilm() {
        for (int j = 0; j < W; j++) {
            char leastChar = film[0][j];
            int sameCnt = 1;
            for (int i = 1; i < D; i++) {
                if (K <= sameCnt) {
                    break;
                }

                if (leastChar != film[i][j]) {
                    leastChar = film[i][j];
                    sameCnt = 1;
                    continue;
                }
                sameCnt++;
            }

            if (sameCnt < K) {
                return false;
            }
        }
        return true;
    }
}
