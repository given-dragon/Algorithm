import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        long[] dp = new long[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = i - 1;
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]) % 1_000_000_000;
        }

        System.out.println(dp[N]);
    }
}
