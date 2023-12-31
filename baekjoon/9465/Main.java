import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][n+1];
            int[][] values = new int[2][n+1];

            for (int j = 0; j < 2; j++) {
                String[] split = br.readLine().split(" ");
                for (int k = 1; k <= n; k++) {
                    stickers[j][k] = Integer.parseInt(split[k-1]);
                }
            }

            values[0][1] = stickers[0][1];
            values[1][1] = stickers[1][1];

            for (int j = 2; j <= n; j++) {
                values[0][j] = Math.max(values[1][j-1], values[1][j-2]) + stickers[0][j];
                values[1][j] = Math.max(values[0][j-1], values[0][j-2]) + stickers[1][j];
            }
            System.out.println(Math.max(values[0][n], values[1][n]));
        }
    }
}