import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+1];   // 상담 소요 기간
        int[] P = new int[N+1];   // 상담 보수
        for (int i = 1; i <= N; i++) {
            String[] split = br.readLine().split(" ");
            T[i] = Integer.parseInt(split[0]);
            P[i] = Integer.parseInt(split[1]);
        }

        int endDay = N + 1;
        int[] dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i-1]);

            if (i + T[i] > endDay)
                continue;

            dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
        }

        System.out.println(Math.max(dp[endDay], dp[N]));
    }
}