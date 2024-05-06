import java.io.*;
import java.util.*;

/**
 * 후보가 될 수 있는 점의 포인트
 *  row -> 0 ~ N-2
 *  col -> 1 ~ N-1
 *
 *  [풀이]
 *  1. 시작점 선택 -> r, c
 *  2. 사각형의 두 변 길이 선택 -> a, b   | a:1~N, b:1~N
 *      a, b에서 나올 수 있는 꼭지점
 *      - (r, c)
 *      - (r+a, c+a) => 최대 col
 *      - (r+a+b, c+a-b) => 최대 row
 *      - (r+b, c-b) => 최소 col
 *      최대, 최소 값들을이 map에 포함되는지 확인
 *  3. 만족한다면 이동 시작
 *      이동하며 boolean 배열로 먹은 디저트 번호 체크
 *      중복되는 번호 존재 시 2부터 재시작
 *      # 마지막 이동은 시작점이 포함되므로 1 적게 이동
 *  4. 4방향 모두 이동했다면
 *      maxCnt 수정
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] map;
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};

    static boolean[] check;
    static int[] currPoint;
    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxRow = N-2;
            int maxCol = N-1;
            int maxCnt = 0;
            // select start point
            for (int r = 0; r < maxRow; r++) {
                for (int c = 1; c < maxCol; c++) {

                    for (int a = 1; a < N; a++) {   // 사각형 변의 길이 a
                        outer:
                        for (int b = 1; b < N; b++) {   // 사각형 변의 길이 b
                            if (r + a + b >= N) {  // 최대 row 체크
                                continue;
                            }
                            if ((c + a >= N) || (c - b < 0)) {  // 최소 & 최대 col 체크
                                continue;
                            }

                            int cnt = 1;
                            currPoint = new int[]{r, c};
                            check = new boolean[101];
                            check[map[r][c]] = true;

                            for (int m = 1; m <= a; m++) {
                                if (!move(0)) {
                                    continue outer;
                                }
                                cnt++;
                            }

                            for (int m = 1; m <= b; m++) {
                                if (!move(1)) {
                                    continue outer;
                                }
                                cnt++;
                            }

                            for (int m = 1; m <= a; m++) {
                                if (!move(2)) {
                                    continue outer;
                                }
                                cnt++;
                            }

                            for (int m = 1; m < b; m++) {
                                if (!move(3)) {
                                    continue outer;
                                }
                                cnt++;
                            }

                            maxCnt = Math.max(maxCnt, cnt);
                        }
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(maxCnt==0 ? -1 : maxCnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    private static boolean move(int d) {
        currPoint[0] += dr[d];
        currPoint[1] += dc[d];
        int no = map[currPoint[0]][currPoint[1]];
        if (check[no]) {
            return false;
        }
        check[no] = true;
        return true;
    }
}