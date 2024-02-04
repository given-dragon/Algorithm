import java.io.*;

class Main {
    static int[][][] tornadoMap;
    static int N;
    static int outSand;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // init
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        // shark info
        int r = N/2;
        int c = r;
        int look = 0; // 0:W, 1:S, 2:E, 3:N

        // move info
        int moveAmount = 1;
        int alpha = -1;

        get4WayTornadoMap();
        while (true) {

            for (int i = 0; i < moveAmount; i++) {
                c += alpha;
                tornado(map, r, c, look);
            }
            look = (look+1) % 4;

            if (r==0 && c==0) {
                break;
            }

            alpha *= -1;

            for (int i = 0; i < moveAmount; i++) {
                r += alpha;
                tornado(map, r, c, look);
            }
            look = (look+1) % 4;

            if (moveAmount < N-1) {
                moveAmount++;
            }
        }

        System.out.println(outSand);
    }

    public static void tornado(int[][] map, int r, int c, int look) {
        int oneP = (int) (map[r][c] * 0.01);
        int twoP = (int) (map[r][c] * 0.02);
        int fiveP = (int) (map[r][c] * 0.05);
        int sevenP = (int) (map[r][c] * 0.07);
        int tenP = (int) (map[r][c] * 0.1);
        int left = map[r][c] - ((oneP+twoP+sevenP+tenP)<<1) - fiveP;
        int[] calcSandArr = {0, oneP, twoP, left, 0, fiveP, 0, sevenP, 0, 0, tenP};

        map[r][c] = 0;

        int startR = r-2;
        int startC = c-2;
        for (int i = 0; i < 5; i++) {   // t-map row
            for (int j = 0; j < 5; j++) {   // t-map col
                int nr = startR + i ;  // map row
                int nc = startC + j ;  // map col

                int movingSand = calcSandArr[tornadoMap[look][i][j]];

                // out sand
                if (nr < 0 || N <= nr ||
                        nc < 0 || N <= nc) {
                    outSand += movingSand;
                    continue;
                }

                map[nr][nc] += movingSand;
            }
        }
    }

    public static void get4WayTornadoMap() {
        tornadoMap = new int[4][5][5];
        tornadoMap[0] = new int[][]{
                {0, 0, 2, 0, 0},
                {0, 10, 7, 1, 0},
                {5, 3, 0, 0, 0},
                {0, 10, 7, 1, 0},
                {0, 0, 2, 0, 0},
        };
        for (int k = 1; k < 4; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    tornadoMap[k][4-j][i] = tornadoMap[k-1][i][j];
                }
            }
        }
    }
}