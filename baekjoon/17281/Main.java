import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] inningArr;
    static int[] hitterList;
    static boolean[] isVisited;
    static int maxScore, N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        inningArr = new int[N+1][9+1];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=9; j++) {
                inningArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        hitterList = new int[9+1];
        isVisited = new boolean[9+1];

        hitterList[4] = 1;
        isVisited[1] = true;

        makeHitterOrder(1);

        System.out.println(maxScore);
    }

    public static void makeHitterOrder(int cnt) {
        if (cnt == 10) {
            game();
            return;
        }
        if (cnt == 4) {
            makeHitterOrder(cnt+1);
            return;
        }

        for (int i=2; i<=9; i++) {
            if (isVisited[i]) {
                continue;
            }

            isVisited[i] = true;
            hitterList[cnt] = i;
            makeHitterOrder(cnt+1);
            isVisited[i] = false;
        }
    }

    public static void game() {
        int score = 0;
        int order = 1;
        for (int i=1; i<=N; i++) {

            int[] base = new int[4+1];	//1:1st, 2:2nd, 3:3rd, 4:home
            int outCount = 0;
            while (outCount != 3) {
                int currHitter = hitterList[order];
                int hitResult = inningArr[i][currHitter];

                order = order%9 + 1;

                if (hitResult == 0) {
                    outCount++;
                    continue;
                }

                for (int b=3; b>=1; b--) {
                    if (base[b] == 1) {
                        base[b]--;
                        int nextB = Math.min(4, b+hitResult);
                        base[nextB]++;
                    }
                }
                base[hitResult]++;
            }
            score += base[4];
        }
        maxScore = Math.max(maxScore, score);
    }
}
