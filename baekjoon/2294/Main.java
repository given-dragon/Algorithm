import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]); // 동전 개수
        int K = Integer.parseInt(split[1]); // 목표 가치

        int[] coins = new int[N+1];
        for (int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (coins[i] <= j) {
                    int save = dp[j];
                    dp[j] = Math.min(dp[j], dp[j - coins[i]]);
                    if (dp[j] != save)
                        dp[j]++;
                }
            }
        }

        if (dp[K] == Integer.MAX_VALUE)
            dp[K] = -1;
        System.out.println(dp[K]);
    }
}