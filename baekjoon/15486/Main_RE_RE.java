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
        for (int i = N; i >= 1; i--) {
            if (i + T[i] > endDay)
                dp[i] = dp[i+1];
            else
                dp[i] = Math.max(dp[i + T[i]] + P[i], dp[i+1]);
        }

        System.out.println(dp[1]);
    }
}