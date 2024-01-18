import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int C = Integer.parseInt(split[0]); // 목표 고객 수
        int N = Integer.parseInt(split[1]); // 도시의 개수

        int[] exp = new int[N]; // 홍보 비용
        int[] cus = new int[N]; // 고객 수
        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            exp[i] = Integer.parseInt(split[0]);
            cus[i] = Integer.parseInt(split[1]);
        }

        int[] dp = new int[C+1];
        Arrays.fill(dp, 100001);
        for (int i = 0; i < N; i++) {
            dp[0] = Math.min(dp[0], exp[i]);
        }

        for (int i = 1; i <= C; i++) {  // 명 수
            for (int j = 0; j < N; j++) {
                if (i <= cus[j])
                    dp[i] = Math.min(dp[i], exp[j]);
                else
                    dp[i] = Math.min(dp[i], dp[i-cus[j]] + exp[j]);
            }
        }
        System.out.println(dp[C]);
    }
}
