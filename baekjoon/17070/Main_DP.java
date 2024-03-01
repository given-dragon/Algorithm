import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    static long[][][] dp;
    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        map = new char[N+1][N+1];
        for (int i=1; i<=N; i++) {
            String line = br.readLine();
            for (int j=1; j<=N; j++) {
                map[i][j] = line.charAt((j-1)<<1);
            }
        }

        dp = new long[3][N+1][N+1];
        dp[0][1][2] = 1;	// 가로

        for (int j=3; j<=N; j++) {	// 파이프가 처음 놓여있는 1번 row 초기화
            if (map[1][j] == '1') {
                continue;
            }
            dp[0][1][j] = dp[0][1][j-1] + dp[2][1][j-1];
        }

        for (int i=2; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (map[i][j] == '1') {
                    continue;
                }

                dp[0][i][j] = dp[0][i][j-1] + dp[2][i][j-1];
                dp[1][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
                if (map[i-1][j] == '0' && map[i][j-1] == '0') {
                    dp[2][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
                }
            }
        }

        System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
    }
}
