import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, MAX = 1_000_001;;
    static int[][] price;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        price = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            price[i][0] = Integer.parseInt(st.nextToken());
            price[i][1] = Integer.parseInt(st.nextToken());
            price[i][2] = Integer.parseInt(st.nextToken());
        }

        int minCost = Integer.MAX_VALUE;
        for (int c = 0; c < 3; c++) {   // 1번집 색 선택
            int[][] dp = new int[N + 1][3]; //[번호][현재 색]

            Arrays.fill(dp[1], MAX);
            dp[1][c] = price[1][c];

            for (int i = 2; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + price[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + price[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + price[i][2];
            }

            dp[N][0] = (0 == c)
                    ? MAX
                    : Math.min(dp[N - 1][1] + price[N][0], dp[N - 1][2] + price[N][0]);
            dp[N][1] = (1 == c)
                    ? MAX
                    : Math.min(dp[N - 1][0] + price[N][1], dp[N - 1][2] + price[N][1]);

            dp[N][2] = (2 == c)
                    ? MAX
                    : Math.min(dp[N - 1][0] + price[N][2], dp[N - 1][1] + price[N][2]);

            minCost = Math.min(minCost, dp[N][0]);
            minCost = Math.min(minCost, dp[N][1]);
            minCost = Math.min(minCost, dp[N][2]);
        }

        System.out.println(minCost);
    }
}
