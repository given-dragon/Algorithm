import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static int DIV_SIZE = 1000000000;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][10];    // [N개 수][계단 수의 끝자리 수] = N개 수에서 끝자리 수가 같은 개수

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % DIV_SIZE;
            }
        }

        int result =0;
        for (int j = 0; j <= 9; j++) {
            result = (result + dp[N][j]) % DIV_SIZE;
        }
        System.out.println(result);
    }
}