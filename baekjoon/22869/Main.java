import java.io.*;

class Main {
    static int[] stones;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        split = br.readLine().split(" ");
        stones = new int[N+1];
        for (int i = 1; i <= N; i++) {
            stones[i] = Integer.parseInt(split[i-1]);
        }

        int[] dp = new int[N + 1];

        dp[1] = 1;
        for (int i = 1; i < N; i++) {
            if (dp[i] == 0) continue;

            for (int j = 1; j <= K; j++) {
                int to = i + j;
                if (to > N) break;

                if (calcEnergy(i, to) <= K) {
                    dp[to] = 1;
                }
            }
        }

        System.out.println(dp[N]==1 ? "YES" : "NO");
    }
    public static int calcEnergy(int from, int to) {
        return (to - from) * (1 + Math.abs(stones[from] - stones[to]));
    }
}