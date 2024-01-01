import java.io.*;

class Main {
    static int[][] board;
    static long[][] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        count = new long[N][N];
        count[0][0] = 1;
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                findLeft(i, j);
                findUp(i, j);
            }
        }

        System.out.println(count[N-1][N-1]);
    }

    public static void findLeft(int row, int col) {
        int idx = 1;
        for (int i = col-1; i >= 0; i--) {
            if (board[row][i] == idx++)
                count[row][col] += count[row][i];
        }
    }
    public static void findUp(int row, int col) {
        int idx = 1;
        for (int i = row-1; i >= 0; i--) {
            if (board[i][col] == idx++)
                count[row][col] += count[i][col];
        }
    }
}