import java.io.*;

class Main {
    static int INF = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        for (int i = 0; i <= N; i++) {
            dp[i] = INF;
        }
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            int v = 1;
            for (int j = 1; v <= i; ) {
                dp[i] = Math.min(dp[i], dp[i-v] + 1);
                v = (int) Math.pow(++j, 2);
            }
        }
        System.out.println(dp[N]);
    }
}