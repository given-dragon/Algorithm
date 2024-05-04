import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, X;
    static int[][] map, turnedMap;
    static boolean[][] mapCheck, turnedMapCheck;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            mapCheck = new boolean[N][N];
            turnedMapCheck = new boolean[N][N];
            map = new int[N][N];
            turnedMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j << 1) - '0';
                    turnedMap[j][i] = map[i][j];
                }
            }

            int accessCnt = 0;
            for (int i = 0; i < N; i++) {
                if (checkForward(map[i], mapCheck[i]) &&
                        checkBackward(map[i], mapCheck[i])) {
                    accessCnt++;
                }

                if (checkForward(turnedMap[i], turnedMapCheck[i]) &&
                        checkBackward(turnedMap[i], turnedMapCheck[i])) {
                    accessCnt++;
                }
            }

            sb.append('#').append(tc).append(' ').append(accessCnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static boolean checkBackward(int[] line, boolean[] lineCheck) {
        for (int c = N-1; c > 0; c--) {
            int diff = line[c] - line[c - 1];
            if (diff < 1) {
                continue;
            }
            if (diff > 1 || c - X < 0) {
                return false;
            }

            int height = line[c-1];
            int end = c-X;
            for (int i=c-1; i>=end; i--) {
                if (line[i] != height || lineCheck[i]) {
                    return false;
                }
                lineCheck[i] = true;
            }
            c = end+1;
        }
        return true;
    }

    public static boolean checkForward(int[] line, boolean[] lineCheck) {
        for (int c = 0; c < N-1; c++) {
            int diff = line[c] - line[c + 1];
            if (diff < 1) {
                continue;
            }
            if (diff > 1 || c + X >= N) {
                return false;
            }

            int height = line[c+1];
            int end = c+X;
            for (int i=c+1; i<=end; i++) {
                if (line[i] != height || lineCheck[i]) {
                    return false;
                }
                lineCheck[i] = true;
            }
            c = end-1;
        }
        return true;
    }
}