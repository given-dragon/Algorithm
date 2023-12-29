import java.io.*;

// dp - bottom up
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1+2];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= N; i++) {
            boolean div2 = (i & 1) != 1;
            boolean div3 = i % 3 == 0;

            if (div3 && div2) {
                dp[i] = Math.min(dp[i / 3], dp[i >> 1]) + 1;
                continue;
            }
            if (div2) {
                dp[i] = Math.min(dp[i>>1], dp[i-1]) + 1;
                continue;
            }
            if (div3) {
                dp[i] = Math.min(dp[i/3], dp[i-1]) + 1;
                continue;
            }
            dp[i] = dp[i-1] + 1;
        }
        System.out.println(dp[N]);
    }
}