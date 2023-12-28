import java.io.*;

class Main {
    static int INF = 2000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+3];
        for (int i = 0; i < N + 3; i++) {
            dp[i] = INF;
        }

        dp[3] = 1;
        dp[5] = 1;
        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }

        System.out.println((dp[N] < INF) ? dp[N] : -1);
    }
}