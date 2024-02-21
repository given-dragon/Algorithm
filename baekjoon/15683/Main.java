import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char WALL='6', EMPTY='0';
    static int[][] d = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    static char[][] map;
    static List<int[]> cctvCoordList = new ArrayList<>();
    static int[] cctvWaySaveArr;
    static int N, M, cctvCount, emptyCount, maxWatchCount;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j << 1);
                if (map[i][j] == EMPTY) {
                    emptyCount++;
                    continue;
                }
                if (map[i][j] < '6') {
                    cctvCoordList.add(new int[]{i, j});
                }
            }
        }
        cctvCount = cctvCoordList.size();

        cctvWaySaveArr = new int[cctvCount];
        findWayComb(0);

        bw.append(String.valueOf(emptyCount - maxWatchCount));
        bw.flush();
    }

    public static void findWayComb(int cnt) {
        if (maxWatchCount == emptyCount) {
            return;
        }
        if (cnt == cctvCount) {
            // 반복
            // cctv번호, 방향에 따른 d 배열 생성
            maxWatchCount = Math.max(maxWatchCount, getWatchCount());
            return;
        }

        for (int i = 0; i < 4; i++) {
            cctvWaySaveArr[cnt] = i;
            findWayComb(cnt+1);
        }
    }

    private static int getWatchCount() {
        boolean[][] isWatched = new boolean[N][M];
        int watchCount = 0;
        for (int cctvIdx = 0; cctvIdx < cctvCount; cctvIdx++) {
            int way = cctvWaySaveArr[cctvIdx];
            int[][] offsetArr = getOffsetArr(cctvIdx, way);

            int[] cctvCoord = cctvCoordList.get(cctvIdx);
            for (int[] offset : offsetArr) {
                int r = cctvCoord[0];
                int c = cctvCoord[1];

                while (true) {
                    r += offset[0];
                    c += offset[1];
                    if (r < 0 || N <= r || c < 0 || M <= c) {
                        break;
                    }
                    if (map[r][c] == WALL) {
                        break;
                    }
                    if (!isWatched[r][c] && (map[r][c] == EMPTY)) {
                        watchCount++;
                        isWatched[r][c] = true;
                    }
                }
            }
        }
        return watchCount;
    }

    public static int[][] getOffsetArr(int cctvIdx, int way) {
        int[] coord = cctvCoordList.get(cctvIdx);
        char cctv = map[coord[0]][coord[1]];

        if (cctv == '1') {
            return new int[][]{ d[way] };
        }
        if (cctv == '2') {
            return new int[][]{ d[way], d[(way+2)%4] };
        }
        if (cctv == '3') {
            return new int[][]{ d[way], d[(way+3)%4] };
        }
        if (cctv == '4') {
            return new int[][]{ d[way], d[(way+2)%4], d[(way+3)%4] };
        }
        // if (cctv == 5)
        return new int[][]{ d[0], d[1], d[2], d[3] };
    }
}
