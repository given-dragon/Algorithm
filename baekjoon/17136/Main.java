import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final static int MAP_SIZE = 10;
    final static char Filled ='0', EMPTY = '1';
    static char[][] map = new char[MAP_SIZE + 1][MAP_SIZE + 1];

    static int[] paperCnt = {0, 5, 5, 5, 5, 5};
    static int minUseCnt = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {

        for (int i = 1; i <= MAP_SIZE; i++) {
            String line = br.readLine();
            for (int j = 1; j <= MAP_SIZE; j++) {
                map[i][j] = line.charAt((j-1) << 1);
            }
        }

        recur(1, 1, 0);
        System.out.println(minUseCnt == Integer.MAX_VALUE ? -1 : minUseCnt);
    }

    public static void recur(int r, int c, int usedPaperCnt) {
        if (minUseCnt <= usedPaperCnt) {
            return;
        }
        if (MAP_SIZE == r && MAP_SIZE < c) {
            minUseCnt = usedPaperCnt;
            return;
        }

        if (c > MAP_SIZE) {    // row 증가
            recur(r+1, 1, usedPaperCnt);
        }
        else {  // col 증가
            if (map[r][c] == Filled) {
                recur(r, c+1, usedPaperCnt);
                return;
            }

            //if (map[r][c] == EMPTY)
            for (int pSize = 5; pSize > 0; pSize--) {
                if (paperCnt[pSize] <= 0 || !canAttach(r, c, pSize)) {
                    continue;
                }

                paperCnt[pSize]--;
                fillSpace(r, c, pSize, Filled);

                recur(r, c+pSize, usedPaperCnt+1);

                paperCnt[pSize]++;
                fillSpace(r, c, pSize, EMPTY);
            }
        }
    }

    public static boolean canAttach(int r, int c, int pSize) {
        int endRow = r+pSize;
        int endCol = c+pSize;

        if (MAP_SIZE+1 < endRow || MAP_SIZE+1 < endCol) {
            return false;
        }
        for (int i = r; i < endRow; i++) {
            for (int j = c; j < endCol; j++) {
                if (map[i][j] == Filled) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void fillSpace(int r, int c, int pSize, char type) {
        int endRow = r+pSize;
        int endCol = c+pSize;

        for (int i = r; i < endRow; i++) {
            for (int j = c; j < endCol; j++) {
                map[i][j] = type;
            }
        }
    }
}
