import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        int[] dp = new int[10];
        Arrays.fill(dp, 1);

        int totalCnt = 10;
        for (int i = 1; i < N; i++) {
            int newCnt = 0;
            for (int j = 0; j < 10; j++) {
                int nextCnt = totalCnt - dp[j];
                dp[j] = totalCnt % 10007;
                newCnt += dp[j];
                totalCnt = nextCnt;
            }

            totalCnt = newCnt;
        }

        System.out.println(totalCnt % 10007);
    }
}
