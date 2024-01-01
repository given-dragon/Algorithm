import java.util.Arrays;
import java.util.Scanner;

class Solution
{
    static int N;
    static int M;
    static int[][] arr;
    static int[] dRow = {-1, 1, -1, 1};
    static int[] dCol = {-1, 1, 1, -1};
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=Integer.parseInt(sc.nextLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringBuilder sb = new StringBuilder();
            String[] split = sc.nextLine().split(" ");
            N = Integer.parseInt(split[0]);
            M = Integer.parseInt(split[1]);

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                split = sc.nextLine().split(" ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(split[j]);
                }
            }

            int maxCount = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int count = getCountByPlusSign(i, j);
                    maxCount = Math.max(maxCount, count);
                    count = getCountByMultiplicationSign(i, j);
                    maxCount = Math.max(maxCount, count);
                }
            }


            sb.append('#').append(test_case).append(' ').append(maxCount);
            System.out.println(sb);
        }
    }

    public static int getCountByPlusSign(int row, int col) {
        int count = -arr[row][col];
        int rowStart = Math.max(0, row - (M-1));
        int rowEnd = Math.min(N-1, row + (M-1));
        for (int i = rowStart; i <= rowEnd; i++) {
            count += arr[i][col];
        }

        int colStart = Math.max(0, col-(M-1));
        int colEnd = Math.min(N-1, col + (M-1));
        for (int i = colStart; i <= colEnd; i++) {
            count += arr[row][i];
        }
        return count;
    }
    public static int getCountByMultiplicationSign(int row, int col) {
        int count = arr[row][col];
        for (int time = 1; time < M; time++) {
            for (int j = 0; j < 4; j++) {
                int r = row + (time * dRow[j]);
                int c = col + (time * dCol[j]);
                if ((r >= 0) && (r < N) && (c >= 0) && (c < N)) {
                    count += arr[r][c];
                }
            }
        }
        return count;
    }
}