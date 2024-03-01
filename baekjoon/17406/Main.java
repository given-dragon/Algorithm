import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] map;
    static List<Turn> turnList;
    static int[] selectArr;
    static boolean[] selected;
    static int N, M, K, minVal=Integer.MAX_VALUE;    // 배열 행, 배열 열, 회전연산 수

    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turnList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turnList.add(new Turn(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        selectArr = new int[K];
        selected = new boolean[K];

        recur(0);
        System.out.println(minVal);
    }

    public static void recur(int idx) {
        if (idx == K) {
            int rowSum = simulation();
            minVal = Math.min(minVal, rowSum);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (selected[i]) {
                continue;
            }
            selected[i] = true;
            selectArr[idx] = i;
            recur(idx+1);
            selected[i] = false;
        }
    }

    public static int simulation() {
        int[][] copyMap = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < K; i++) {
            turnMap(i);
        }

        // 최솟값 계산
        int minRowSum = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int rowSum = 0;
            for (int j = 1; j <= M; j++) {
                rowSum += map[i][j];
            }
            minRowSum = Math.min(minRowSum, rowSum);
        }

        map = copyMap;
        return minRowSum;
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void turnMap(int selectIdx) {
        int turnIdx = selectArr[selectIdx];
        Turn t = turnList.get(turnIdx);
        int[] s = new int[]{t.r - t.s, t.c - t.s};
        int[] e = new int[]{t.r + t.s, t.c + t.s};

        int recurCount = (e[0]-s[0]+1) / 2;

        for (int i = 0; i < recurCount; i++) {

            int d = 0;
            int nr = s[0];
            int nc = s[1] + 1;
            int saveNew = map[nr][nc];
            int saveOld;
            map[nr][nc] = map[nr - dr[d]][nc - dc[d]];

            while (!(nr==s[0] && nc==s[1])) {
                // 인덱스 체크
                if (nr+dr[d] < s[0] || e[0] < nr+dr[d] || nc+dc[d] < s[1] || e[1] < nc+dc[d]) {
                    d++;
                    continue;
                }
                nr += dr[d];
                nc += dc[d];

                saveOld = saveNew;
                saveNew = map[nr][nc];
                map[nr][nc] = saveOld;

            }
            s[0]++; s[1]++;
            e[0]--; e[1]--;
        }
    }

    static class Turn {
        int r, c, s;

        public Turn(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
