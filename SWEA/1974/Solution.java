import java.util.Arrays;
import java.util.Scanner;

class Solution
{
    static final int SIZE = 9;
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=Integer.parseInt(sc.nextLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringBuilder sb = new StringBuilder();
            int[][] puzzle = new int[SIZE][SIZE];

            for (int i = 0; i < SIZE; i++) {
                String[] split = sc.nextLine().split(" ");
                for (int j = 0; j < SIZE; j++) {
                    puzzle[i][j] = Integer.parseInt(split[j]);
                }
            }

            sb.append('#').append(test_case).append(' ').append(checkBoard(puzzle) ? 1 : 0);
            System.out.println(sb);
        }
    }

    public static boolean checkBoard(int[][] puzzle) {
        for (int i = 0; i < SIZE; i++) {
            if (!checkLine(puzzle[i])) {
                return false;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            int[] column = new int[SIZE];
            for (int j = 0; j < SIZE; j++) {
                column[j] = puzzle[j][i];
            }
            if (!checkLine(column)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!checkDistrict(puzzle, i * 3, j * 3)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkLine(int[] puzzle) {
        boolean[] check = new boolean[SIZE];
        for (int num : puzzle) {
            check[num-1] = true;
        }
        for (boolean flag : check) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDistrict(int[][] puzzle, int r, int c) {
        boolean[] check = new boolean[SIZE];
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = puzzle[r + i][c + j];
                check[num-1] = true;
            }
        }
        for (boolean flag : check) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}